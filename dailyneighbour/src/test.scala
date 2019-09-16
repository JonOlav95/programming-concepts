

object test {


  def main(args: Array[String]): Unit = {


    for(n <- 10 to 0 by -1){
      print(n)

    }

    val a = 1 to 10
    val b = 1 to 10

    a.foreach(y => { b.foreach(x => println("y: " + y + "x: " + x) )})
  }
}
