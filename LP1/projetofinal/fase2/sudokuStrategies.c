//
//  sudokuStrategies.c
//  projetofinal
//
//  Created by Faculdade on 28/12/2019.
//  Copyright © 2019 Patricia. All rights reserved.
//

#include "sudokuStrategies.h"

MATRIX * create_Vetor_Strategies(MATRIX *pM){
    CELL * c= pM->pfirst, *aux=c;
    int size =pM->size;
    for(int i=0;i<size;i++){
        (c->p)[i]=0;
    }
    
    for(int i=0;i< size;i++){// linhas
        for(int j=0;j<size;j++){ //colunas
            if(c->v ==0){
                int k=0; // andar dentro do m
                for(int num =1;num <=size;num++){
                    if(ifSafe(pM, i, j, num)==1){
                        (c->p)[k] = num;
                        k++;
                    }
                }
                if( k<size )// garante que o último é mesmo o último
                    (c->p)[k]= 0;
            }
            c=c->E;
        }
        aux=aux->S;
        c=aux;
    }
    return pM;
}

void cliente_Strategies(void){
    MATRIX * m = (MATRIX*)malloc(sizeof(MATRIX));
    int size = 9;
    init_matrix(m, size);
    m=readMatrixtxt("/Users/faculdade/Documents/LP/projetofinal/data/matrizApont5.txt");
    printMatrixF2(*m);
    
    create_Vetor_Strategies(m);
    print_Vetor_Strategies(*m);
    
    /*hiddenSingles(m);
    printMatrixF2(*m);
    print_Vetor_Strategies(*m);
    
    nakedSingles(m);
    printMatrixF2(*m);
    print_Vetor_Strategies(*m);
    
    //testar com outro sudoku
    nakedPairs(m);
    printMatrixF2(*m);
    print_Vetor_Strategies(*m);
    
    boxLineReduction(m);
    printMatrixF2(*m);
    print_Vetor_Strategies(*m);*/
    
    pointingPairs(m);
    printMatrixF2(*m);
    print_Vetor_Strategies(*m);
    
    free_matrix(m);
    free(m);
}

void print_Vetor_Strategies(MATRIX pM){
    int size = pM.size;
    CELL * c= pM.pfirst, *aux=c;
    for(int i=0;i< size;i++){
        for(int j=0;j<size;j++){
            printf("(%d,%d):",i,j);
            for(int k =0;k <=size;k++){
                int num =(c->p)[k];
                if(num == 0){
                    break;
                }
                else
                    printf("%d ",num);
            }
            c=c->E;
            printf("\n");
        }
        aux=aux->S;
        c=aux;
    }
}

MATRIX * nakedSingles (MATRIX * pM){
    CELL * c= pM->pfirst, *aux=c;
    int size = pM->size;
    for(int i=0;i< size;i++){
        for(int j=0;j<size;j++){
            if((c->p)[0]!=0 && (c->p)[1]==0) {// Se só existir uma possibilidade
                c->v= (c->p)[0];
                (c->p)[0]=0;// Esvazia o vetor de probabilidades
            }
            c=c->E;
        }
        aux=aux->S;
        c=aux;
    }
    create_Vetor_Strategies(pM);// atualiza o vetor de probabilidades
    return pM;
}

MATRIX * hiddenSingles (MATRIX * pM){
    CELL * c= pM->pfirst, *aux=c;
    int size = pM->size;
    int count[size];
    for(int i=0;i<size;i++){// inicialização da variável local count
        count[i]=0;
    }
    for(int i=0;i< size;i++){
        for(int j=0;j<size;j++){
            for(int k=0; k<size;k++){
                if((c->p)[k]==0)
                    break;
                // guarda o número de vezes que um valor aparece no valor-1
                count[(c->p)[k]-1]= (count[(c->p)[k]-1] )+ 1;
            }
            c=c->E;
        }
        for(int z=0; z<size;z++){
            if(count[z]==1)// se só aparecer uma vez nessa linha
                placeHiddenSingles(pM, aux, z+1);
        }
        for(int k=0;k<size;k++){// zera se a variável local count
            count[k]=0;
        }
        aux=aux->S;
        c=aux;
    }
    
    return pM;
}

MATRIX * placeHiddenSingles (MATRIX * pM, CELL * cLine, int num){
    for(int i=0;i<pM->size && cLine!=NULL;i++){
        for(int k=0;k<pM->size && (cLine->p)[k]<=num;k++){
            if((cLine->p)[k]==num){
                cLine->v = num;
                create_Vetor_Strategies(pM);// atualiza o vetor de probabilidades
                return pM;
            }
        }
        cLine=cLine->E;
    }
    
    return pM;
}

MATRIX * nakedPairs(MATRIX * pM){
    CELL * c= pM->pfirst, *aux=c;
    int size = pM->size;
    int pair[2]={0,0}, flag[3]={0,0,0};
    for(int i=0;i< size;i++){
        for(int j=0;j<size;j++){
            // se existir só dois digitos guarda-se esses digitos
            if( (c->p)[0]!=0 && (c->p)[1]!=0 && (c->p)[2]==0){
                // Encontra-se um possivel par
                if(pair[0]==0 && pair[1]==0){
                    pair[0]=(c->p)[0];
                    pair[1]=(c->p)[1];
                    flag[1]=j; // coluna do primeiro par
                }
                //Encontra-se outro par
                else if(pair[0]== (c->p)[0] && pair[1]== (c->p)[1]){
                    flag[0]=1;
                    flag[2]=j;// coluna do segundo par
                    break;
                }
            }
            c=c->E;
        }
        if(flag[0] == 1){
            c=aux;
            for(int j=0;j<size;j++){
                if(j!=flag[1] && j!=flag[2]){// colunas dos pares
                    removeFromVector(c, pair[0], size);
                    removeFromVector(c, pair[1], size);
                }
                c=c->E;
            }
        }
        aux=aux->S;
        c=aux;
        // zera se as variáveis
        pair[0]=0;pair[1]=0;
        flag[0]=0; flag[1]=0;flag[2]=0;
    }
    return pM;
}

CELL* removeFromVector(CELL * c, int num, int size){
    for (int k=0;k<size || (c->p)[k]!=0;k++) {
        if ( (c->p)[k]== num) {
            for(int z=k;z<size-1;z++) //Elimina até ao penultimo
                (c->p)[z] = (c->p)[z+1];
            if((c->p)[size-2]==(c->p)[size-1])// se for o último
                (c->p)[size-1]=0;
            k--;// para confirmar o valor novo dessa possição
        }
    }
    return c;
}

MATRIX * pointingPairs(MATRIX * pM){
    CELL * c= pM->pfirst, *aux=c, *current =c, *box =NULL;;
    int size = pM->size, num=0;
    int square =(int)sqrt(pM->size);
    int startCol = 0, flag =0,startRow=0;
  for(int i=0;i< size;i++){
        for(int j=0;j<size;j++){
            for(int k=0;k<size;k++){
                num= (c->p)[k];
                if(num == 0)// se não ouver nada no vetor de possibilidades
                    break;
                else{// fixas num valor e vê se se aparece dentro da caixa
                    startCol=(c->c) - (c->c)%(square);
                    startRow = (c->l) - (c->l)%(square);
                    current =c; // guarda a célula que estamos fixados
                    for (int col=0; col<size;col++){
                        if(col!=c->c){
                            c=c->W;
                        }
                        else{
                            for(int line=0;line<(startRow+square);line++){
                               if(line != startRow)
                                     c=c->N;
                                 else{
                                     for(int w=startRow;w<startRow+square;w++){
                                            if(w==current->l)
                                                c=c->S;
                                            box=c;
                                         for(int z=startCol;z<startRow+square;z++){
                                             for(int q=0;q<size && (c->p)[q]!=0;q++){
                                                 if( (c->p)[q]== num){
                                                     flag=1;
                                                     break;
                                                 }
                                             }
                                             c=c->E;
                                         }
                                            box=box->S;
                                            c=box;
                                     }
                                 }
                            }
                        }
                        c=current;// volta se para a célula onde estavamos fixados
                        if(flag==0)
                            isInBoxPointingPairs(pM, num,startCol, c->l);
                        flag=0;
                    }
                }
            }
            c=c->E;
            for(int r=0;r<2;r++)
                if(r==1) return NULL;
        }
        aux=aux->S;
        c=aux;
    }
    
    return pM;
}

void isInBoxPointingPairs(MATRIX * pM, int num,int startCol,int line){
    CELL * c= pM->pfirst;
    int size = pM->size;
    for(int i=0;i<size;i++){
        if(i==line){
            for(int j=0;j<size;j++){
                if(j!=startCol && j!=(startCol+sqrt(size)) ){
                    for(int k=0;k<size;k++){
                        if((c->p)[k]==num)
                            removeFromVector(c, num, size);
                    }
                }
                c=c->E;
            }
            return;
        }
        else
            c=c->S;
    }
}

MATRIX * boxLineReduction(MATRIX * pM){
    // vou verificar em cada linha e célula por célula se o valor aparece fora da caixa
    // se aparecer remove se o valor do resto das células da caixa sem ser a linha que estavamos
    CELL * c= pM->pfirst, *aux=c, *current =c;
    int size = pM->size, num=0;
    int square =(int)sqrt(pM->size);
    int startCol = 0, flagIs =0;
    for(int i=0;i< size;i++){
        for(int j=0;j<size;j++){
            for(int k=0;k<size;k++){
                num= (c->p)[k];
                if(num == 0)// se não ouver nada no vetor de possibilidades
                    break;
                else{// fixas num valor e vê se se aparece fora da caixa
                    current =c; // guarda a célula que estamos fixados
                    startCol = (c->c) - (c->c)%(square);
                    c = aux; // volta ao inicio da linha
                    for (int col=0; col<size;col++){
                        if(col < startCol || col >= (startCol + square)){
                            if ((c->p)[0]==0)
                                break;
                            else{
                                for(int z=0;(c->p)[z] !=0;z++){
                                    if((c->p)[z] == num && flagIs!=1){
                                        flagIs =1;
                                        break;
                                    }
                                }
                            }
                        }
                        c=c->E;
                    }
                    c=current;// volta se para a célula onde estamos fixados
                }
                if(flagIs == 0){ // se o valor não aparecer na linha fora da caixa
                    // remove se dentro da caixa
                    boxLineReduction_BoxRemoval(pM, num, (c->l) - (c->l)%(square), (c->c) - (c->c)%(square), c->l);
                    //return pM;// Feito para poder testar
                }
                flagIs=0;
            }
            c=c->E;
        }
        aux=aux->S;
        c=aux;
    }
    
    return pM;
}

MATRIX * boxLineReduction_BoxRemoval(MATRIX * pM, int num, int startL, int startCol, int notL){
    int square =(int)sqrt(pM->size);
    int size = pM->size;
    CELL * cell= pM->pfirst, *aux=cell;
    for(int l=0;l<(startL +square);l++){
        if(l==startL){
            for(int c=0;c<(startCol +square);c++){
                if(c==startCol){// chegamos á primeira célula da caixa
                    aux =cell;
                    for(int line =l;line<(startL +square);line++){
                        if(line!=notL){
                            for(int col=c;col<(startCol +square);col++){
                                if(cell->v==0)
                                    removeFromVector(cell, num, size);
                                cell=cell->E;
                            }
                        }
                        aux = aux->S;
                        cell=aux;
                    }
                    return pM;
                }
                cell=cell->E;
            }
        }
        cell = cell->S;
    }
    return pM;
}

void solveSudokuStrategies(MATRIX * pM){
        pM=nakedSingles(pM);
        pM=hiddenSingles(pM);
        pM=nakedSingles(pM);
        solveSudokuRec(pM);
}
