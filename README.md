## Slick thread crash reproduction

https://github.com/slick/slick/issues/1856
https://github.com/slick/slick/issues/2417
https://github.com/slick/slick/discussions/2432

### Usage

```
sbt "runMain SlickThreadCrash"
```

### Sample Output

```
[info] running SlickThreadCrash 
Run 1 successful
Run 2 successful
Run 3 successful
Run 4 successful
Run 5 successful
Run 6 successful
Run 7 successful
Run 8 successful
Run 9 failed
java.lang.IllegalArgumentException: requirement failed: count cannot be increased
        at scala.Predef$.require(Predef.scala:337)
        at slick.util.ManagedArrayBlockingQueue.$anonfun$increaseInUseCount$1(ManagedArrayBlockingQueue.scala:43)
        at scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
        at slick.util.ManagedArrayBlockingQueue.locked(ManagedArrayBlockingQueue.scala:201)
        at slick.util.ManagedArrayBlockingQueue.increaseInUseCount(ManagedArrayBlockingQueue.scala:42)
        at slick.util.AsyncExecutor$$anon$1$$anon$2.beforeExecute(AsyncExecutor.scala:161)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1126)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
        at java.base/java.lang.Thread.run(Thread.java:829)
```
