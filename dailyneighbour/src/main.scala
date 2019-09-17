
object main {

  def main(args: Array[String]): Unit = {


    val t1 = System.nanoTime

    Solver.solvePuzzle("puzzle.txt")

    val duration = (System.nanoTime - t1) / 1e9d
    println("TIME: " + duration)

  }
}