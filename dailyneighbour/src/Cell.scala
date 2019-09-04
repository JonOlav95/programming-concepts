class Cell (val x: Int, val y: Int) {

  // Value needs to be changed
  var value = 0
  var changeable = true

  var neighbours: List[Cell] = List()

}
