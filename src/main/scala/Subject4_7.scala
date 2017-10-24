package com.cauwt.learning.scala
import scala.collection.JavaConversions.propertiesAsScalaMap
object Subject4_7 {
  //打印出所有Java系统属性的表格
  def main(args: Array[String]): Unit = {
    val props: scala.collection.mutable.Map[String,String] = System.getProperties//.take(10)
    val maxLength = props.keys.map(_.length).max //get max length
    for((k,v) <- props){
      println(k + " "*(maxLength-k.length+5)+ "|"+" "*5 + v)
    }
  }

}
