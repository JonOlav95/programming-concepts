import scala.annotation.tailrec

class Backtracker(private var cells: Array[Array[Cell]], private val size: Int) {

  def start(): Unit = {
    _loop(cells(0)(0))
  }

  @tailrec
  private def _loop(cell: Cell): Unit = {

    var itrCell = cell

    if(cell.getChangeable){
      if(!_assignValue(cell)){


        itrCell = _backtrack(_prevValid(cell))

      }
    }

    if(itrCell.getY == size - 1 && itrCell.getX == size - 1){
      return
    } else {
      _loop(_nextNode(itrCell))
    }

  }


  @tailrec
  private def _assignValue(cell: Cell, value: Int = size): Boolean = {

    if(value == 0){
      return false
    }

    if(isValid(cell.getY, cell.getX, value)){
      cell.setValue(value)
      true
    } else {
      _assignValue(cell, value - 1)
    }

  }


  private def isValid(y: Int, x: Int, value: Int): Boolean = {


    val range = 0 until size

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

  @tailrec
  private def _backtrack(cell: Cell): Cell = {

    if (cell.getValue == 1){
      cell.setValue(0)
      _backtrack(_prevValid(cell))

    } else {
      cell.setValue(cell.getValue - 1)

      if(isValid(cell.getY, cell.getX, cell.getValue)){
        cell
      } else {
        _backtrack(cell)
      }
    }

  }


  @tailrec
  private def _prevValid(cell: Cell): Cell = {
    if(cell.getX == 0){

      if(cells(cell.getY - 1)(size - 1).getChangeable){
        cells(cell.getY - 1)(size - 1)
      } else {
        _prevValid(cells(cell.getY - 1)(size - 1))
      }

    } else {

      if(cells(cell.getY)(cell.getX - 1).getChangeable){
        cells(cell.getY)(cell.getX - 1)
      } else {
        _prevValid(cells(cell.getY)(cell.getX - 1))
      }

    }

  }

  private def _nextNode(cell: Cell) : Cell = {
    if(cell.getX == size - 1){
      cells(cell.getY + 1)(0)
    } else {
      cells(cell.getY)(cell.getX + 1)
    }
  }

}
