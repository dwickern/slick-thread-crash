import akka.actor.ActorSystem
import akka.stream.scaladsl._
import slick.jdbc.HsqldbProfile.api._
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Using

object SlickThreadCrash {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem()

    val numbers = sql"""
       values  (1),  (2),  (3),  (4),  (5),  (6),  (7),  (8),  (9), (10),
              (11), (12), (13), (14), (15), (16), (17), (18), (19), (20)
     """.as[Int]

    for (run <- 1 to 50) {
      Using.resource(Database.forConfig("db")) { db =>
        val graph = Source.fromPublisher(db.stream(numbers))
          .mapAsync(parallelism = 5 /* parallelism >= numThreads */) { i =>
            db.run(sql"values ($i)".as[Int].head)
          }
          .toMat(Sink.ignore)(Keep.right)

        Thread.setDefaultUncaughtExceptionHandler((t: Thread, e: Throwable) => {
          println(s"Run $run failed")
          e.printStackTrace()
          System.exit(1)
        })

        Await.result(graph.run(), atMost = 30.seconds)
        println(s"Run $run successful")
      }
    }
    Await.result(system.terminate(), atMost = 10.seconds)
  }
}
