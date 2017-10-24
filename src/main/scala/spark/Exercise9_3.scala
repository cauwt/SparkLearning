package spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.hadoop.fs.Path
import org.apache.hadoop.conf.Configuration

object Exercise9_3 {
  def main(args: Array[String]): Unit = {
    //aim: average by key and then save result to /temp/output
    val sparkConf = new SparkConf()
    sparkConf.setAppName("Exercise9_3")
    sparkConf.setMaster("yarn-client")
    val sc = new SparkContext(sparkConf)
    //process hadoop path
    val output = "/temp/output"
    val hadoopConf: Configuration = new Configuration()
    val outputPath = new Path(output)
    val fs = outputPath.getFileSystem(hadoopConf)
    if (fs.exists(outputPath)) fs.delete(outputPath, true)

    //source data
    val input = sc.parallelize(List(("coffee", 1) , ("coffee", 3) ,
      ("panda", 4), ("coffee", 5), ("street", 2), ("panda", 5)))
    //adding counter
    val rdd2 = input.map(x=> (x._1,(x._2,1)))
    //sum by key
    val rdd3 = rdd2.reduceByKey((x,y) => (x._1+y._1,x._2+y._2))
    //get average by key
    val rdd4 = rdd3.map(x => x._1+"\t"+x._2._1/1.0/x._2._2)
    //save
    rdd4.saveAsTextFile(output)


    sc.stop()
  }
}
