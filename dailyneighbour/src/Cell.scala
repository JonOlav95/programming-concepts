import scala.collection.mutable.ArrayBuffer

/* The Cell class represents one cell in the daily neighbour puzzle. A puzzle with row/column size n, will resolve
in the creation of (n * n) cells used to solve the puzzle. */

class Cell (private val x: Int, private val y: Int) {

  // Value needs to be changed
  private var value = 0

  // If the value of a cell is 100% certain, e.g. it is one of the initial values, the cell is unchangeable
  private var changeable = true

  // Arraybuffer used to store the neighbours of the cell.
  var neighbours: ArrayBuffer[Cell] = ArrayBuffer[Cell]()

  // Setters
  def setValue(value: Int): Unit = { this.value = value }
  def setChangeable(changeable: Boolean): Unit = { this.changeable = changeable; }

  // Getters
  def getNeighbours: ArrayBuffer[Cell] = { neighbours }
  def getValue: Int = { value }
  def getChangeable: Boolean = { changeable }
  def getX: Int = { x }
  def getY: Int = { y }

}
