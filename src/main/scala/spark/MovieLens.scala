package spark

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object MovieLens {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Movie lens analysis")
    var dataPath = "data/ml-1m"
    if(args.length>0){
      dataPath = args(0)
    }else {
      conf.setMaster("local[1]")
    }
    val sc = new SparkContext(conf)

    /**
      * Step 1: Create RDDs
      */
    val DATA_PATH = dataPath

    val usersRdd = sc.textFile(DATA_PATH + "/users.dat").map(x => x.split("::")).map(x=> (x(0),x(1))) //UserID::Gender::Age::Occupation::Zip-code
    val moviesRdd = sc.textFile(DATA_PATH + "/movies.dat").map(x => x.split("::")).map(x=> (x(0),x(1)))//MovieID::Title::Genres
    val ratingsRdd = sc.textFile(DATA_PATH + "/ratings.dat").map(x => x.split("::")).map(x=> (x(0),x(1)))//UserID::MovieID::Rating::Timestamp
    /**
      * 女性看过最多的10部电影
      * 男性看过最多的10部电影
      * */
    /**
      * step1: join ratingsRdd and usersRdd by UserID
      * step2: join the above rdd and moviesRdd by MovieID
      * step3: filter the rdd using gender
      */

    usersRdd.persist(StorageLevel.MEMORY_ONLY_2)
    val resRdd = ratingsRdd.join(usersRdd) //(UserID,(MovieID,Gender))
      .map(x=> (x._2._1,(x._1,x._2._2))) //(MovieID,(UserID,Gender))
      .join(moviesRdd) //(MovieID,((UserID,Gender),Title))
      .map(x => (x._2._1._2,x._2._2 )) //(Gender,Title)
    println("top 10 movies watched by females")
    val femaleRdd = resRdd.filter(x => x._1 == "F")
      .map(x => (x._2,1)) //(Title,1)
      .reduceByKey(_+_)//(Title,n)
      .sortBy(_._2,false)
    femaleRdd.take(10).foreach(x => println(x._1 + "\t" + x._2 ))

    println("top 10 movies watched by males")
    val maleRdd = resRdd.filter(x => x._1 == "M")
      .map(x => (x._2,1)) //(Title,1)
      .reduceByKey(_+_)//(Title,n)
      .sortBy(_._2,false)
    maleRdd.take(10).foreach(x => println(x._1 + "\t" + x._2 ))

  }
}
