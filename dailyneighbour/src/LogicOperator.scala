import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

/* Logical operations are performed on the puzzle with certainty, meaning the values set by the logic
becomes unchangeable.
The logical operations run recursively, if a value is found by the first iteration of all the logical functions,
it might unlock the possibility to find more logical connections when ran again. Only when all the logical functions
are unable to find a value to insert or the puzzle is finished the recursion stops. */

class LogicOperator(private var cells: Array[Array[Cell]], private val size: Int) {


  val vc = new ValidChecker(cells, size)

  def start(): Unit = {
    _logic()
  }

  @tailrec
  private def _logic(): Unit = {

    var recursion = false

    val yRange = 0 until size
    val xRange = 0 until size

    // Iterates all the cells in the puzzle
    yRange.foreach(y => {
      xRange.foreach(x => {

        // Operations for cells with a value
        if(cells(y)(x).getValue != 0){
          if(_neighbourChain(cells(y)(x))) {
            recursion = true
          }

        // Operations for cells without a value
        } else {
          if(_oneValid(cells(y)(x))){
            recursion = true
          }
        }

      })
    })

    // One cells or more were found, the logic functionality runs again
    if(recursion){
      _logic()
    }

  }

  /* If there is only one valid value for the cell, the value is set and the function will return true.
  If there are more than one valid value for the cell, no value is set and the function returns false */
  private def _oneValid(cell: Cell): Boolean = {

    val range = 1 to size

    // Iterates through all the possible values for a cell
    range.foreach(n => {
      if(vc.isValid(cell.getY, cell.getX, n)){

        if(cell.getValue == 0){

          cell.setValue(n)
          cell.setChangeable(false)

        } else {

          cell.setChangeable(true)
          cell.setValue(0)
          return false

        }

      }
    })

    true

  }

  // Certain pattern detected in a chain of neighbours, thus, the values are set with certainty.
  @tailrec
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

  /* The function checks if an ascending or descending pattern in a chain of neighbours fits or not.
   The function accepts three lambdas, one to decide the x-direction, one to decide the y-direction, and one to decide
   whether the value is increasing or decreasing. Thus, the function is flexible for checking both columns and rows,
   and in both directions. 'potValue' holds the value of the cell at the beginning of the chain. */
  private def _checkChainValue(cell: Cell, yItr: (Int) => Int, xItr: (Int) => Int, valueItr: (Int) => Int, potValue: Int): Boolean = {

    val y = yItr(cell.getY)
    val x = xItr(cell.getX)
    val value = valueItr(potValue)

    if(x == -1 || x == size || y == -1 || y == size || cells(y)(x).getValue != 0){
      return true
    }

    if(cell.neighbours.contains(cells(y)(x))){

      if(vc.isValid(y, x, value)) {
        return _checkChainValue(cells(y)(x), yItr, xItr, valueItr, value)
      } else {
        return false
      }
    }

    true
  }

  /* Checks a chain of neighbours. A chain of neighbours consists of two or more neighbours next to each other on
  the same row or column. This chain must have an ascending or descending pattern, e.g. 4-5-6 or 9-8.
  If the descending or ascending pattern does not fit, the chain must have the opposite pattern of the non fitting
  pattern. However, if both pattern fits, there is no way to know which is the correct one. */
  private def _neighbourChain(cell: Cell): Boolean = {

    var found = false
    val value = cell.getValue

    // Checks the cells row both ways, the column bot ways, and both decreasing and increasing value for all options

    if(!_checkChainValue(cell, (y: Int) => y, (x: Int) => x + 1, (v: Int) => v + 1, value)){
      _setChainValues(cell, (y: Int) => y, (x: Int) => x + 1, (v: Int) => v - 1)
      found = true
    }

    if(!_checkChainValue(cell, (y: Int) => y, (x: Int) => x - 1, (v: Int) => v + 1, value)){
      _setChainValues(cell, (y: Int) => y, (x: Int) => x - 1, (v: Int) => v - 1)
      found = true
    }

    if(!_checkChainValue(cell, (y: Int) => y + 1, (x: Int) => x, (v: Int) => v + 1, value)){
      _setChainValues(cell, (y: Int) => y + 1, (x: Int) => x, (v: Int) => v - 1)
      found = true
    }

    if(!_checkChainValue(cell, (y: Int) => y - 1, (x: Int) => x, (v: Int) => v + 1, value)){
      _setChainValues(cell, (y: Int) => y - 1, (x: Int) => x, (v: Int) => v - 1)
      found = true
    }

    if(!_checkChainValue(cell, (y: Int) => y, (x: Int) => x + 1, (v: Int) => v - 1, value)){
      _setChainValues(cell, (y: Int) => y, (x: Int) => x + 1, (v: Int) => v + 1)
      found = true
    }

    if(!_checkChainValue(cell, (y: Int) => y, (x: Int) => x - 1, (v: Int) => v - 1, value)){
      _setChainValues(cell, (y: Int) => y, (x: Int) => x - 1, (v: Int) => v + 1)
      found = true
    }

    if(!_checkChainValue(cell, (y: Int) => y + 1, (x: Int) => x, (v: Int) => v - 1, value)){
      _setChainValues(cell, (y: Int) => y + 1, (x: Int) => x, (v: Int) => v + 1)
      found = true
    }

    if(!_checkChainValue(cell, (y: Int) => y - 1, (x: Int) => x, (v: Int) => v - 1, value)){
      _setChainValues(cell, (y: Int) => y - 1, (x: Int) => x, (v: Int) => v + 1)
      found = true
    }

    found
  }

  private def _next(cell: Cell) : Cell = {
    if(cell.getX == size - 1){
      cells(cell.getY + 1)(0)
    } else {
      cells(cell.getY)(cell.getX + 1)
    }
  }

}
