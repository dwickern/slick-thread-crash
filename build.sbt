ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "slick-thread-crash",
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.4.1",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
      "com.typesafe.akka" %% "akka-stream" % "2.7.0",
      "com.h2database" % "h2" % "2.1.214",
      "org.slf4j" % "slf4j-nop" % "1.7.36"
    )
  )
