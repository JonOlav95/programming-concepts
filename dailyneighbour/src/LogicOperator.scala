import scala.collection.mutable.ArrayBuffer

/* Logical operations are performed on the puzzle with certainty, meaning the values set by the logic
becomes unchangeable.
The logical operations run recursively, if a value is found by the first iteration of all the logical functions,
it might unlock the possibility to find more logical connections when ran again. Only when all the logical functions
are unable to find a value to insert or the puzzle is finished the recursion stops. */

class LogicOperator(private var cells: Array[Array[Cell]], private val size: Int) {

  def logic(): Unit = {

    val bools = ArrayBuffer[Boolean]()

    val yRange = 0 until size
    val xRange = 0 until size

    yRange.foreach(y => {
      xRange.foreach(x => {

        if(cells(y)(x).getValue != 0){
          //bools.append(this.checkEndPoints(y, x))
          bools.append(neighbourChain(cells(y)(x)))
        } else {
          bools.append(this._noneNeighbour(cells(y)(x)))
        }

      })
    })



    if(bools.contains(true)){
      println("\n\nLogic Recursion")
      SolverHelper.printPuzzle(size, cells)
      logic()
    }

  }

  /* Checks cells with value 1 or the value of the size of the puzzles row/column, if such a cell exist,
  and this cell has neighbours, the value of the neighbours must respectively be 2 and size - 1 */
  private def checkEndPoints(y: Int, x: Int): Boolean = {

    val Size = this.size
    var found = false

    cells(y)(x).getValue match {
      case 1 =>
        cells(y)(x).neighbours.foreach(
          neighbour => (
            if(neighbour.getValue == 0) {
              neighbour.setValue(2)
              neighbour.setChangeable(false)
              found = true
            })
        );


      case Size =>
        cells(y)(x).neighbours.foreach(
          neighbour => (
            if(neighbour.getValue == 0) {
              neighbour.setValue(Size - 1)
              neighbour.setChangeable(false)
              found = true
            })
        );

      case _ => return false

    }

    return found

  }

  private def _noneHelper(cell: Cell, exclude: ArrayBuffer[Int]): Boolean = {

    if(cell.getValue != 0 || !cell.getChangeable){
      return false
    }

    val range = 1 to size

    range.foreach(n => {

      if(!exclude.contains(n)){
        if(isValid(cell.getY, cell.getX, n)){
          if(cell.getValue == 0){
            cell.setValue(n)
            cell.setChangeable(false)
          } else {
            cell.setChangeable(true)
            cell.setValue(0)
            return false
          }

        }
      }

    })

    true

  }


  private def _noneNeighbour(cell: Cell): Boolean = {

    val exclude = ArrayBuffer[Int]()


    val checkAdj = (innerCell: Cell) => {
      if(!cell.neighbours.contains(innerCell)) {
        if(innerCell.getValue != 0){
          exclude.append(innerCell.getValue + 1)
          exclude.append(innerCell.getValue - 1)
        }
      }
    }

    if(cell.getY != 0){
      checkAdj(cells(cell.getY - 1)(cell.getX))
    }

    if(cell.getY != size - 1){
      checkAdj(cells(cell.getY + 1)(cell.getX))
    }

    if(cell.getX != 0){
      checkAdj(cells(cell.getY)(cell.getX - 1))
    }

    if(cell.getX != size - 1){
      checkAdj(cells(cell.getY)(cell.getX + 1))
    }

    if(_noneHelper(cell, exclude))
      true
    else
      false
  }

  @scala.annotation.tailrec
  private def _setChainValues(cell: Cell, yItr: (Int) => Int, xItr: (Int) => Int, valueItr: (Int) => Int): Unit = {
    val y = yItr(cell.getY)
    val x = xItr(cell.getX)
    val value = valueItr(cell.getValue)


    if(x == -1 || x == size || y == -1 || y == size){
      return
    }

    if(cell.neighbours.contains(cells(y)(x))){


      if(cells(y)(x).getChangeable){
        cells(y)(x).setValue(value)
        cells(y)(x).setChangeable(false)
      }

      _setChainValues(cells(y)(x), yItr, xItr, valueItr)
    }


  }

  private def _neighbourChainHelper(cell: Cell, yItr: (Int) => Int, xItr: (Int) => Int, valueItr: (Int) => Int, potValue: Int): Boolean = {

    val y = yItr(cell.getY)
    val x = xItr(cell.getX)
    val value = valueItr(potValue)

    if(x == -1 || x == size || y == -1 || y == size || cells(y)(x).getValue != 0){
      return true
    }

    if(cell.neighbours.contains(cells(y)(x))){

      if(isValid(y, x, value)) {
        return _neighbourChainHelper(cells(y)(x), yItr, xItr, valueItr, value)
      } else {
        return false
      }
    }

    true
  }



  private def neighbourChain(cell: Cell): Boolean = {

    var found = false
    val value = cell.getValue


    if(!_neighbourChainHelper(cell, (y: Int) => y, (x: Int) => x + 1, (v: Int) => v + 1, value)){
      _setChainValues(cell, (y: Int) => y, (x: Int) => x + 1, (v: Int) => v - 1)
      found = true
    }

    if(!_neighbourChainHelper(cell, (y: Int) => y, (x: Int) => x - 1, (v: Int) => v + 1, value)){
      _setChainValues(cell, (y: Int) => y, (x: Int) => x - 1, (v: Int) => v - 1)
      found = true
    }

    if(!_neighbourChainHelper(cell, (y: Int) => y + 1, (x: Int) => x, (v: Int) => v + 1, value)){
      _setChainValues(cell, (y: Int) => y + 1, (x: Int) => x, (v: Int) => v - 1)
      found = true
    }

    if(!_neighbourChainHelper(cell, (y: Int) => y - 1, (x: Int) => x, (v: Int) => v + 1, value)){
      _setChainValues(cell, (y: Int) => y - 1, (x: Int) => x, (v: Int) => v - 1)
      found = true
    }

    if(!_neighbourChainHelper(cell, (y: Int) => y, (x: Int) => x + 1, (v: Int) => v - 1, value)){
      _setChainValues(cell, (y: Int) => y, (x: Int) => x + 1, (v: Int) => v + 1)
      found = true
    }

    if(!_neighbourChainHelper(cell, (y: Int) => y, (x: Int) => x - 1, (v: Int) => v - 1, value)){
      _setChainValues(cell, (y: Int) => y, (x: Int) => x - 1, (v: Int) => v + 1)
      found = true
    }

    if(!_neighbourChainHelper(cell, (y: Int) => y + 1, (x: Int) => x, (v: Int) => v - 1, value)){
      _setChainValues(cell, (y: Int) => y + 1, (x: Int) => x, (v: Int) => v + 1)
      found = true
    }

    if(!_neighbourChainHelper(cell, (y: Int) => y - 1, (x: Int) => x, (v: Int) => v - 1, value)){
      _setChainValues(cell, (y: Int) => y - 1, (x: Int) => x, (v: Int) => v + 1)
      found = true
    }

    found
  }


  // Checks if the value at the given position follows the daily neighbours puzzle constraints
  private def isValid(y: Int, x: Int, value: Int): Boolean = {


    val range = 0 until size

    if(value < 0 || value > size){
      return false
    }

    range.foreach(
      n => {
        if(y != n){
          if(cells(n)(x).getValue == value){
            return false
          }
        }

        if(x != n){
          if(cells(y)(n).getValue == value){
            return false
          }
        }
      })

    cells(y)(x).neighbours.foreach(
      neighbour =>
        if(neighbour.getValue != 0){
          if(value - 1 != neighbour.getValue && value + 1 != neighbour.getValue){
            return false
          }
        }
    )

    true
  }





}
