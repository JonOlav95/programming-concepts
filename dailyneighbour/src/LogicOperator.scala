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
          bools.append(this.checkEndPoints(y, x))
          bools.append(this._noneNeighbour(cells(x)(y)))
        } else {
          bools.append(this._noneNeighbour(cells(x)(y)))
        }

      })
    })

    bools.append(neighbourChain())

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

  /* If there is a cell with the value n and an horizontal neighbour, the function checks is the row with the cells
  has a cell with value (n - 1) or (n + 1). If a cell with value (n - 1) exists, the neighbour is given value (n + 1),
  as it is the only possible option. And naturally the other way around if a cell in the row with value (n + 1) exists.
  For vertical neighbours the column is checked and the same operations are performed. */
  private def checkCloseNeighbour(y: Int, x: Int): Boolean = {

    var cellVal = cells(y)(x).getValue

    cells(y)(x).neighbours.foreach(
      neighbour =>
        if(neighbour.getValue == 0 && neighbour.getChangeable){

          if(y == neighbour.getY){
            if(cells(y).exists(x => x.getValue == cellVal - 1)){

              neighbour.setValue(cellVal + 1)
              neighbour.setChangeable(false)
              return true

            }

            else if(cells(y).exists(x => x.getValue == cellVal + 1)){

              neighbour.setValue(cellVal - 1)
              neighbour.setChangeable(false)
              return true

            }



          }

          if(x == neighbour.getX){

            for(i <- 0 until size){

              if(cells(i)(x).getValue == cellVal + 1){
                neighbour.setValue(cellVal - 1)
                neighbour.setChangeable(false)
                return true

              }

              else if(cells(i)(x).getValue == cellVal - 1){
                neighbour.setValue(cellVal + 1)
                neighbour.setChangeable(false)
                return true
              }

            }


          }

        }
    )

    false
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


    if(cell.getY != 0){
      if(!cell.neighbours.contains(cells(cell.getY - 1)(cell.getX))){
        if(cells(cell.getY - 1)(cell.getX).getValue != 0){
          exclude.append(cells(cell.getY - 1)(cell.getX).getValue + 1)
          exclude.append(cells(cell.getY - 1)(cell.getX).getValue - 1)
        }
      }
    }

    if(cell.getY != size - 1){
      if(!cell.neighbours.contains(cells(cell.getY + 1)(cell.getX))){
        if(cells(cell.getY + 1)(cell.getX).getValue != 0){
          exclude.append(cells(cell.getY + 1)(cell.getX).getValue + 1)
          exclude.append(cells(cell.getY + 1)(cell.getX).getValue - 1)
        }
      }
    }

    if(cell.getX != 0){
      if(!cell.neighbours.contains(cells(cell.getY)(cell.getX - 1))){
        if(cells(cell.getY)(cell.getX - 1).getValue != 0){
          exclude.append(cells(cell.getY)(cell.getX - 1).getValue + 1)
          exclude.append(cells(cell.getY)(cell.getX - 1).getValue - 1)
        }
      }
    }

    if(cell.getX != size - 1){
      if(!cell.neighbours.contains(cells(cell.getY)(cell.getX + 1))){
        if(cells(cell.getY)(cell.getX + 1).getValue != 0){
          exclude.append(cells(cell.getY)(cell.getX + 1).getValue + 1)
          exclude.append(cells(cell.getY)(cell.getX + 1).getValue - 1)
        }
      }
    }

    if(_noneHelper(cell, exclude))
      true
    else
      false
  }

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



  private def neighbourChain(): Boolean = {

    val range = 0 until size
    var found = false


    range.foreach(y => {
      range.foreach(x => {

        if(cells(y)(x).getValue != 0){

          if(!_neighbourChainHelper(cells(y)(x), (y: Int) => y, (x: Int) => x + 1, (v: Int) => v + 1, cells(y)(x).getValue)){
            _setChainValues(cells(y)(x), (y: Int) => y, (x: Int) => x + 1, (v: Int) => v - 1)
            found = true
          }

          if(!_neighbourChainHelper(cells(y)(x), (y: Int) => y, (x: Int) => x - 1, (v: Int) => v + 1, cells(y)(x).getValue)){
            _setChainValues(cells(y)(x), (y: Int) => y, (x: Int) => x - 1, (v: Int) => v - 1)
            found = true
          }

          if(!_neighbourChainHelper(cells(y)(x), (y: Int) => y + 1, (x: Int) => x, (v: Int) => v + 1, cells(y)(x).getValue)){
            _setChainValues(cells(y)(x), (y: Int) => y + 1, (x: Int) => x, (v: Int) => v - 1)
            found = true
          }

          if(!_neighbourChainHelper(cells(y)(x), (y: Int) => y - 1, (x: Int) => x, (v: Int) => v + 1, cells(y)(x).getValue)){
            _setChainValues(cells(y)(x), (y: Int) => y - 1, (x: Int) => x, (v: Int) => v - 1)
            found = true
          }


          if(!_neighbourChainHelper(cells(y)(x), (y: Int) => y, (x: Int) => x + 1, (v: Int) => v - 1, cells(y)(x).getValue)){
            _setChainValues(cells(y)(x), (y: Int) => y, (x: Int) => x + 1, (v: Int) => v + 1)
            found = true
          }

          if(!_neighbourChainHelper(cells(y)(x), (y: Int) => y, (x: Int) => x - 1, (v: Int) => v - 1, cells(y)(x).getValue)){
            _setChainValues(cells(y)(x), (y: Int) => y, (x: Int) => x - 1, (v: Int) => v + 1)
            found = true
          }

          if(!_neighbourChainHelper(cells(y)(x), (y: Int) => y + 1, (x: Int) => x, (v: Int) => v - 1, cells(y)(x).getValue)){
            _setChainValues(cells(y)(x), (y: Int) => y + 1, (x: Int) => x, (v: Int) => v + 1)
            found = true
          }

          if(!_neighbourChainHelper(cells(y)(x), (y: Int) => y - 1, (x: Int) => x, (v: Int) => v - 1, cells(y)(x).getValue)){
            _setChainValues(cells(y)(x), (y: Int) => y - 1, (x: Int) => x, (v: Int) => v + 1)
            found = true
          }

        }
      })
    })

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
