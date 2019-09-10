import scala.collection.mutable.ArrayBuffer

class Cell (private val x: Int, private val y: Int) {

  // Value needs to be changed
  private var value = 0
  private var changeable = true
  var neighbours: ArrayBuffer[Cell] = ArrayBuffer[Cell]()

  def setValue(value: Int): Unit = {
    this.value = value
  }

  def setChangeable(changeable: Boolean): Unit = {
    this.changeable = changeable;
  }

  def getNeighbours: ArrayBuffer[Cell] = { neighbours }

  def getValue: Int = { value }
  def getChangeable: Boolean = { changeable }
  def getX: Int = { x }
  def getY: Int = { y }

}
