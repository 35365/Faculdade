//
//  fase2.h
//  projetofinal
//
//  Created by Faculdade on 24/12/2019.
//  Copyright © 2019 Patricia. All rights reserved.
//

#ifndef fase2_h
#define fase2_h

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

/**
        Estrutura célula de uma matriz
 */
typedef struct cell{
    int l, c, v; // l->linha da célula; c-> coluna da célula; v-> valor
    struct cell *N,*NE,*E,*SE,*S,*SW,*W,*NW; // apontadores cartezianos
    int *p;// vetor de possibilidades
}CELL;

/**
        Estrutura da matriz do sudoku
 */
typedef struct matrix{
    int size;
    struct cell *pfirst;// Apontodor para a primeira célula da matriz
}MATRIX;

/**
        Estrutura onde todas as tabelas sudoku são ligadas por um tipo "fila"
 */
typedef struct qmatrix{
    int size; // tamanho do sudoku guardado
    MATRIX * pM; // Apontador para a matriz
    struct qmatrix * pfirst,* plast,* pnext; // Apontador para a primeira, última e próxima Matriz segundo uma fila
}QMATRIX;

/**
* Inicialização das células da matriz
*/
void init_matrix(MATRIX *pMX, int size);

/**
* Libertação da memória dinamica das células da matrix
* A Matriz fica vazia e vai ser libertada a sua memória posteriormente
*/
void free_matrix(MATRIX * pMX);

/**
 Função para imprimir a matriz
 */
void printMatrixF2(MATRIX m);

/**
 Função para testar as outras funções da segunda fase
 */
void clientSudoku(void);
/**
  Função para guardar em ficheiro de texto uma matriz
 */
void saveMatrixtxt(MATRIX m, char *fn);

/**
 Função para ler de um ficheiro de texto para uma matriz
*/
MATRIX * readMatrixtxt(char *fn);

/**
  Função para inicializar uma fila com um determinado número de matrizes
 */
QMATRIX * initQueueMatrix(int sizeQM);

/**
   Libertação da memória dinamica da Matriz e da fila de matrizes
 */
void freeQueueMatrix(QMATRIX * pQM);

/**
  *Função para inserir uma matriz na fila de matrizes
  *Inserção á cauda
 */
void enQueueMatrix (QMATRIX * pfirst, MATRIX *m);

/**
  *Função para retirar uma Matriz da fila de matrizes libertando a memória
  *Remoção á cabeça
 */
QMATRIX *deQueueMatrix(QMATRIX * pMQ);

/**
    Função para ler uma fila de matrizes a partir de um ficheiro de texto
 */
QMATRIX *readQueueMatrix(char *fn);

/**
    Função para gravar num ficheiro binário a fila de matrizes
 */
void writeQueueMatrixBin(QMATRIX pQM,char *fn);

/**
    Ler a partir de um ficheiro binário para a fila de matrizes
 */
QMATRIX *readQueueMatrixBin(char *fn);

/**
    Imprimir uma fila de Matrizes
 */
void printQueueMatrix(QMATRIX pM);

/**
    Cliente para testar a diferença no tempo de resolução com e sem as estratégias
 */
void clientTime(MATRIX * pM);

#endif /* fase2_h */
