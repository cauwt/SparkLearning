//抽象类Person
abstract class Person

//case class Student
case class Student(name:String,age:Int,studentNo:Int) extends Person
//case class Teacher
case class Teacher(name:String,age:Int,teacherNo:Int) extends Person
//case class Nobody
case class Nobody(name:String) extends Person

object CaseClassDemo{
  def main(args: Array[String]): Unit = {
    //case class 会自动生成apply方法，从而省去new操作
    val p:Person=Teacher("john",18,1024)
    val s:Teacher=Teacher("john",18,1024)
    //match case 匹配语法
    p  match {
      case Student(name,age,studentNo)=>println("Student: " + name+":"+age+":"+studentNo)
      case Teacher(name,age,teacherNo)=>println("Teacher: " + name+":"+age+":"+teacherNo)
      case Nobody(name)=>println("Nobody: "+ name)
    }
    s.copy(teacherNo= 3212)
  }
}