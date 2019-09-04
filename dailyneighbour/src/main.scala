import scala.util.matching.Regex

object main {



  def main(args: Array[String]): Unit = {

    val solver = new Solver()
    solver.setPuzzle("puzzle.txt")
    solver.solvePuzzle()

    var cell = new Cell(3, 4)


  }
}