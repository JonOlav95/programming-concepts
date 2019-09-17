object Solver {

  def solvePuzzle(filename: String): Unit = {

    // Make sure a puzzle is chosen either through the constructor or by the @setPuzzle function
    if (filename == null) {
      println("Need to set puzzle")
      return
    }

    // Find size of the puzzle and create cells from the filename
    val size = SolverHelper.parse(filename)
    val cells = SolverHelper.createCells(size)

    // Print the unsolved puzzle
    SolverHelper.printPuzzle(size, cells)

    val logicOperator = new LogicOperator(cells, size)
    logicOperator.logic()

    println("\n\n Logic Finished")
    SolverHelper.printPuzzle(size, cells)


    val backtracker = new Backtracker(cells, size);
    backtracker.start()




    // Print the solved puzzle
    SolverHelper.printPuzzle(size, cells)

  }


}
