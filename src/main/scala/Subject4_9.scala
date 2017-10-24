import scala.util.Random

object Subject4_9 {
  def main(args: Array[String]): Unit = {
    val n = 45
    val top = 100
    val a = new Array[Int](n)
    val r = new Random()
    for(i <- 0 until a.length){
      a(i) = r.nextInt(top) -top /2
    }

    val (lt,eq,gt) = lteqgt(a,0)
    println("array:" + a.mkString(", "))
    println("lt: "+lt + " eq: " + eq + " gt: " + gt)
  }
  def lteqgt(values:Array[Int], v:Int)={
    val lt = values.count(i => i<v)
    val eq = values.count(i => i==v)
    val gt = values.count(i => i>v)
    (lt,eq,gt)
  }
}
