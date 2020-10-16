//
// Created by Rita Vieira Almeida on 05/11/2019.
//

#ifndef PROJETO_BACKTRACKING_SEARCH_H
#define PROJETO_BACKTRACKING_SEARCH_H

#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include "../fase1/fase1.h"
// UNASSIGNED is used for empty cells in sudoku grid
#define UNASSIGNED 0

// N is used for the size of Sudoku grid. Size will be NxN
#define N 16

// Checks whether it will be legal to assign num to the given row, col
bool isSafe(int grid[N][N], int row, int col, int num);
bool SolveSudoku(int grid[N][N]);
// This function finds an entry in grid that is still unassigned
bool FindUnassignedLocation(int grid[N][N], int (*row), int (*col));
bool UsedInRow(int grid[N][N], int row, int num);
bool UsedInCol(int grid[N][N], int col, int num);
bool UsedInBox(int grid[N][N], int boxStartRow, int boxStartCol, int num);
bool isSafe(int grid[N][N], int row, int col, int num);
void printGrid(int grid[N][N]);
int main_bts(int argc,const char *argv[]);

#endif //PROJETO_BACKTRACKING_SEARCH_H
