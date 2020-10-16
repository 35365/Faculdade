
// A Backtracking program in C to solve Sudoku problem
#include "backtracking_search.h"

/* Takes a partially filled-in grid and attempts to assign values to
all unassigned locations in such a way to meet the requirements
for Sudoku solution (non-duplication across rows, columns, and boxes) */
bool SolveSudoku(int grid[N][N]){

    int row, col;

    // If there is no unassigned location, we are done
    if (!FindUnassignedLocation(grid, &row, &col))
        return true; // success!

    // consider digits 1 to 9
    for (int num = 1; num <= N; num++)
    {
        // if looks promising
        if (isSafe(grid, row, col, num))
        {
            // make tentative assignment
            grid[row][col] = num;

            // return, if success, yay!
            if (SolveSudoku(grid))
                return true;

            // failure, unmake & try again
            grid[row][col] = UNASSIGNED;
        }
        print_Matrix(grid);
    }
    return false; // this triggers backtracking_search
}

/* Searches the grid to find an entry that is still unassigned. If
found, the reference parameters row, col will be set the location
that is unassigned, and true is returned. If no unassigned entries
remain, false is returned. */
bool FindUnassignedLocation(int grid[N][N], int *row, int *col){

    for (*row = 0; *row < N; (*row)++)
        for (*col = 0; *col < N; (*col)++)
            if (grid[*row][*col] == UNASSIGNED)
                return true;
    return false;
}

/* Returns a boolean which indicates whether an assigned entry
in the main diagolnal matches the given number. */
bool UsedInDiag1(int grid[N][N], int row, int col, int num){
    if(row!=col)
        return false;
    for(int r = 0, c = 0; r < N && c < N; r++, c++)
        if (grid[r][c] == num)
            return true;
    return false;

    /* for(int row = 0; row < N; row++){
         for(int col = 0; col < N; col++){
             if (grid[row][col] == num)
                 return true;
             return false;
         }
     }*/
}

/* Returns a boolean which indicates whether an assigned entry
in the second diagolnal matches the given number. */
bool UsedInDiag2(int grid[N][N], int row, int col, int num){
    if((row+col)!=(N-1))
        return false;
    for(int r = 0, c = N-1; r < N && c >= 0; r++, c--)
        if (grid[r][c] == num)
            return true;
    return false;

    /*for(int row = 0; row < N; row++){
        for(int col = 8; col >= 0; col--)
            if (grid[row][col] == num)
                return true;
            return false;
    }*/
}

/* Returns a boolean which indicates whether an assigned entry
in the specified row matches the given number. */
bool UsedInRow(int grid[N][N], int row, int num){

    for (int col = 0; col < N; col++)
        if (grid[row][col] == num)
            return true;
    return false;
}

/* Returns a boolean which indicates whether an assigned entry
in the specified column matches the given number. */
bool UsedInCol(int grid[N][N], int col, int num){

    for (int row = 0; row < N; row++)
        if (grid[row][col] == num)
            return true;
    return false;
}

/* Returns a boolean which indicates whether an assigned entry
within the specified 3x3 box matches the given number. */
bool UsedInBox(int grid[N][N], int boxStartRow, int boxStartCol, int num){

    for (int row = 0; row < sqrt(N); row++)
        for (int col = 0; col < sqrt(N); col++)
            if (grid[row+boxStartRow][col+boxStartCol] == num)
                return true;
    return false;
}

/* Returns a boolean which indicates whether it will be legal to assign
num to the given row,col location. */
bool isSafe(int grid[N][N], int row, int col, int num){

    //criar if para ver se estou na diagonal principal, secundaria ou nao e
    //ter atençao ao meio do sudoku que estou nas duas diagonais

    //diagonal principal
    for(int i = 0, j = 0; i<row && j<col; i++, j++){
        if(grid[i][j] == '0')
            return !UsedInDiag1(grid, row, col, num);
    }

    //diagonal secundaria
    for(int i = 0, j = N; i<row && j>=0; i++, j--){
        if(grid[i][j] == '0')
            return !UsedInDiag2(grid, row, col, num);
    }

    /* Check if 'num' is not already placed in current row,
    current column and current 3x3 box */
    return  !UsedInDiag1(grid, row, col, num) &&
            !UsedInDiag2(grid, row, col, num) &&
            !UsedInRow(grid, row, num) &&
            !UsedInCol(grid, col, num) &&
            !UsedInBox(grid, row - row%((int)sqrt(N)) , col - col%((int)sqrt(N)), num)&&
            grid[row][col]==UNASSIGNED;
}

/* A utility function to print grid */
void printGrid(int grid[N][N]){

    for (int row = 0; row < N; row++)
    {
        for (int col = 0; col < N; col++)
            printf("%2d", grid[row][col]);
        printf("\n");
    }
}

/* Driver Program to test above functions */
int main_bts(int argc,const char *argv[]){

    // 0 means unassigned cells
    /*int grid[N][N] = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                      {5, 2, 0, 0, 0, 0, 0, 0, 0},
                      {0, 8, 7, 0, 0, 0, 0, 3, 1},
                      {0, 0, 3, 0, 1, 0, 0, 8, 0},
                      {9, 0, 0, 8, 6, 3, 0, 0, 5},
                      {0, 5, 0, 0, 9, 0, 6, 0, 0},
                      {1, 3, 0, 0, 0, 0, 2, 5, 0},
                      {0, 0, 0, 0, 0, 0, 0, 7, 4},
                      {0, 0, 5, 2, 0, 6, 3, 0, 0}};*/

    //exemplo do enunciado do projeto
    int grid[N][N] = {{1, 0, 3, 4, 0, 6, 8, 0, 0},
                      {8, 7, 0, 0, 0, 0, 0, 0, 0},
                      {0, 5, 6, 0, 0, 0, 0, 2, 3},
                      {0, 0, 8, 0, 1, 0, 0, 4, 0},
                      {9, 0, 0, 5, 3, 4, 0, 0, 8},
                      {0, 4, 0, 0, 9, 0, 7, 0, 0},
                      {3, 6, 0, 0, 0, 0, 5, 1, 0},
                      {0, 0, 0, 0, 0, 0, 0, 9, 2},
                      {0, 0, 5, 7, 0, 1, 3, 0, 0}};

    if (SolveSudoku(grid) == true)
        printGrid(grid);
    else
        printf("No solution exists");

    return 0;
}
