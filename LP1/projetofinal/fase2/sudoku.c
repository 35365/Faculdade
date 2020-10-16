//
//  sudoku.c
//  projetofinal
//
//  Created by Faculdade on 24/12/2019.
//  Copyright © 2019 Patricia. All rights reserved.
//

#include "sudoku.h"

int ifSafe(MATRIX *pM, int row, int col, int num){
    if(usedInRow(pM, row, col, num)==0
       && usedInCol(pM, row, col, num)==0
       && usedInBox(pM, row, col, num)==0
       && usedInDiagonal(pM, row, col, num)==0
       && usedInDiagonal2(pM, row, col, num)==0)
        return 1;
    else
        return 0;
}

int usedInRow(MATRIX *pM, int row, int col, int num){
    CELL * c = pM->pfirst;
    for(int i = 0,j=0; (i < pM->size || j < pM->size )&& c!=NULL; i++){
           if(c->l == row){
               if(c->v == num) return 1; // se o numero já aparece na linha retorna 1
               else {
                   c= c->E; // se não for o numero continua a procura na linha
                   j++;
               }
           }
           else
               c=c->S; // Se a linha não é a certa passa para a proxima
       }
    return 0;
}

int usedInCol(MATRIX *pM, int row, int col, int num){
    CELL *c = pM->pfirst;
    for(int i = 0,j=0; (i < pM->size || j<pM->size) && c!=NULL; i++){
           if(c->c == col){
               if(c->v == num) return 1; // se o numero já aparece na coluna retorna 1
               else c= c->S; // se não for o numero continua a procura na coluna
               j++;
           }
           else
               c=c->E; // Se a coluna não é a certa passa para a proxima
       }
    return 0;
}

int usedInBox(MATRIX *pM, int row, int col, int num){
    int aux;
    aux = (int)sqrt(pM->size);
    int startRow = row - row%((int)sqrt(pM->size)), // linha da primeira celula do quadrado
        startCol = col - col%((int)sqrt(pM->size));// coluna da primeira celula do quadrado
    CELL *c = pM->pfirst;
    CELL *a; // celula que guarda o apontador da primeira celula da linha que estamos
    for(int i = 0,j=0; (i < pM->size || j< pM->size) && c!=NULL; i++){
    
        if(c->l == i + startRow){
            if(c->c == i + startCol){
                a = c;
                
                for(int line =a->l;line< startRow +aux ;line++){
                    for(int j=a->c;j< startCol +aux;j++){
                        if(c->v == num) return 1;
                        else c=c->E;
                    }
                    c=a->S;
                    a=a->S;
                }
            }
            else {
                j++;
                i--;
                c=c->E;// linha certa mas coluna errada anda se para a direita(Este)
            }
        }
        else c = c->S;// linha errada anda se para baixo(Sul)
    }
    return 0;
}

int usedInDiagonal(MATRIX *pM, int row, int col, int num){
    if(row!=col) return 0; //Se o sítio onde querem inserir o número não estiver na diagonal
    CELL *c = pM->pfirst;
    while (c!=NULL) {
        if(c->v == num)
            return 1;
        else
            c=c->SE;
    }
    return 0;
}

int usedInDiagonal2(MATRIX *pM, int row, int col, int num){
    if(row + col != pM->size -1)//Se o sítio onde querem inserir o número não estiver na diagonal
        return 0;
    CELL *c = pM->pfirst;
    while(c!=NULL){
        if(c->S ==NULL) break;
        else c=c->S;
    }
    while (c!=NULL) {
        if(c->v == num)
            return 1;
        else
            c=c->NE;
    }
    return 0;
}

CELL * findCellEmpty(MATRIX pM){
    CELL * c= pM.pfirst, *aux=c;
    for ( int i=0;i<pM.size && c!=NULL ;i++){
        for( int j=0;j<pM.size && c!=NULL;j++){
            if(c->v == 0)
                return c;
            else
                c= c->E;
        }
        aux= aux->S;
        c=aux;
    }
    return NULL;
}

bool solveSudokuRec(MATRIX *pM){
    CELL * c= findCellEmpty(*pM);
    if(c==NULL) return true; // Se não houver células vazias então está resolvido
    int s;
    for(int num=1; num<=pM->size && c!=NULL;num ++){
        s=ifSafe(pM, c->l, c->c, num);
        if(s==1){
            c->v= num;
            if(solveSudokuRec(pM))
                return true;
            c->v = 0;
        }
        //printMatrixF2(*pM);
    }
    return false;
}

