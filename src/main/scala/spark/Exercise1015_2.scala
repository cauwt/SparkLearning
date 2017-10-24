package spark

import org.apache.spark.{SparkConf, SparkContext}

object Exercise1015_2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("scala word count")
    conf.setMaster("local")

    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(1 to 6, 3)
    val rdd2 = sc.makeRDD(7 to 11, 2)

    val rdd4 = rdd1.union(rdd2)
    rdd4.foreach(println)
    sc.stop()
  }
}
