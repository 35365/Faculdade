//
// Created by Rita on 28/11/2019.
//

#include "solveSudoku.h"

int isEmpty(int puzzle[][N], int row, int col, int num){

    int i, aux = sqrt(N);

    int rowSqare = (row/aux) * aux;    //células das linhas
    int colSqare = (col/aux) * aux;    //células das colunas

    for(i = 0; i < N; i++){

        //se uma celula tiver um numero, nao faz nada
        if (puzzle[row][i] == num) return 0;
        if (puzzle[i][col] == num) return 0;
        //quadrado. "equação" encontrada na net
        if (puzzle[rowSqare + (i%aux)][colSqare + (i/aux)] == num) return 0;
    }
    return 1;
}

//função recursiva para resolver sudoku
int solveSudoku(int m[][N], int row, int col){

    int i;

    //percorrer sudoku
    if(row < N && col < N) {

        //se uma celula é diferente de zero, está preenchida. então passamos uma celula à frente
        if (m[row][col] != 0) {

            //avançar uma coluna (dentro do tamanho)
            if ((col + 1) < N)
                return solveSudoku(m, row, col + 1);
            //avançar uma linha (dentro do tamanho)
            if ((row + 1) < N)
                return solveSudoku(m, row + 1, 0);
            else
                return 1;
        } else {

            for (i = 1; i <= N; i++) {

                //se a celula está disponivel para ser preenchida
                if (isEmpty(m, row, col,  i)) {

                    m[row][col] = i;

                    if (col < N) {

                        if (solveSudoku(m, row, col))
                            return 1;
                        else m[row][col] = 0;

                    } else if (row < N) {

                        if (solveSudoku(m, row,0))
                            return 1;
                        else m[row][col] = 0;
                    } else return 1;
                }
            }
        }
        return 0;
    }
    else return 1;
}

int main_sudoku(int argc,const char *argv[])
{
    int i, j, aux = sqrt(N);

    //exemplo do enunciado do projeto
    int m[N][N] =    {{1, 0, 3, 4, 0, 6, 8, 0, 0},
                      {8, 7, 0, 0, 0, 0, 0, 0, 0},
                      {0, 5, 6, 0, 0, 0, 0, 2, 3},
                      {0, 0, 8, 0, 1, 0, 0, 4, 0},
                      {9, 0, 0, 5, 3, 4, 0, 0, 8},
                      {0, 4, 0, 0, 9, 0, 7, 0, 0},
                      {3, 6, 0, 0, 0, 0, 5, 1, 0},
                      {0, 0, 0, 0, 0, 0, 0, 9, 2},
                      {0, 0, 5, 7, 0, 1, 3, 0, 0}};

    if(solveSudoku(m, 0, 0))
    {
        printf("\n+-----+-----+-----+\n");
        for(i=1; i<N+1; ++i)
        {
            for(j=1; j<N+1; ++j) printf("|%d", m[i-1][j-1]);
            printf("|\n");
            if (i%aux== 0) printf("+-----+-----+-----+\n");
        }
    }
    else printf("\n\nNO SOLUTION\n\n");

    return 0;
}

void save_Matrix_bin(int m[][N], char *fn) {

    FILE *fp = NULL;
    fp = fopen("Sudoku_bin.bin", "wb");

    if((fp=fopen(fn,"wb")) ==NULL) {
        printf("Erro %s\n", fn);
        return;
    }
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            fprintf(fp, " %d ", m[i][j]);
        }
        fprintf(fp, "\n");
    }
    fclose(fp);
}
