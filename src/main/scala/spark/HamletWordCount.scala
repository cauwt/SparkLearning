package spark

import org.apache.spark.{SparkConf, SparkContext}

object HamletWordCount {
  def main(args: Array[String]): Unit = {
    val filePath = if (args.length > 0)  args(0) else "hdfs://master:9000/HamletWordCount/input/Hamlet.txt"
    val stopWordFile = if (args.length > 1) args(1) else "hdfs://master:9000/HamletWordCount/input/stopword.txt"
    val output = if (args.length > 2) args(2) else "hdfs://master:9000/HamletWordCount/output/"

    val conf = new SparkConf().setAppName("Hamlet Word Count")
    val sc = new SparkContext(conf)

    val stopwords = sc.textFile(stopWordFile)
      .map(line => line.toLowerCase.trim)
      .collect.toSet

    val rdd = sc.textFile(filePath).flatMap{line => line.replaceAll("['.,:?!-]", " ").split("\\s+")}
      .filter(word => !stopwords.contains(word))
      .map( (_,1))
      .reduceByKey(_+_)
      .saveAsTextFile(output)

    sc.stop()
  }
}
