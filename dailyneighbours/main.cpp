#include "solver.h"

int main()
{
    // Solver holds the functionality and data required to solve daily neighbours
    Solver solver{"puzzle3.txt"};

    // Print the unsolved result
    solver.printResult();

    // Solve the puzzle
    solver.solve();

    // Print the solved result
    solver.printResult();

    return 0;
}