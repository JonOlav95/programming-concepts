
object main {

  def main(args: Array[String]): Unit = {

    val solver = new Solver("puzzle.txt")
    solver.solvePuzzle()

    solver.setPuzzle("puzzle2.txt")
    solver.solvePuzzle()

    solver.setPuzzle("puzzle5.txt")
    solver.solvePuzzle()

  }
}