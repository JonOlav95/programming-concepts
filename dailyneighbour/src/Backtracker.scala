import scala.annotation.tailrec

/* The code for the backtracking algorithm for solving the puzzle. */

class Backtracker(private var cells: Array[Array[Cell]], private val size: Int) {

  val vc = new ValidChecker(cells, size)

  // Initiate the backtracking algorithm at the cell in position x: 0, y: 0
  def start(): Unit = {
    _loop(cells(0)(0))
  }

  /* Recursively iterates through all the cells in the puzzle assigning values when possible, and backtracking
  when not possible. */
  @tailrec
  private def _loop(cell: Cell): Unit = {

    /* The next cell used for the argument in the recursion. If there is no backtracking this loop, itrCell will be the
    natural next cell of the current cell. If backtracking is initiated itrCell will be further back in the puzzle. */
    var itrCell = cell

    // Attempts to set a value for the current cell if it is changeable
    if(cell.getChangeable){
      if(!_assignValue(cell)){

        // No valid value for the current selected cell; Backtracking initiated
        itrCell = _backtrack(_prevValid(cell))

      }
    }

    // Recursively iterates while in the domain of the puzzle
    if(!(itrCell.getY == size - 1 && itrCell.getX == size - 1)) {
      _loop(_next(itrCell))
    }

  }


  /* Incrementally assigns a value to a cell starting at the maximum valid value for the current puzzle.
  The incrementation is performed recursively.
  If there is no value between the maximum valid value and 1, the function returns false, otherwise
  the function sets first found valid value and returns true. */
  @tailrec
  private def _assignValue(cell: Cell, value: Int = size): Boolean = {

    // Value recursively incremented down to 0, meaning there is no valid value, returns false
    if(value == 0){
      return false
    }

    if(vc.isValid(cell.getY, cell.getX, value)){
      cell.setValue(value)
      true
    } else {
      _assignValue(cell, value - 1)
    }

  }

  /* The backtracking function increments the value of the given cell by 1 until it finds a valid value.
  If there is no such valid value, the functions sets the value of the cell to be 0, and then recursively calls itself
  to work on the previous cell. */
  @tailrec
  private def _backtrack(cell: Cell): Cell = {

    // Cell already at minimum value, previous cell will be incremented
    if (cell.getValue == 1){
      cell.setValue(0)
      _backtrack(_prevValid(cell))

    } else {
      cell.setValue(cell.getValue - 1)

      if(vc.isValid(cell.getY, cell.getX, cell.getValue)){
        cell
      } else {
        _backtrack(cell)
      }
    }

  }

  // Finds the previous changeable cell to the given cell
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

  /* Finds the next cell to the given cell.
  Unlike previous cell the next cell does not need to return a changeable cell,
  as this function is only used in the loop which already is responsible for checking
  whether or not a cell is changeable.*/
  private def _next(cell: Cell) : Cell = {
    if(cell.getX == size - 1){
      cells(cell.getY + 1)(0)
    } else {
      cells(cell.getY)(cell.getX + 1)
    }
  }

}

