import scala.util.Random

object Subject4_8 {
  def main(args: Array[String]): Unit = {
    val a = new Array[Int](5)
    val r = new Random()
    for(i <- 0 until a.length){
      a(i) = r.nextInt(100) -50
    }

    val (min,max) = minmax(a)
    println("array:" + a.mkString(", "))
    println("min: "+min + " max: " + max)
  }
  def minmax(values:Array[Int])={
    (values.min,values.max)
  }
}
