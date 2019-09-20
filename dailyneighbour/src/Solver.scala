object Solver {

  def solvePuzzle(filename: String): Unit = {

    // Find size of the puzzle and create cells from the filename



   // val size = SolverHelper.parse(filename)
    val size = SolverHelper.parse2(filename)
    val cells = SolverHelper.createCells(size)

    // Print the unsolved puzzle
    SolverHelper.printPuzzle(size, cells)

    val logicOperator = new LogicOperator(cells, size)
    logicOperator.start()

    SolverHelper.printPuzzle(size, cells)

    val backtracker = new Backtracker(cells, size)
    backtracker.start()

    // Print the solved puzzle
    SolverHelper.printPuzzle(size, cells)

  }


}
