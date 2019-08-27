#include <iostream>
#include "solver.h"

Solver::Solver(const std::string& filename)
{
    this->readFile(filename);
    this->initSize();
    this->initCells();
    this->setNeighbours();
}

void Solver::initSize()
{

    cells.resize(size);
    for(int i = 0; i < size; i++)
    {
        //cells[i].resize(size);
        for(int j = 0; j < size; j++)
        {
            Cell* cell = new Cell(i, j, true);
            cell->setValue(0);
            cells[i].emplace_back(cell);
        }

    }
}



void Solver::setNeighbours()
{
    for(int i = 0; i < size; i++)
    {

        for (int j = 0; j < size; j++)
        {
            if(cells[i][j]->value == 1)
            {
                for(auto& neighbour : cells[i][j]->neighbours)
                {
                    neighbour->setValue(2);
                    neighbour->changeable = false;
                }
            }
            else if(cells[i][j]->value == 4)
            {
                for(auto& neighbour : cells[i][j]->neighbours)
                {
                    neighbour->setValue(3);
                    neighbour->changeable = false;
                }

            }


        }
    }
}


void Solver::initCells()
{

    for(int i = 0; i < size; i++)
    {

        for(int j = 0; j < size; j++)
        {
            if(isdigit(puzzleString[i * 2][j * 4]))
            {
                cells[i][j]->setValue(std::atoi(&puzzleString[i * 2][j * 4]));
                cells[i][j]->changeable = false;
            }


            if(cells[i][j]->xPos != 0)
            {
                if(puzzleString[i * 2][j * 4 - 2] == 'x')
                {
                    cells[i][j]->addNeighbour(cells[i][j - 1]);
                    cells[i][j - 1]->addNeighbour(cells[i][j]);
                }
            }

            if(cells[i][j]->yPos != 0)
            {
                if(puzzleString[i * 2 - 1][j * 4] == 'x')
                {
                    cells[i][j]->addNeighbour(cells[i - 1][j]);
                    cells[i - 1][j]->addNeighbour(cells[i][j]);
                }
            }
        }


    }

}


bool Solver::isValid(int x, int y, int val)
{

    for(int i = 0; i < size; i++)
    {
        if(y != i)
        {
            if(cells[i][x]->value == val)
            {
                return false;
            }
        }

        if(x != i)
        {
            if(cells[y][i]->value == val)
            {
                return false;
            }
        }
    }


    for(auto& neighbour : cells[y][x]->neighbours)
    {

        for(int i = 1; i < size + 1; i++)
        {
            if(neighbour->value == i)
            {
                if(val != neighbour->value - 1 && val != neighbour->value + 1)
                {
                    return false;
                }
            }
        }
    }

    return true;

}

int Solver::deduct(int y, int x, int back)
{

    auto vel = cells[y][x]->value;

    if(!cells[y][x]->changeable)
    {
        if(x == 0)
        {
            return deduct( y - 1, size - 1, back += 1);
        }
        else
        {
            return deduct(y, x - 1, back += 1);
        }
    }

    cells[y][x]->value--;

    if(cells[y][x]->value == 0)
    {

        if(x == 0 && y == 0)
        {
            cells[x][y]--;
            std::cout << "first number crash";
        }
        else if(x == 0)
        {
            return deduct( y - 1, size - 1, back += 1);
        }
        else
        {
            return deduct(y, x - 1, back += 1);
        }
    }

    if(!isValid(x, y, cells[y][x]->value))
    {
        return deduct(y, x, back);
    }

    return back;


}


// Main function used to iterate through all the cells and solve the puzzle
// Uses backtracking
void Solver::solve()
{

    for(int i = 0; i < size; i++)
    {

        for (int j = 0; j < size; j++)
        {

            // Cell cannot be changed, so it will be skipped
            if(!cells[i][j]->changeable)
                continue;

            // Find a value for the current marked cell, returns amount of backtracks made
            int back = backtrackStep(i, j);

            // Apply the backtracks to the appropriate cell
            stepBack(i, j, back);

        }
    }
}

void Solver::stepBack(int& i, int& j, int back) {

    for(int l = back; l > 0; l--)
    {

        if(j == 0)
        {
            i--;
            j = 3;
        }
        else
        {
            j--;
        }

    }
}

// Works on the next step of the backtrack algorithm. Can enter a recursion of the deduct function
// Returns the amount of backtracks made
int Solver::backtrackStep(int i, int j) {
    bool valid = false;
    int back = 0;

    if(cells[i][j]->value == 0)
    {
        for(int n = 4; n > 0; n--)
        {
            if(isValid(j, i, n))
            {
                cells[i][j]->value = n;
                valid = true;
                break;
            }
        }

        // No digit fits the current cell, the previous cell is deducted by one
        if(!valid)
        {
            if(j == 0)
            {
                // Deduct from the previous position
                back = deduct(i - 1, size - 1, 1);
            }
            else
            {
                // Deduct from the previous position edge case
                back = deduct(i, j - 1, 1);
            }
        }

    }
    else
    {
        // Deduct from the current position
        back = deduct(i, j, 0);
    }

    return back;
}


