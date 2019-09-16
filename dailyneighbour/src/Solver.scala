
class Solver (arg: String = null) {

  // Filename of the current file ready to be solved
  var filename: String = arg

  // Set up current puzzle
  def setPuzzle(filename: String): Unit = {
    this.filename = filename
  }

  def solvePuzzle(): Unit = {

    // Make sure a puzzle is chosen either through the constructor or by the @setPuzzle function
    if(this.filename == null){
      println("Need to set puzzle")
      return
    }

    val solverHelper = new SolverHelper();

    // Find size of the puzzle and create cells from the filename
    val size = solverHelper.parse(this.filename)
    val cells = solverHelper.createCells(size)

    // Print the unsolved puzzle
    solverHelper.printPuzzle(size, cells)

    val logicOperator = new LogicOperator(cells, size)
    logicOperator.logic()

    solverHelper.printPuzzle(size, cells)

    val backtracker = new Backtracker(cells, size);
    backtracker.start()

    // Print the solved puzzle
    solverHelper.printPuzzle(size, cells)
  }








}
