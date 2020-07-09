/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Scanner;

/**
 *
 * @author Home
 */
public class game {

    Scanner s = new Scanner(System.in);
    int height, width;
    public boolean mines[][];       //keeps track of our mines
    public int board[][];           //keeps track of our board and numbers
    public char wincheck[][];       //keeps track of whether the game has ended
    public grid gr;

    void start() {
        boolean check = true;

        while (check) {                                                        
            System.out.println("Enter the height of the grid?");
            height = s.nextInt();

            if (height < 10) {
                System.out.println("Please enter a height greater than 10.");
                continue;
            }
            System.out.println("Enter the width of the grid?");
            width = s.nextInt();
            if (width < 10) {
                System.out.println("Please enter a width greater than 10.");
                continue;
            }
            if (height != width) {
                System.out.println("The grid should have the same height and width!");
                continue;
            }
            break;
        }
        mines = new boolean[height][width];
        board = new int[height][width];
        wincheck = new char[height][width];

        for (int i = 0; i < height; i++) {      //filling wincheck with / so we its easier for us to distinguish
            for (int j = 0; j < width; j++) {
                wincheck[i][j] = '/';
            }
        }

        System.out.println("There are a total of 10 mines in the minefield.");

        gr = new grid(height, width);
        gr.displayGrid(wincheck, board);         //displaying grid for first time  
        gr.randomFillGrid(mines, wincheck); //randomly placing the mines

        gameLoop();
    }

    void gameLoop() {
        int row = 0, column = 0;
        boolean check = true;

        System.out.println("Pick a spot to check for a mine.");
        System.out.println("First enter the row number then the column number:");

        while (check) {
            row = s.nextInt();
            column = s.nextInt();
            if (row > height || column > width) {
                System.out.println("Please enter a valid row and column!");
            } else {
                break;
            }
        }

        row = row - 1;
        column = column - 1;
        checkFinish();              //check whether the game has ended

        if (checkLocation(row, column)) {
            System.out.println("GameOver!");
            System.out.println("There are a total of 10 mines in the minefield.");
            gr.displayGridData(mines);
        } else {
            clearBanks(row, column);
        }
    }

    boolean checkLocation(int row, int column) {
        return mines[row][column];
    }

    void clearBanks(int row, int column) { //this method checks for all 8 sides of the given cell
        int count = 0;
        wincheck[row][column] = '-';       //putting an '-' if we've traversed there already

        if (isValid(row - 1, column) == true) {
            if (mines[row - 1][column] == true) {

                wincheck[row - 1][column] = '*';
                checkFinish();
                count++;
            } else {
                int r = row;
                int c = column;
                while (isValid(r - 1, c) == true && mines[r - 1][c] == false) {
                    r = r - 1;
                    checkAdjacent(r, c);    //iteratively checking adjacent cells
                }
            }

        }

        if (isValid(row + 1, column) == true) {
            if (mines[row + 1][column] == true) {
                wincheck[row + 1][column] = '*';
                checkFinish();
                count++;
            } else {
                int r = row;
                int c = column;
                while (isValid(r + 1, c) == true && mines[r + 1][c] == false) {
                    r = r + 1;
                    checkAdjacent(r, c);
                }
            }

        }

        if (isValid(row, column + 1) == true) {
            if (mines[row][column + 1] == true) {
                wincheck[row][column + 1] = '*';
                checkFinish();
                count++;
            } else {
                int r = row;
                int c = column;
                while (isValid(r, c + 1) == true && mines[r][c + 1] == false) {
                    c = c + 1;
                    checkAdjacent(r, c);
                }
            }

        }

        if (isValid(row, column - 1) == true) {
            if (mines[row][column - 1] == true) {
                wincheck[row][column - 1] = '*';
                checkFinish();
                count++;
            } else {
                int r = row;
                int c = column;
                while (isValid(r, c - 1) == true && mines[r][c - 1] == false) {
                    c = c - 1;
                    checkAdjacent(r, c);
                }
            }

        }

        if (isValid(row - 1, column + 1) == true) {
            if (mines[row - 1][column + 1] == true) {
                wincheck[row - 1][column + 1] = '*';
                checkFinish();
                count++;
            } else {
                int r = row;
                int c = column;
                while (isValid(r - 1, c + 1) == true && mines[r - 1][c + 1] == false) {
                    r = r - 1;
                    c = c + 1;
                    checkAdjacent(r, c);
                }
            }
        }

        if (isValid(row - 1, column - 1) == true) {
            if (mines[row - 1][column - 1] == true) {
                wincheck[row - 1][column - 1] = '*';
                checkFinish();
                count++;
            } else {
                int r = row;
                int c = column;
                while (isValid(r - 1, c - 1) == true && mines[r - 1][c - 1] == false) {
                    r = r - 1;
                    c = c - 1;
                    checkAdjacent(r, c);
                }
            }
        }

        if (isValid(row + 1, column + 1) == true) {
            if (mines[row + 1][column + 1] == true) {
                wincheck[row + 1][column + 1] = '*';
                checkFinish();
                count++;
            } else {
                int r = row;
                int c = column;
                while (isValid(r + 1, c + 1) == true && mines[r + 1][c + 1] == false) {
                    r = r + 1;
                    c = c + 1;
                    checkAdjacent(r, c);
                }
            }
        }

        if (isValid(row + 1, column - 1) == true) {
            if (mines[row + 1][column - 1] == true) {
                wincheck[row + 1][column - 1] = '*';
                checkFinish();
                count++;
            } else {
                int r = row;
                int c = column;
                while (isValid(r + 1, c - 1) == true && mines[r + 1][c - 1] == false) {
                    r = r + 1;
                    c = c - 1;
                    checkAdjacent(r, c);
                }
            }
        }

        board[row][column] = count;

        gr.displayGrid(wincheck, board);
        gameLoop();
    }

    void checkAdjacent(int row, int column) {   //further checks for adjacent cells
        int count = 0;                          //is called by the previous method
        wincheck[row][column] = '-';

        if (isValid(row - 1, column) == true) {
            if (mines[row - 1][column] == true) {
                wincheck[row - 1][column] = '*';
                checkFinish();
                count++;
            }

        }

        if (isValid(row + 1, column) == true) {
            if (mines[row + 1][column] == true) {
                wincheck[row + 1][column] = '*';
                checkFinish();
                count++;
            }

        }

        if (isValid(row, column + 1) == true) {
            if (mines[row][column + 1] == true) {
                wincheck[row][column + 1] = '*';
                checkFinish();
                count++;
            }

        }

        if (isValid(row, column - 1) == true) {
            if (mines[row][column - 1] == true) {
                wincheck[row][column - 1] = '*';
                checkFinish();
                count++;
            }

        }

        if (isValid(row - 1, column + 1) == true) {
            if (mines[row - 1][column + 1] == true) {
                wincheck[row - 1][column + 1] = '*';
                checkFinish();
                count++;
            }

        }

        if (isValid(row - 1, column - 1) == true) {
            if (mines[row - 1][column - 1] == true) {
                wincheck[row - 1][column - 1] = '*';
                checkFinish();
                count++;
            }

        }

        if (isValid(row + 1, column + 1) == true) {
            if (mines[row + 1][column + 1] == true) {
                wincheck[row + 1][column + 1] = '*';
                checkFinish();
                count++;
            }

        }

        if (isValid(row + 1, column - 1) == true) {
            if (mines[row + 1][column - 1] == true) {
                wincheck[row + 1][column - 1] = '*';
                checkFinish();
                count++;
            }

        }

        board[row][column] = count;
    }

    boolean isValid(int row, int column) { //this is to confirm that the user enters values within the grid's range
        return ((row >= 0 && row < height) && (column >= 0 && column < width));
    }

    void checkFinish() {
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (wincheck[i][j] == '*') {
                    count++;
                }
                if (count == 10) {              //if all the mines have been revealed the game has ended
                    gr.displayGrid(wincheck, board);
                    System.out.println("You WON!");
                    System.exit(0);
                }
            }
        }
    }
}
