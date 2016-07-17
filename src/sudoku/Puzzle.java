package sudoku;

public class Puzzle {

    Integer[][] values = new Integer[9][9];

    Puzzle(String s) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                values[col][row] = Character.getNumericValue(s.charAt(col + row * 9));
            }
        }
    }

    public void print() {
        System.out.println();
        System.out.println(" -----------------------");
        for (int row = 0; row < 9; row++) {
            System.out.print("| ");
            for (int col = 0; col < 9; col++) {
                System.out.print(values[col][row] + " ");
                if (col % 3 == 2) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if (row % 3 == 2) {
                System.out.println(" -----------------------");
            }
        }
        System.out.println();
    }

    public int getValue(int col, int row) {
        return values[col][row];
    }

    public void unset(int col, int row) {
        values[col][row] = 0;
    }

    public void set(int col, int row, int val) {
        values[col][row] = val;
    }

    public boolean checkMove(int col, int row, int val) {
        for (int i = 0; i < 9; i++) {
            if (values[col][i] == val) {
                return false;
            }
            if (values[i][row] == val) {
                return false;
            }
        }
        int colrem = col % 3;
        int rowrem = row % 3;
        if (colrem == 0) {
            if (rowrem == 0) {
                if (values[col + 1][row + 1] == val) {
                    return false;
                }
                if (values[col + 1][row + 2] == val) {
                    return false;
                }
                if (values[col + 2][row + 1] == val) {
                    return false;
                }
                if (values[col + 2][row + 2] == val) {
                    return false;
                }
            }
            if (rowrem == 1) {
                if (values[col + 1][row - 1] == val) {
                    return false;
                }
                if (values[col + 1][row + 1] == val) {
                    return false;
                }
                if (values[col + 2][row - 1] == val) {
                    return false;
                }
                if (values[col + 2][row + 1] == val) {
                    return false;
                }
            }
            if (rowrem == 2) {
                if (values[col + 1][row - 1] == val) {
                    return false;
                }
                if (values[col + 1][row - 2] == val) {
                    return false;
                }
                if (values[col + 2][row - 1] == val) {
                    return false;
                }
                if (values[col + 2][row - 2] == val) {
                    return false;
                }
            }
        }
        if (colrem == 1) {
            if (rowrem == 0) {
                if (values[col + 1][row + 1] == val) {
                    return false;
                }
                if (values[col + 1][row + 2] == val) {
                    return false;
                }
                if (values[col - 1][row + 1] == val) {
                    return false;
                }
                if (values[col - 1][row + 2] == val) {
                    return false;
                }
            }
            if (rowrem == 1) {
                if (values[col + 1][row - 1] == val) {
                    return false;
                }
                if (values[col + 1][row + 1] == val) {
                    return false;
                }
                if (values[col - 1][row - 1] == val) {
                    return false;
                }
                if (values[col - 1][row + 1] == val) {
                    return false;
                }
            }
            if (rowrem == 2) {
                if (values[col + 1][row - 1] == val) {
                    return false;
                }
                if (values[col + 1][row - 2] == val) {
                    return false;
                }
                if (values[col - 1][row - 1] == val) {
                    return false;
                }
                if (values[col - 1][row - 2] == val) {
                    return false;
                }
            }
        }
        if (colrem == 2) {
            if (rowrem == 0) {
                if (values[col - 1][row + 1] == val) {
                    return false;
                }
                if (values[col - 1][row + 2] == val) {
                    return false;
                }
                if (values[col - 2][row + 1] == val) {
                    return false;
                }
                if (values[col - 2][row + 2] == val) {
                    return false;
                }
            }
            if (rowrem == 1) {
                if (values[col - 1][row - 1] == val) {
                    return false;
                }
                if (values[col - 1][row + 1] == val) {
                    return false;
                }
                if (values[col - 2][row - 1] == val) {
                    return false;
                }
                if (values[col - 2][row + 1] == val) {
                    return false;
                }
            }
            if (rowrem == 2) {
                if (values[col - 1][row - 1] == val) {
                    return false;
                }
                if (values[col - 1][row - 2] == val) {
                    return false;
                }
                if (values[col - 2][row - 1] == val) {
                    return false;
                }
                if (values[col - 2][row - 2] == val) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSolution() {
        int rowsum = 0;
        int colsum = 0;
        for (int i = 0; i < 9; i++) {
            rowsum = 0;
            colsum = 0;
            for (int j = 0; j < 9; j++) {
                rowsum += getValue(j, i);
                colsum += getValue(i, j);
            }
            if (rowsum != 45 || colsum != 45) {
                return false;
            }
        }
        return true;
    }

    public String toString(){
        String puzz = "";
        for (int row=0; row<9; row++) {
            for (int col = 0; col < 9; col++) {
                puzz += values[col][row];
            }
        }
        return puzz;
    }
}