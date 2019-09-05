#include <iostream>
#include <algorithm>
#include <fstream>
#include <string>
#include <sstream>
#include "solver.h"

Solver::Solver(const std::string& filename)
{
    this->readFile(filename);
    this->initSize();
    this->initCells();
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
            cell->xPos = j;
            cell->yPos = i;
            cells[i].emplace_back(cell);
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

void Solver::printResult()
{

    for(int i = 0; i < puzzleString.size(); i++)
    {

        for(int j = 0; j < puzzleString[i].size(); j++)
        {

            if(i % 2 == 0)
            {

                if(j % 4 == 0)
                {
                    std::cout << cells[i / 2][j / 4]->value;
                }
                else if(cells[i / 2][j / 4]->xPos != (size - 1))
                {
                    if(puzzleString[i][j] == 'x')
                    {
                        std::cout << "-";
                    }
                    else
                    {
                        std::cout << " ";
                    }
                }
            }
            else
            {
                if(puzzleString[i][j] == 'x')
                {
                    std::cout << "|";
                }
                else
                {
                    std::cout << " ";
                }
            }
        }

        std::cout << "\n";

    }


    std::cout << "\n";
    for(int i = 0; i < puzzleString[0].size(); i++)
    {
        std::cout << "@";
    }

    std::cout << "\n";
    std::cout << "\n";

}

void Solver::readFile(std::string filename)
{
    std::string line;
    std::ifstream myfile (filename);

    if (myfile.is_open())
    {
        int i = 0;
        while ( getline (myfile,line) )
        {
            std::string str = (line + "\n");
            puzzleString.push_back(str);
            i++;
        }
        myfile.close();
    }

    else std::cout << "Unable to open file";

    std::string firstLine = puzzleString[0];

    std::stringstream ss;

    /* Storing the whole string into string stream */
    ss << firstLine;

    /* Running loop till the end of the stream */
    std::string temp;
    int found{};
    while (!ss.eof()) {

        /* extracting word by word from stream */
        ss >> temp;

        /* Checking the given word is integer or not */
        if (std::stringstream(temp) >> found)
        {}

        /* To save from space at the end of string */
        temp = "";
    }

    this->size = found;

    std::reverse(puzzleString.begin(),puzzleString.end()); // first becomes last, reverses the vector
    puzzleString.pop_back(); // pop last
    std::reverse(puzzleString.begin(),puzzleString.end());

    for(auto& str : puzzleString)
    {
        str.erase(std::remove(str.begin(), str.end(), '\n'), str.end());
    }

}

