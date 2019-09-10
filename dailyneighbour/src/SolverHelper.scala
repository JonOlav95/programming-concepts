import scala.util.matching.Regex

class SolverHelper {


  var lines: String = new String

  def parse(filename: String): Int ={

    // Read the filename
    val source = scala.io.Source.fromFile(filename)
    this.lines = try source.mkString finally source.close()

    // Using regex to read and set size of the puzzle
    val pattern = "\\d".r
    val result = pattern.findAllIn(lines).toArray
    var size = result(0).toInt

    // Remove the size line as it is no longer necessary
    this.lines = lines.substring(lines.indexOf("\n") + 1)

    return size
  }


  def createCells(size: Int): Array[Array[Cell]] = {

    var cells = Array.ofDim[Cell](size, size);

    for (i <- 0 until size) {
      for (j <- 0 until size) {
        var cell = new Cell(x = j, y = i);

        if(this.lines(j * 4 + (i * (size * 8 - 2))).isDigit){
          cell.setValue(this.lines(j * 4 + (i * (size * 8 - 2))) - 48)
          cell.setChangeable(false)
        } else {
          cell.setValue(0);
        }

        cells(i)(j) = cell;

        if(i != 0) {
          if(this.lines(j * 4 + (i * (size * 8 - 2)) - (size * 4 - 1)) == 'x'){
            cells(i)(j).neighbours.append(cells(i - 1)(j))
            cells(i - 1)(j).neighbours.append(cells(i)(j))
          }
        }

        if(j != 0){
          if(this.lines(j * 4 - 2 + (i * (size * 8 - 2))) == 'x'){
            cells(i)(j).neighbours.append(cells(i)(j - 1))
            cells(i)(j - 1).neighbours.append(cells(i)(j))
          }
        }



      }
    }

    return cells
  }


  def printPuzzle(size: Int, cells: Array[Array[Cell]]): Unit = {
    print("\n")
    print("\n")

    for (y <- 0 until size) {
      for (x <- 0 until size) {


        if(cells(y)(x).getValue != 0){
          print(cells(y)(x).getValue)
        } else {
          print('_')
        }

        print(" ")

        if(x < size - 1){
          if(cells(y)(x).neighbours.contains(cells(y)(x + 1))){
            print("x")
          } else {
            print(" ")
          }
          print(" ")
        }



      }
      print("\n")

      if(y < size - 1){
        for(i <- 0 until size) {
          if(cells(y)(i).neighbours.contains(cells(y + 1)(i))){
            print("x")
          } else {
            print(" ")
          }
          print("   ")
        }

        print("\n")
      }

    }



  }

}
