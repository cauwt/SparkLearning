package com.cauwt.SparkLearing

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.spark.{SparkConf, SparkContext}

object scalaWordCount2 {
  def main(args: Array[String]):Unit= {
      val conf = new SparkConf()
      conf.setAppName("scala word count")
      conf.setMaster(args(0)) //local
//      val input = "http://master:9000/SparkLearning/WordCount/input/"
//      val output = "http://master:9000/SparkLearning/WordCount/output/"
      val input = args(1) // http://master:9000/SparkLearning/WordCount/input/
      val output = args(2) // http://master:9000/SparkLearning/WordCount/output/
      val hadoopConf: Configuration = new Configuration()
      val outputPath = new Path(output)
      val fs = outputPath.getFileSystem(hadoopConf)
      if (fs.exists(outputPath)) fs.delete(outputPath, true)


      val sc = new SparkContext(conf)
      val rdd = sc.textFile(input)
      val rdd2 = rdd.flatMap(line => line.split(" "))
      val rdd3  = rdd2.map(word => (word,1))
      val rdd4 = rdd3.reduceByKey( (x,y) => x+y)
      rdd4.saveAsTextFile(output)
      sc.stop()
  }
}
