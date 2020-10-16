//
// Created by Faculdade on 28/11/2019.
//

#ifndef PROJETO_SOLVESUDOKU_H
#define PROJETO_SOLVESUDOKU_H

#include "fase1.h"
#include <stdbool.h>
#define N 16
int isEmpty(int puzzle[][N], int row, int col, int num);
int solveSudoku(int m[][N], int row, int col);
int main_sudoku(int argc,const char *argv[]);
void save_Matrix_bin(int m[][N], char *fn);

#endif //PROJETO_SOLVESUDOKU_H
