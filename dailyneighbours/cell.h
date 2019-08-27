#ifndef DAILYNEIGHBOURS_CELL_H
#define DAILYNEIGHBOURS_CELL_H

#include <vector>

class Cell
{
public:

    int xPos{};
    int yPos{};
    int value{};

    bool changeable{};
    std::vector<Cell*> neighbours{};

    Cell(int yPos, int xPos, bool changeable) :
            xPos(xPos), yPos(yPos),
            changeable(changeable)
    {}

    void setValue(int val)
    {
        this->value = val;
    }

    void addNeighbour(Cell* neighbour)
    {
        neighbours.push_back(neighbour);
    }



};

#endif
