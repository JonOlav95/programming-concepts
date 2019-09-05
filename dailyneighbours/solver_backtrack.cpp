#include <iostream>
#include "solver.h"

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

    this->setNeighbours();

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
            j = size - 1;
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
        for(int n = size; n > 0; n--)
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

