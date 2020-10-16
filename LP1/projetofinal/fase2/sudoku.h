//
//  sudoku.h
//  projetofinal
//
//  Created by Faculdade on 24/12/2019.
//  Copyright © 2019 Patricia. All rights reserved.
//

#ifndef sudoku_h
#define sudoku_h

#include "fase2.h"
#include <stdio.h>
#include <MATH.h>
#include <stdbool.h>

/**
 Função que retorna 1 se for seguro inserir o número numa determinada linha e coluna
 */
int ifSafe(MATRIX *pM, int row, int col, int num);

/**
Função que retorna 1 se já existir esse número na linha
*/
int usedInRow(MATRIX *pM, int row, int col, int num);

/**
Função que retorna 1 se já existir esse número  na coluna
*/
int usedInCol(MATRIX *pM, int row, int col, int num);

/**
Função que retorna 1 se já existir esse número no quadrado
 */
int usedInBox(MATRIX *pM, int row, int col, int num);

/**
Função que retorna 1 se já existir esse número na diagonal principal
 */
int usedInDiagonal(MATRIX *pM, int row, int col, int num);

/**
Função que retorna 1 se já existir esse número na diagonal secundária
 */
int usedInDiagonal2(MATRIX *pM, int row, int col, int num);

/**
    Função que retorna o apontador para um célula com o valor 0, isto é, candidata a inserir um número
 */
CELL * findCellEmpty(MATRIX pM);

/**
 Função para resolver o sudoku com brute force
 */
bool solveSudokuRec(MATRIX *pM);

#endif /* sudoku_h */
