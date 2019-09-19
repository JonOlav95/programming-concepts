import scala.collection.mutable.ArrayBuffer

class ValidChecker(private var cells: Array[Array[Cell]], private val size: Int) {

  private def _adjacentNoneNeighbours(cell: Cell): ArrayBuffer[Int] = {

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

    exclude
  }

  // Checks if the value at the given position follows the daily neighbours puzzle constraints
  def isValid(y: Int, x: Int, value: Int): Boolean = {


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


    val exclusion = _adjacentNoneNeighbours(cells(y)(x))

    if(exclusion.contains(value))
      return false


    true
  }


}
