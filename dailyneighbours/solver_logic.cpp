#include "solver.h"
#include <algorithm>
#include <vector>


bool Solver::checkCloseNeighbour(int i, int j, bool find)
{

    for(auto& neighbour : cells[i][j]->neighbours)
    {
        if(neighbour->value != 0)
            continue;

        if(neighbour->yPos == cells[i][j]->yPos)
        {

            int value = cells[i][j]->value;
            if(std::any_of(cells[i].begin(), cells[i].end(), [value](Cell*& cell){ return cell->value == (value - 1); }))
            {
                neighbour->value = value + 1;
                neighbour->changeable = false;
                find = true;
            }
            else if(std::any_of(cells[i].begin(), cells[i].end(), [value](Cell*& cell){ return cell->value == (value + 1); }))
            {
                neighbour->value = value - 1;
                neighbour->changeable = false;
                find = true;
            }

        }

        if(neighbour->xPos == cells[i][j]->xPos)
        {

            int value = cells[i][j]->value;
            if(std::any_of(cells.begin(), cells.end(), [j, value](std::vector<Cell*>& cell){ return cell[j]->value == (value - 1); }))
            {
                neighbour->value = value + 1;
                neighbour->changeable = false;
                find = true;
            }
            else if(std::any_of(cells.begin(), cells.end(), [j, value](std::vector<Cell*>& cell){ return cell[j]->value == (value + 1); }))
            {
                neighbour->value = value - 1;
                neighbour->changeable = false;
                find = true;
            }
        }
    }

    return find;

}


bool Solver::checkEndPoints(int i, int j, bool find)
{

    if(cells[i][j]->value == 1)
    {
        for(auto& neighbour : cells[i][j]->neighbours)
        {

            if(neighbour->value == 1)
            {
                neighbour->setValue(2);
                neighbour->changeable = false;
                find = true;
            }

        }
    }
    else if(cells[i][j]->value == size)
    {
        for(auto& neighbour : cells[i][j]->neighbours)
        {
            if(neighbour->value == 0)
            {
                neighbour->setValue(size - 1);
                neighbour->changeable = false;
                find = true;
            }
        }
    }

    return find;
}

void Solver::setNeighbours()
{

    bool found = false;

    for(int i = 0; i < size; i++)
    {
        for (int j = 0; j < size; j++)
        {

            found = checkEndPoints(i, j, found);

            if(cells[i][j]->value != 0 && cells[i][j]->value != 1 && cells[i][j]->value != size)
            {
                found = checkCloseNeighbour(i, j, found);
            }

        }
    }

    if(found)
    {
        this->printResult();
        setNeighbours();
    }

}