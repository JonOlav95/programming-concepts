#ifndef DAILYNEIGHBOURS_SOLVER_H
#define DAILYNEIGHBOURS_SOLVER_H

#include <vector>
#include <string>
#include "cell.h"

class Solver
{
private:

    std::vector<std::vector<Cell*>> cells{};
    std::vector<std::string> puzzleString{};
    int size{};
    void readFile(std::string filename);
    void initSize();
    bool isValid(int x, int y, int val);
    int deduct(int y, int x, int back);
    void stepBack(int& i, int& j, int back);
    int backtrackStep(int i, int j);
    void setNeighbours();
    void initCells();


public:
    explicit Solver(const std::string& filename);
    void printResult();
    void solve();

};


#endif //DAILYNEIGHBOURS_SOLVER_H
