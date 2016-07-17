package sudoku;

import static java.lang.System.exit;

public class Exe {

    public static long time = System.currentTimeMillis();

    public static void main(String[] args) {
        System.out.println("START:");
        String puzz = "290500007700000400004738012902003064800050070500067200309004005000080700087005109";
        String puzzh = "010020300004003050060000001005700060000800002070012000400005090000400805007000000";
        String puzzs = "293541687718296453654738912972813564846952371531467298369174825125389746487625139";
        String puzzt = "294167358315489627678253491456312879983574216721698534562941783839726145147835962";
        Puzzle puzzle = new Puzzle(puzz);
        PossibleValues possibleValues = new PossibleValues(puzzle);
        puzzle.print();
        solve(puzzle, possibleValues, 0, 0);
    }

    public static void solve(Puzzle puzzle, PossibleValues possibleValues, int col, int row){
        if (row >= 8){ //is it solved? if so, print details, exit
            if (puzzle.isSolution()) {
                System.out.println("Solution found:");
                puzzle.print();
                time = System.currentTimeMillis() - time;
                System.out.println("Execution time: " + time + " ms");
                exit(0);
            }
        }
        if (puzzle.getValue(col, row) != 0){ //is the square already filled in? if yes, skip
            if (col == 8){
                col =0; row++;
            } else {
                col++;
            }
            solve(puzzle, possibleValues, col, row);
            return; //gets back another iteration (we don't want to continue from this point at this square)
        }
        int val = 0;
        while ((val = possibleValues.findNext(col, row)) != 0){
            if (col == 8){
                puzzle.set(col, row, val);
                PossibleValues temp = new PossibleValues(possibleValues.toList());
                temp.placeNumber(col, row, val);
                solve(new Puzzle(puzzle.toString()), temp, 0, row+1);
            } else {
                puzzle.set(col, row, val);
                PossibleValues temp = new PossibleValues(possibleValues.toList());
                temp.placeNumber(col, row, val);
                solve(new Puzzle(puzzle.toString()), temp, col+1, row);
            }
            puzzle.unset(col, row);
        } //no values left, backtracking
        return;
    }
}
