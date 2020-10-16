//
//
// Created by Patricia on 20/11/2019.
//

#include "fase1.h"
int main_fase1(int argc,const char *argv[]){
    int  m[N][N];
    /*int m[][9]={{1, 0, 3, 4, 0, 6, 8, 0, 0},
                {8, 7, 0, 0, 0, 0, 0, 0, 0},
                {0, 5, 6, 0, 0, 0, 0, 2, 3},
                {0, 0, 8, 0, 1, 0, 0, 4, 0},
                {9, 0, 0, 5, 3, 4, 0, 0, 8},
                {0, 4, 0, 0, 9, 0, 7, 0, 0},
                {3, 6, 0, 0, 0, 0, 5, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 9, 2},
                {0, 0, 5, 7, 0, 1, 3, 0, 0}
    };
    save_Matrix_txt(m,"/Users/faculdade/Documents/LP/text1.txt");*/
    read_Matrix_txt(m,"/Users/faculdade/Documents/LP/SUDOKU16.txt");
    //create_Matrix(m);
    //SolveSudoku(m);
    //print_Matrix(m);
    solveSudoku(m,0,0);
    print_Matrix(m);
    return 0;
}

void create_Matrix(int m[][N]){
    //srand(time(NULL));
    int k=0,num=0,c=0,l=0;
    for(int i=0; i < N; i++){
        for(int j=0; j < N; j++)
            m[i][j]=0;
    }
    while (k<(N*N*0.20)){
        num=rand()%N +1;
        c=rand()%N;
        l=rand()%N;
        if(isSafe(m,l,c,num)) {
            m[l][c] = num;
            k++;
        }
    }
    print_Matrix(m);
    if (SolveSudoku(m) == true)
        print_Matrix(m);
    else {
        print_Matrix(m);
        create_Matrix(m);
    }
}

void print_Matrix(int m[][N]){
    printf("Sodoku %dx%d\n", N, N);
    for(int i=0; i < N; i++) {
        if (i % ((int) sqrt(N)) == 0) //separar os quadrados interiores
            printf("\n");
        for(int j=0; j < N; j++) {
            if(j%((int)sqrt(N)) == 0)// linha a separar os quadrados interiores
                printf("|");
            printf(" %d ", m[i][j]);
        }
        printf("|\n");
    }
}

void save_Matrix_txt(int m[][N], char *fn){
    FILE *fp=NULL;
    if ((fp=fopen(fn,"w"))==NULL){
        printf("Erro %s\n",fn);
        return;
    }
    for (int i=0; i < N; i++) {
        for (int j = 0; j < N; j++)
            fprintf(fp, " %d ", m[i][j]);
        fprintf(fp,"\n");
    }
    fclose(fp);
}

void read_Matrix_txt(int m[][N], char *fn){
    FILE *fp;
    fp=fopen(fn,"r");
    if(fp==NULL){
        printf("Erro na abertura: %s\n",fn);
        return;
    }
    for (int i=0; i < N; i++) {
        for (int j = 0; j < N; j++)
            fscanf(fp,"%d",&(m[i][j]));
    }
    fclose(fp);

}
