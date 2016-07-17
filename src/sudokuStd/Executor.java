package sudokuStd;

import static java.lang.System.exit;
import static java.lang.System.setOut;

public class Executor {

    public static long time = System.currentTimeMillis();

    public static void main(String[] args) {
        System.out.println("START:");
        Puzzle puzzleh = new Puzzle("010020300004003050060000001005700060000800002070012000400005090000400805007000000");
        Puzzle puzzle = new Puzzle("290500007700000400004738012902003064800050070500067200309004005000080700087005109");
        Puzzle puzzles = new Puzzle("293541687718296453654738912972813564846952371531467298369174825125389746487625139");
        Puzzle puzzlet = new Puzzle("294167358315489627678253491456312879983574216721698534562941783839726145147835962");
        //puzzle.print();
        puzzleh.print();
        solve(puzzle, 0, 0);
    }

    public static void solve(Puzzle puzzle, int col, int row){
        if (row >= 8){
            if (puzzle.isSolution()) {
                System.out.println("Solution found:");
                puzzle.print();
                time = System.currentTimeMillis() - time;
                System.out.println("Execution time: " + time + " ms");
                exit(0);
            }
        }
        if (puzzle.getValue(col, row) != 0){ //Square is already filled in
            if (col == 8){
                col =0; row++;
            } else {
                col++;
            }
            solve(puzzle, col, row);
            return; //maybe not?
        }
        for (int val = 1; val <10; val ++){
            if (puzzle.checkMove(col, row, val)){
                if (col == 8){
                    //puzzle.print();
                    puzzle.set(col, row, val);
                    solve(puzzle, 0, row+1);
                    puzzle.unset(col, row);
                } else {
                    //puzzle.print();
                    puzzle.set(col, row, val);
                    solve(puzzle, col+1, row);
                    puzzle.unset(col, row);
                }
            }
        }
        return;
    }
}