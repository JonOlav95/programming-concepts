#include <iostream>
#include <algorithm>
#include <fstream>
#include <string>
#include <sstream>
#include "solver.h"

void Solver::printResult()
{
    for(int i = 0; i < size; i++)
    {

        for (int j = 0; j < size; j++)
        {
            std::cout << cells[i][j]->value << " ";
        }

        std::cout << "\n";
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