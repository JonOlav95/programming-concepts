class Backtracker(private var cells: Array[Array[Cell]], private val size: Int) {


  var steps = 0;


  private def isValid(y: Int, x: Int, value: Int): Boolean = {


    for(n <- 0 until size){

      if(cells(n)(x).getValue == value){
        return false
      }

      if(cells(y)(n).getValue == value){
        return false
      }

    }


    cells(y)(x).neighbours.foreach(
      neighbour =>
        if(neighbour.getValue != 0){

          if(value - 1 != neighbour.getValue && value + 1 != neighbour.getValue){
            return false
          }

        }

    )



    return true
  }

  private def finished(y: Int, x:Int): Boolean = {

    for(i <- 0 until size){
      for(j <- 0 until size){
        if(cells(i)(j).getValue == 0)
          return false
      }
    }

    println("Total steps used in backtracking: " + steps)
    return true
  }

  // FIXME: Stackoverflow at 4793 steps
  def backtrack(y: Int, x:Int , value: Int, forward: Boolean = false): Unit = {

    steps += 1
    println(steps)

    if(!cells(y)(x).getChangeable){

      if(forward){


        if(x == size - 1) {
          backtrack(y + 1, 0, size)
        } else {
          backtrack(y, x + 1, size)
        }
      } else {
        bstep(y, x, value)
      }

      return
    }

    if(value == 0){

      cells(y)(x).setValue(0)
      bstep(y, x, value)

    }

    else if(isValid(y, x, value)){
      cells(y)(x).setValue(value)

      if(this.finished(y, x)) {
        return
      }

      if(x == size - 1) {
        backtrack(y + 1, 0, size, forward = true)
      } else {
        backtrack(y, x + 1, size, forward = true)
      }

    } else {
      backtrack(y, x, value - 1)
    }

  }

  private def bstep(y: Int, x:Int , value: Int): Unit ={
    if(x == 0) {
      backtrack(y - 1, size - 1, cells(y - 1)(size - 1).getValue - 1)
    } else {
      backtrack(y, x - 1, cells(y)(x - 1).getValue - 1)
    }
  }

}
