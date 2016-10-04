# Sudoku-Solvers
I wanted to test the performance of two methods of solving Sudoku puzzles, so I wrote some quick code for both.

The first method I test is the standard approach: backtrack through possible combinations until you find one that works. Essentially, cleverer bruteforcing. This package is names *SudokuStd*

The second method I test is more like the human approach to solving. It still uses backtracking, but instead of testing every possible entry for the square it is on, it instead passes a "possible values" object with the puzzle that tracks what values are allowable by removing possibilities as numbers are placed. Obviously, this method is less memory efficient, but I wanted to see if removing the need to check the next value to be placed increased performance. Spoilers: it didn't. It turns out the process of removing a single number from several lists (corresponding to possible values for particular squares) takes more time than simply checking each number for every square.
