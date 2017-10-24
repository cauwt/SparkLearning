package spark

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.spark.{SparkConf, SparkContext}

object Exercise9_4 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
    sparkConf.setAppName("Exercise9_4")
    sparkConf.setMaster("yarn-client")
    val sc = new SparkContext(sparkConf)
    //process hadoop path
    //aim: average by key and then save result to /temp/output
    val input = "/SparkLearning/data/Exercise9_4/input.txt"
    val output = "/SparkLearning/output/Exercise9_4"
    val hadoopConf: Configuration = new Configuration()
    val outputPath = new Path(output)
    val fs = outputPath.getFileSystem(hadoopConf)
    if (fs.exists(outputPath)) fs.delete(outputPath, true)
    //val rdd = sc.textFile(input).map(_.split(",")).map(x => (x(0),(x(1).toInt,x(2).toInt,x(3).toInt,1)))
    //SELECT id, SUM(x), MAX(y), MIN(z), AVERAGE(x) FROMT GROUP BY id
    //rdd.reduceByKey((x,y) =>(x._1+y._1,x._2 max y._2,x._3 min y._3,x._4+y._4)).map{case(x,y) => (x,y._1,y._2,y._3,y._1/1.0/y._4)}.collect()
    //val rdd1 = rdd.reduceByKey((x,y) =>(x._1+y._1,x._2 max y._2,x._3 min y._3,x._4+y._4)).map{case(x,y) => x+ ","+y._1+ ","+y._2+ ","+y._3+ ","+ y._1 / 1.0 / y._4}

    //SELECT SUM(x), MAX(y), MIN(z), AVERAGE(x) FROM T
    val rdd = sc.textFile(input).map(_.split(",")).map(x => (x(1).toInt,x(2).toInt,x(3).toInt,1))
    val res = rdd.reduce((x,y) => (x._1+y._1,x._2 max y._2,x._3 min y._3,x._4+y._4))
    val rdd1 = sc.parallelize(Seq(res),1)
    rdd1.saveAsTextFile(output)


    sc.stop()
  }
}
