//
// Created by Patricia on 20/11/2019.
//

#ifndef PROJETO_FASE1_H
#define PROJETO_FASE1_H
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <time.h>
#include "solveSudoku.h"
#include <stdbool.h>
#define N 16

#include "backtracking_search.h"

int main_fase1(int argc,const char *argv[]);
void create_Matrix(int m[][N]);
void print_Matrix(int m[][N]);
void save_Matrix_txt(int m[][N], char *fn);
void read_Matrix_txt(int m[][N], char *fn);
bool solve_sudoku2(int m[N][N]);

#endif //PROJETO_FASE1_H
