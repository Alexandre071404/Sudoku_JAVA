package src.Model;

import java.util.Random;

public class Grid {
    private int[][] matrix;

    public Grid() {
        matrix = new int[9][9];
    }

    public void setElement(int row, int col, int value) {
        matrix[row][col] = value;
    }

    public int getElement(int row, int col) {
        return matrix[row][col];
    }

    public int[][] getgrid() {
        return this.matrix;
    }

    public void display() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isPlaceable(int row, int col, int value) {
        for (int i = 0; i < 9; i++) {
            if (matrix[row][i] == value) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (matrix[i][col] == value) {
                return false;
            }
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (matrix[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean setElementInGrid(int x, int y, int value) {
        if (isPlaceable(x, y, value)) {
            setElement(x, y, value);
            return true;
        } else {
            return false;
        }
    }


    //////////////////////////// Résolution du sudoku///////////////////////////////////////////////////////////////////////

    public boolean solveSudoku() {
        int[] emptyCell = findEmptyCell();

        if (emptyCell == null) {
            return true;
        }

        int row = emptyCell[0];
        int col = emptyCell[1];
        for (int num = 1; num <= 9; num++) {
            if (isPlaceable(row, col, num)) {
                setElement(row, col, num);
                if (solveSudoku()) {
                    return true;
                }
                setElement(row, col, 0);
            }
        }

        return false;
    }

    private int[] findEmptyCell() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (matrix[i][j] == 0) {
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }

    //////////////////////////////////// génération aléatoire de grille////////////////////////////////////////////////////////////

    public void generateGRID(int diff) {
        for (int i = 0; i < 4; i++) {
            alea();
        }
        this.solveSudoku();
        switch (diff) {
            case 1:
                this.deleteElement(40);
                break;
            case 2:
                this.deleteElement(50);
                break;
            case 3:
                this.deleteElement(60);
                break;
            case 4:
                this.deleteElement(70);
                break;

            default:
                break;
        }

    }

    public void alea() {
        Random random = new Random();
        this.matrix[0][0] = random.nextInt(9) + 1;
        int valinit = random.nextInt(9) + 1;
        while (!isPlaceable(0, 3, valinit)) {
            valinit = random.nextInt(9) + 1;
        }
        this.matrix[0][3] = valinit;
        while (!isPlaceable(0, 6, valinit)) {
            valinit = random.nextInt(9) + 1;
        }
        this.matrix[0][6] = valinit;

        int val = random.nextInt(9) + 1;
        int row = random.nextInt(9);
        int coll = random.nextInt(9);
        while (this.matrix[coll][row] != 0) {
            row = random.nextInt(9);
            coll = random.nextInt(9);
        }
        while (!isPlaceable(coll, row, val)) {
            val = random.nextInt(9) + 1;
        }
        this.setElementInGrid(row, coll, val);
    }

    public void deleteElement(int tt) {
        int nb = 0;
        Random random = new Random();
        while (nb < tt) {
            int row = random.nextInt(9);
            int coll = random.nextInt(9);
            if (this.matrix[row][coll] != 0) {
                this.matrix[row][coll] = 0;
                nb++;
            }
        }
    }

    public boolean IsWin() {
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                if (this.matrix[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    

}
