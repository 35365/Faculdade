//
//  sudokuStrategies.h
//  projetofinal
//
//  Created by Faculdade on 28/12/2019.
//  Copyright © 2019 Patricia. All rights reserved.
//

#ifndef sudokuStrategies_h
#define sudokuStrategies_h

#include <stdio.h>
#include <stdlib.h>
#include "sudoku.h"
#include "fase2.h"

/**
    Função para criar o vetor de possibilidades de cada célula
 */
MATRIX * create_Vetor_Strategies(MATRIX *pM);

/**
    Função para testar as funções com as estratégias indicadas
 */
void cliente_Strategies(void);

/**
  Função auxiliar para imprimir os vetores de possibilidades
 */
void print_Vetor_Strategies(MATRIX pM);

/**
    Naked Single - Significa que em uma célula específica apenas um dígito permanece possível.
 */
MATRIX * nakedSingles (MATRIX * pM);

/**
 Hidden singles - É quando um número é o único em uma linha, coluna ou bloco inteiro.
 */
MATRIX * hiddenSingles (MATRIX * pM);
//Função auxiliar á hiddenSingles- serve para colocar o número encontrado no seu lugar
MATRIX * placeHiddenSingles (MATRIX * pM, CELL * cLine, int num);

/**
  Naked Pairs/Triples - Se as células contiverem um par ou triplo, nenhuma das outras células nessa área poderá contê-las - portanto, podemos remover os numeros de todas as outras células da área que os tenham como candidatos.
 */
MATRIX * nakedPairs(MATRIX * pM);
// Função auxiliar á nakedPairs- serve para remover um numero do vetor de possibilidades
CELL* removeFromVector(CELL * c, int num, int size);

/**
 Pointing Pairs - Olhando para cada caixa, por sua vez, pode haver duas ou três ocorrências de um número específico. Se esses números estiverem alinhados em uma única linha ou coluna (como um par ou um triplo), sabemos que esse número DEVE ocorrer nessa linha. Portanto, se o número ocorrer em qualquer outro lugar da linha ou coluna fora da caixa EM QUE ESTÃO ALINHADOS, ele poderá ser removido
 */
MATRIX * pointingPairs(MATRIX * pM);
// Função auxiliar á pointingPairs- serve para remover o número do vetor de possibilidades da linha toda onde a célula se encontra
void isInBoxPointingPairs(MATRIX * pM, int num,int startCol,int line);

/**
    Box/Line Reduction - Sua estratégia envolve uma comparação cuidadosa de linhas e colunas com o conteúdo das caixas. Se encontrarmos números em qualquer linha ou coluna agrupados em apenas uma caixa, podemos excluir esses números do restante da caixa.
 */
MATRIX * boxLineReduction(MATRIX * pM);
// Função auxiliar - serve para remover um numero do vetor de possibilidades das células de uma caixa mas que não perteça a uma certa linha
MATRIX * boxLineReduction_BoxRemoval(MATRIX * pM, int num, int startL, int startCol, int line);

/**
        Função para resolver o sudoku com utilização das estratégias
 */
void solveSudokuStrategies(MATRIX * pM);

#endif /* sudokuStrategies_h */
