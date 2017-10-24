import java.util

object TryScala {
  def main(args:Array[String]): Unit ={
    //chapter 4
    //subject 4.2: use mutable map
//    val in = new java.util.Scanner(new java.io.File("/home/zkpk/test/README.txt"))
//    val map1 = scala.collection.mutable.Map[String,Int]()
//    while(in.hasNext()){
//      val array = in.next().split("[^a-zA-z0-9]+")
//      array.foreach(x => {
//        if(map1.contains(x))
//          map1(x) += 1
//        else
//          map1(x) =1
//      })
//    }
//    map1.foreach(println)
    //subject 4.3: use immutable map
//    val in = new java.util.Scanner(new java.io.File("/home/zkpk/test/README.txt"))
//    var map1 = scala.collection.immutable.Map[String,Int]()
//    while(in.hasNext()){
//      val array = in.next().split("[^a-zA-z0-9]+")
//      array.foreach(x => {
//        if(map1.contains(x)) {
//          val c:Int = map1(x) +1
//          map1 = map1 -x
//          map1 = map1 + (x -> c )
//        }
//
//        else
//          map1 += (x -> 1)
//      })
//    }
//    map1.foreach(println)

    //subject 4.4 use sored map
//    val in = new java.util.Scanner(new java.io.File("/home/zkpk/test/README.txt"))
//    var map1 = scala.collection.immutable.SortedMap[String,Int]()
//    while(in.hasNext()){
//      val array = in.next().split("[^a-zA-z0-9]+")
//      array.foreach(x => {
//        if(map1.contains(x)) {
//          val c:Int = map1(x) +1
//          map1 = map1 -x
//          map1 = map1 + (x -> c )
//        }
//
//        else
//          map1 += (x -> 1)
//      })
//    }
    //subject 4.5 use TreeMap
    import scala.collection.JavaConversions.mapAsScalaMap
    val in = new java.util.Scanner(new java.io.File("/home/zkpk/test/README.txt"))
    val map1: scala.collection.mutable.Map[String,Int] = new util.TreeMap[String,Int]
    while(in.hasNext()){
      val array = in.next().split("[^a-zA-z0-9]+")
      array.foreach(x => {
        if(map1.contains(x)) {
          val c:Int = map1(x) +1
          map1(x) += 1
        }

        else
          map1 += (x -> 1)
      })
    }
    map1.foreach(println)

  }
}
