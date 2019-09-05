import scala.util.matching.Regex

class Solver (arg: String = null) {

  // Filename of the current file ready to be solved
  var filename: String = arg

  // Size of the puzzle
  var xSize = 0
  var ySize = 0

  // Set up current puzzle
  def setPuzzle(filename: String): Unit = {
    this.filename = filename
  }

  def solvePuzzle(): Unit = {

    if(this.filename == null){
      println("Need to set puzzle")
      return
    }

    // Read the filename
    val source = scala.io.Source.fromFile(this.filename)
    var lines = try source.mkString finally source.close()

    // Using regex to read and set size of the puzzle
    val pattern = "\\d".r
    val result = pattern.findAllIn(lines).toArray
    this.xSize = result(0).toInt
    this.ySize = result(1).toInt

    // Remove the size line as it is no longer necessary
    lines = lines.substring(lines.indexOf("\n") + 1)
    println(lines);

  }

}
