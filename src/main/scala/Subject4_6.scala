object Subject4_6 {
  def main(args: Array[String]): Unit = {
    val weeks = scala.collection.mutable.LinkedHashMap[String,Int]()
    weeks += ("Monday" -> java.util.Calendar.MONDAY)
    weeks += ("Tuesday" -> java.util.Calendar.TUESDAY)
    weeks += ("Friday" -> java.util.Calendar.FRIDAY)
    weeks += ("Saturday" -> java.util.Calendar.SATURDAY)
    weeks += ("Wednesday" -> java.util.Calendar.WEDNESDAY)
    weeks += ("Thursday" -> java.util.Calendar.THURSDAY)
    weeks += ("Sunday" -> java.util.Calendar.SUNDAY)
    weeks.foreach(println)
  }

}
