object Subject4_2 {
  def main(args: Array[String]): Unit = {
    val in = new java.util.Scanner(new java.io.File("/home/zkpk/test/README.txt"))
    val map1 = scala.collection.mutable.Map[String, Int]()
    while (in.hasNext()){
      val w = in.next()
      map1(w) = map1.getOrElse(w,0) + 1
    }

    map1.foreach(println)
  }
}