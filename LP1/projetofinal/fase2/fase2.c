//
//  fase2.c
//  projetofinal
//
//  Created by Faculdade on 24/12/2019.
//  Copyright © 2019 Patricia. All rights reserved.
//

#include "fase2.h"
#include "sudoku.h"
#include "sudokuStrategies.h"

void init_matrix(MATRIX *pMX, int size){
    if (size==0){
        printf("Dimensões inválidas\n");
        return;
    }
    pMX->size=size;
    CELL *auxS=NULL;
    CELL *auxE=NULL;
    for (int i=0; i<size; i++){
        for (int j=0; j<size; j++){
            CELL *temp=(CELL *)malloc(sizeof(CELL));
            temp->l=i;
            temp->c=j;
            temp->v=0;
            temp->N=NULL;
            temp->NE=NULL;
            temp->E=NULL;
            temp->SE=NULL;
            temp->S=NULL;
            temp->SW=NULL;
            temp->W=NULL;
            temp->NW=NULL;
            temp->p=(int*)malloc(sizeof(int)*size);
            for(int i=0;i<size;i++){
                temp->p[i]=0;
            }
            // Primeira célula
            if (i==0 && j==0){
                pMX->pfirst=temp;
                auxS=pMX->pfirst;
                auxE=pMX->pfirst;
            }
            // Células na primeira coluna
            if (i>0 && j==0){
                temp->N=auxS;
                auxS->S=temp;
                temp->NE=auxS->E;
                if (auxS->E!=NULL){
                    auxS->E->SW=temp;
                }
                auxS=auxS->S;
                auxE=auxS;
            }
            // Restantes células
            if (j>0){
                temp->W=auxE;
                auxE->E=temp;
                temp->NW=auxE->N;
                if (auxE->N!=NULL){
                    auxE->N->SE=temp;
                }
                temp->N=auxE->NE;
                if (auxE->NE!=NULL){
                    auxE->NE->S=temp;
                    temp->NE=auxE->NE->E;
                    if(auxE->NE->E!=NULL){
                        auxE->NE->E->SW=temp;
                    }
                }
                auxE=auxE->E;
            }
        }
    }
    
}

void free_matrix(MATRIX * pMX){
    CELL * current=pMX->pfirst;
    CELL * nextright=NULL;
    CELL * nextdown=NULL;
    if(current!=NULL){
        nextright=current->E;
        nextdown=current->S;
    }
    while (current!=NULL){
        free(current->p);
        free(current);
        current=nextright;
        if (current==NULL){
            current=nextdown;
            if (current!=NULL){
                nextdown=current->S;
                nextright=current->E;
            }
        }
        else{
            nextright=current->E;
        }
    }
    pMX->pfirst=NULL;
    pMX->size=0;
    pMX->size=0;
}

void printMatrixF2(MATRIX m){
    CELL *c = m.pfirst, *aux= c;
    if(m.size==0)
        return;
    printf("MATRIX(%d,%d):\n",m.size,m.size);
    while (c!=NULL) {
        
        if(c->l % ((int) sqrt(m.size)) == 0) //separar os quadrados interiores
            printf("\n");
        
        while (c!=NULL){
            
            if(c->c %((int)sqrt(m.size)) == 0)// linha a separar os quadrados interiores
            printf("|");
            
            printf("%d  ", c->v);
            c=c->E;
        }
        printf("\n");
        aux=aux->S;
        c=aux;
    }
}

void saveMatrixtxt(MATRIX m, char *fn){
    FILE *fp=NULL;
    CELL *c = m.pfirst, *aux = c;
    if ((fp=fopen(fn,"w"))==NULL){
        printf("Erro %s\n",fn);
        return;
    }
    fprintf(fp, "Matriz: %d\n",m.size);
    while (c!=NULL) {
        while (c!=NULL){
            fprintf(fp,"%d ", c->v);
            c=c->E;
        }
        fprintf(fp,"\n");
        aux=aux->S;
        c=aux;
    }
    fprintf(fp, "Matriz: 0\n");
    fclose(fp);
}

MATRIX * readMatrixtxt(char *fn){
    MATRIX *pM=(MATRIX*)malloc(sizeof(MATRIX));
    int size;
    FILE *fp;
    fp=fopen(fn,"r");
    if(fp==NULL){
        printf("Erro na abertura: %s\n",fn);
        return pM;
    }
    fscanf(fp, "%*s %d",&size);
    init_matrix(pM, size);
    pM->size =size;
    CELL *c = pM->pfirst, *aux = c;
    while (c!=NULL) {
        while (c!=NULL){
            fscanf(fp,"%d", &(c->v));
            c=c->E;
        }
        aux=aux->S;
        c=aux;
    }
    fclose(fp);
    return pM;
}

QMATRIX * initQueueMatrix(int sizeQM){
    MATRIX * m=(MATRIX*)malloc(sizeof(MATRIX));
    QMATRIX * firstM = (QMATRIX*)malloc(sizeof(QMATRIX));
    firstM->pfirst=firstM;
    firstM->pM=m;
    firstM->size= 0;
    if(sizeQM == 1){ // Se for só uma matriz na fila o apontador para o primeiro é igual ao último e o proximo é null
        firstM->pnext = NULL;
        firstM->plast = firstM;
        return firstM;
    }
    QMATRIX * lastM = (QMATRIX*)malloc(sizeof(QMATRIX));
    QMATRIX * nextM = (QMATRIX*)malloc(sizeof(QMATRIX));
    QMATRIX *current=nextM;
    firstM->plast=lastM;
    firstM->pnext=nextM;
    int i;
    for(i=1;i<sizeQM -1;i++){ // Restantes matrizes são inseridas no fim
        m=(MATRIX*)malloc(sizeof(MATRIX));
        current->pfirst= firstM;
        current->plast = lastM;
        current->pM = m;
        current->size = 0;
        nextM=(QMATRIX*)malloc(sizeof(QMATRIX));
        current->pnext=nextM;
        current= nextM;
    }
    current->pnext=lastM;
    lastM->pfirst=firstM;
    lastM->plast=lastM;
    lastM->pM=(MATRIX*)malloc(sizeof(MATRIX));
    lastM->size= 0;
    return firstM;
}

void freeQueueMatrix(QMATRIX * pQM){
    QMATRIX *aux= pQM->pfirst;
    QMATRIX *current = NULL;
    while (aux!=NULL) {
        if(pQM == pQM->pfirst){// Se aparecer no inicio da fila
            aux= aux->pnext;
            aux->pfirst = aux;
            free_matrix(pQM->pM);
            free(pQM);
            break;
        }
        if(aux->pnext==pQM && pQM!=pQM->plast){ // Se aparecer no meio da fila
            aux->pnext = aux->pnext->pnext;
            free_matrix(pQM->pM);
            free(pQM);
            break;
        }
        if(aux->pnext==pQM && pQM==pQM->plast){ // Se aparecer no fim da fila
            aux->pnext = NULL;
            free_matrix(pQM->pM);
            free(pQM);
            break;
        }
        else aux = aux->pnext;
    }
    if(aux->pnext==NULL){// se aux for o ultimo
        current= aux->pfirst;
        while(current!=NULL){
            current->plast = aux;
            current= current->pnext;
        }
    }
    else if(aux->pfirst==aux){ // se aux for o primeiro
        current= aux->pnext;
        while(current!=NULL){
            current->pfirst = aux;
            current = current->pnext;
        }
    }
}

void enQueueMatrix (QMATRIX * pfirst, MATRIX *m){
    QMATRIX * pCurrent = pfirst;
    if(pfirst->size==0 && pfirst== pfirst->pfirst){ // se está vazia e é o primeiro
        pfirst->pM= m;// assumo que foi feito um init e o resto dos parametros da estrutura foram inicializados e configurados
        pfirst->size = pCurrent->pM->size;
        return;
    }
    pCurrent = pCurrent->pnext;
    while (pCurrent!=NULL) {// se estiver vazia a fila de matrizes
        if(pCurrent->size==0){
            pCurrent->pM=m;
            pCurrent->size = m->size;
            return;
        }
        else pCurrent = pCurrent->pnext;
    }
    // Se está cheia insere se no fim
    pCurrent= pfirst;
    QMATRIX * pAux = initQueueMatrix(1);
    pAux->pfirst=pCurrent;
    pAux->pM= m;
    pAux->size = m->size;
    while (pCurrent!=NULL) {
        pCurrent->plast= pAux;
        if(pCurrent->pnext==NULL)
            pCurrent->pnext= pAux;
    }
}

QMATRIX *deQueueMatrix(QMATRIX * pMQ){
    QMATRIX * pFirst = pMQ->pnext, *current = pFirst;
    while (current!=NULL) { // Vai se a todas as matrizes da fila para mudar o apontador para o primeiro para o que passa a ser o primeiro
        current->pfirst=pFirst;
        current = current->pnext;
    }
    freeQueueMatrix(pMQ);
    return pFirst;
}

QMATRIX *readQueueMatrix(char *fn){
    QMATRIX *pQM=NULL;
    MATRIX *m=NULL;
    int i=0,size=0;
    FILE *fp;
    fp=fopen(fn,"r");
    if(fp==NULL){
        printf("Erro na abertura: %s\n",fn);
        return pQM;
    }
    fscanf(fp, "%*s %d",&size);
    while( size!=0 ) {
        if(i==0){ // primeira matriz
            pQM=initQueueMatrix(3);
            m= pQM->pM;
            init_matrix(m, size);
            i++;
        }
        else{
            m= (MATRIX*)malloc(sizeof(MATRIX));
            init_matrix(m, size);
            enQueueMatrix(pQM, m);
        }
            CELL *c = m->pfirst, *aux = c;
            while (c!=NULL) {
                while (c!=NULL){
                    fscanf(fp,"%d", &(c->v));
                    c=c->E;
                }
                aux=aux->S;
                c=aux;
            }
        fscanf(fp, "%*s %d",&size);
    }
    pQM->plast->pnext=NULL;
    fclose(fp);
    return pQM;
}

void writeQueueMatrixBin(QMATRIX pQM,char *fn){
    QMATRIX * QM=&pQM;
    MATRIX * pM=NULL;
    CELL *c = NULL, *aux=NULL;
    FILE *fp;
    fp=fopen(fn,"wb");
    if(fp==NULL){
        printf("Erro na abertura: %s\n",fn);
    }
    while (QM!=NULL) {
        pM= QM->pM;
        c=pM->pfirst;
        aux = c;
         fprintf(fp, "Matriz: %d\n",pM->size);
         while (c!=NULL) {
             while (c!=NULL){
                 fprintf(fp,"%d ", c->v);
                 c=c->E;
             }
             fprintf(fp,"\n");
             aux=aux->S;
             c=aux;
         }
         fprintf(fp, "Matriz: 0\n");
        QM=QM->pnext;
    }
    
    fclose(fp);
}

QMATRIX *readQueueMatrixBin(char *fn){
    QMATRIX *pQM=NULL;
    MATRIX *m=NULL;
    int i=0,size=0;
    FILE *fp;
    fp=fopen(fn,"rb");
    if(fp==NULL){
        printf("Erro na abertura: %s\n",fn);
        return pQM;
    }
    fscanf(fp, "%*s %d",&size);
    while( size!=0 ) {
        if(i==0){ // primeira matriz
            pQM=initQueueMatrix(3);
            m= pQM->pM;
            init_matrix(m, size);
            i++;
        }
        else{
            m= (MATRIX*)malloc(sizeof(MATRIX));
            init_matrix(m, size);
            enQueueMatrix(pQM, m);
        }
            CELL *c = m->pfirst, *aux = c;
            while (c!=NULL) {
                while (c!=NULL){
                    fscanf(fp,"%d", &(c->v));
                    c=c->E;
                }
                aux=aux->S;
                c=aux;
            }
        fscanf(fp, "%*s %d",&size);
    }
    pQM->plast->pnext=NULL;
    fclose(fp);
    return pQM;
}

void printQueueMatrix(QMATRIX pM){
    QMATRIX * pAux = &pM;
    while(pAux != NULL){
        printMatrixF2(*(pAux->pM));
        pAux=pAux->pnext;
    }
}

void clientSudoku(void){
    MATRIX * m = (MATRIX*)malloc(sizeof(MATRIX));
    QMATRIX * pQM=NULL;
   // int size = 9;
   // init_matrix(m, size);
    
    /*
    //# 4
    int mAux[9][9]={{0,2,8,0,0,7,0,0,0},
                    {0,1,6,0,8,3,0,7,0},
                    {0,0,0,0,2,0,8,5,1},
                    {1,3,7,2,9,0,0,0,0},
                    {0,0,0,7,3,0,0,0,0},
                    {0,0,0,0,4,6,3,0,7},
                    {2,9,0,0,7,0,0,0,0},
                    {0,0,0,8,6,0,1,4,0},
                    {0,0,0,3,0,0,7,0,0}
    };
    
     // #2
     int mAux[9][9]={{4,0,0,0,0,0,9,3,8},
                    {0,3,2,0,9,4,1,0,0},
                    {0,9,5,3,0,0,2,4,0},
                    {3,7,0,6,0,9,0,0,4},
                    {5,2,9,0,0,1,6,7,3},
                    {6,0,4,7,0,3,0,9,0},
                    {9,5,7,0,0,8,3,0,0},
                    {0,0,3,9,0,0,4,0,0},
                    {2,4,0,0,3,0,7,0,9}
    };
    int mAux[9][9]={{5, 7, 6, 3, 2, 4, 1, 9, 8},
                    {1, 0, 3, 6, 8, 5, 7, 2, 4},
                    {8, 2, 4, 1, 9, 7, 3, 5, 6},
                    {7, 1, 0, 2, 6, 9, 5, 0, 3},
                    {6, 3, 5, 4, 7, 8, 9, 1, 2},
                    {9, 0, 2, 5, 1, 3, 8, 6, 7},
                    {3, 5, 1, 8, 0, 2, 6, 7, 9},
                    {2, 6, 7, 9, 3, 1, 4, 8, 5},
                    {4, 8, 9, 7, 5, 6, 0, 3, 1}
                    };
   int mAux[][9]={{1, 0, 3, 4, 0, 6, 8, 0, 0},
                {8, 7, 0, 0, 0, 0, 0, 0, 0},
                {0, 5, 6, 0, 0, 0, 0, 2, 3},
                {0, 0, 8, 0, 1, 0, 0, 4, 0},
                {9, 0, 0, 5, 3, 4, 0, 0, 8},
                {0, 4, 0, 0, 9, 0, 7, 0, 0},
                {3, 6, 0, 0, 0, 0, 5, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 9, 2},
                {0, 0, 5, 7, 0, 1, 3, 0, 0}
    };
    int mAux[][4]={{1,0,2,4},
                {2,4,1,3},
                {0,2,0,1},
                {3,0,4,0}
    };
     //#3
    int mAux[][9]={ {0,1,6,0,0,7,8,0,3},
                    {0,9,0,8,0,0,0,0,0},
                    {8,7,0,0,0,1,0,6,0},
                    {0,4,8,0,0,0,3,0,0},
                    {6,5,0,0,0,9,0,8,2},
                    {0,3,9,0,0,0,6,5,0},
                    {0,6,0,9,0,0,0,8,2},
                    {0,8,0,0,0,2,9,3,6},
                    {9,2,4,6,0,0,5,1,0}
        
    };
    // mater na matriz dinâmica a partir de uma matriz estática
    CELL * c=m->pfirstCell, *aux = c;
    for(int i=0;i<size;i++){
        for(int j=0;j<size;j++){
            c->v= mAux[i][j];
            c=c->E;
        }
        aux=aux->S;
        c=aux;
    }
    saveMatrixtxt(*m, "/Users/faculdade/Documents/LP/projetofinal/data/matrizApont.txt");*/
    m= readMatrixtxt("/Users/faculdade/Documents/LP/projetofinal/data/matrix16.txt");
    
    //pQM=readQueueMatrix("/Users/faculdade/Documents/LP/projetofinal/data/matrizApont.txt");
    //printQueueMatrix(*pQM);
    //writeQueueMatrixBin(*pQM, "/Users/faculdade/Documents/LP/projetofinal/data/queueMatrixBin.bin");
    //pQM=readQueueMatrix("/Users/faculdade/Documents/LP/projetofinal/data/queueMatrixBin.bin");
    //printQueueMatrix(*pQM);
    
   /* solveSudokuRec(m);
    printMatrixF2(*m);
    free_matrix(m);*/
    //cliente_Strategies();
    clientTime(m);
}

void clientTime(MATRIX * pM){
    create_Vetor_Strategies(pM);
    MATRIX  *aux= pM;
    printMatrixF2(*pM);
    clock_t start =clock();
    solveSudokuStrategies(aux);
    printf("\n solveSudokuStrategies(pM):: %f segundos\n", ((double)clock() - start) / CLOCKS_PER_SEC);
    printMatrixF2(*aux);
    //start = clock();
    //solveSudokuRec(pM);
    //printf("\n solveSudokuRec(pM):: %f segundos\n", ((double)clock() - start) / (CLOCKS_PER_SEC ));
    //printMatrixF2(*pM);
}
