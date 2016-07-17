package sudoku;

import java.util.ArrayList;

/**
 * Ok. Here is what's up. My approach to solving sudokus was to create a list of all values a square could have and
 * remove values as they are no longer possible. That way I don't continually need to check if it works, I just need to
 * remove values, and if 0 is all that remains, it wasn't a valid solution.
 *
 * Since i'm too lazy to build a data structure that simulates a 2-d array of lists, which is not possible, i came up
 * with a easy and lazy solution: one sorted list of three digit numbers. Hundreds place is the column, tens is the row,
 * ones is the number value it could have. If i wanted to try bigger sudoku puzzles I would need to make that 5 digit
 * numbers or 7 digit numbers, and so on, but for now, this works. Could make square objects, and have an array of those
 * but nope :)
 */

public class PossibleValues {

    public ArrayList<Integer> numbers = new ArrayList<Integer>();

    PossibleValues(ArrayList<Integer> array){
        numbers = array;
    }

    PossibleValues(Puzzle puzzle){
        for (int i = 0; i<890; i++){
            if ( i % 100 < 90 ){ //checks second digit is not 0, since that makes no sense in this system
                numbers.add(i);
            }
        }
        int entry = 0;
        for (int row=0; row<9; row++) {
            for (int col = 0; col < 9; col++) {
                entry = puzzle.getValue(col,row);
                if (entry != 0){
                    placeNumber(col, row, entry);
                }
            }
        }
    }

    public void remove(int col, int row, int value){
        numbers.remove(Integer.valueOf(100*col+10*row+value));
    }

    //Could definitely use some further optimization
    public void placeNumber(int col, int row, int val){ // removes other possibilities after a new entry
        for (int i = 0; i < 81; i += 10){
            numbers.remove(Integer.valueOf(col*100+i+val)); // remove entries in the same column
            numbers.remove(Integer.valueOf(i*10+row*10+val)); //remove entries in the same row
        }
        for (int i = 1; i<10; i++) { numbers.remove(Integer.valueOf(col*100+row*10+i)); }
        int remainders = (col%3)*10 + (row%3); //used remainders to determine which squares need to be emptied
        switch (remainders) {
            case 22: numbers.remove(Integer.valueOf((col-2)*100+(row-2)*10+val));
                numbers.remove(Integer.valueOf((col-1)*100+(row-2)*10+val));
                numbers.remove(Integer.valueOf((col-2)*100+(row-1)*10+val));
                numbers.remove(Integer.valueOf((col-1)*100+(row-1)*10+val));
                break;
            case 20: numbers.remove(Integer.valueOf((col-2)*100+(row+2)*10+val));
                numbers.remove(Integer.valueOf((col-1)*100+(row+2)*10+val));
                numbers.remove(Integer.valueOf((col-2)*100+(row+1)*10+val));
                numbers.remove(Integer.valueOf((col-1)*100+(row+1)*10+val));
                break;
            case 21: numbers.remove(Integer.valueOf((col-2)*100+(row-1)*10+val));
                numbers.remove(Integer.valueOf((col-1)*100+(row-1)*10+val));
                numbers.remove(Integer.valueOf((col-2)*100+(row+1)*10+val));
                numbers.remove(Integer.valueOf((col-1)*100+(row+1)*10+val));
                break;
            case 2: numbers.remove(Integer.valueOf((col+2)*100+(row-2)*10+val));
                numbers.remove(Integer.valueOf((col+1)*100+(row-2)*10+val));
                numbers.remove(Integer.valueOf((col+2)*100+(row-1)*10+val));
                numbers.remove(Integer.valueOf((col+1)*100+(row-1)*10+val));
                break;
            case 0: numbers.remove(Integer.valueOf((col+2)*100+(row+2)*10+val));
                numbers.remove(Integer.valueOf((col+1)*100+(row+2)*10+val));
                numbers.remove(Integer.valueOf((col+2)*100+(row+1)*10+val));
                numbers.remove(Integer.valueOf((col+1)*100+(row+1)*10+val));
                break;
            case 1: numbers.remove(Integer.valueOf((col+2)*100+(row-1)*10+val));
                numbers.remove(Integer.valueOf((col+1)*100+(row-1)*10+val));
                numbers.remove(Integer.valueOf((col+2)*100+(row+1)*10+val));
                numbers.remove(Integer.valueOf((col+1)*100+(row+1)*10+val));
                break;
            case 12: numbers.remove(Integer.valueOf((col-1)*100+(row-2)*10+val));
                numbers.remove(Integer.valueOf((col+1)*100+(row-2)*10+val));
                numbers.remove(Integer.valueOf((col-1)*100+(row-1)*10+val));
                numbers.remove(Integer.valueOf((col+1)*100+(row-1)*10+val));
                break;
            case 10: numbers.remove(Integer.valueOf((col-1)*100+(row+2)*10+val));
                numbers.remove(Integer.valueOf((col+1)*100+(row+2)*10+val));
                numbers.remove(Integer.valueOf((col-1)*100+(row+1)*10+val));
                numbers.remove(Integer.valueOf((col+1)*100+(row+1)*10+val));
                break;
            case 11: numbers.remove(Integer.valueOf((col-1)*100+(row-1)*10+val));
                numbers.remove(Integer.valueOf((col+1)*100+(row-1)*10+val));
                numbers.remove(Integer.valueOf((col-1)*100+(row+1)*10+val));
                numbers.remove(Integer.valueOf((col+1)*100+(row+1)*10+val));
                break;
            default: System.out.println("Something is wrong with the algorithms"); break;
        }
    }

    public int findNext(int col, int row){
        for (int i=1; i<10; i++){
            if (numbers.remove(Integer.valueOf(100*col+10*row+i))){
                return i;
            }
        }
        return 0;
    }

    public ArrayList<Integer> toList(){
        ArrayList<Integer> temp = (ArrayList) numbers.clone();
        return temp;
    }
}
