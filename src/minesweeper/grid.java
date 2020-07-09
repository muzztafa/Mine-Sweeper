/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

/**
 *
 * @author Home
 */
public class grid {

    int height, width;

    grid(int height, int width) {
        this.height = height;
        this.width = width;
    }

    static boolean check = false;    //this check is for printing the first time before the game has started

    void randomFillGrid(boolean mines[][], char wincheck[][]) {
        for (int i = 0; i < 10; i++) {
            boolean check = false;

            while (check == false) {
                int rand1 = ((int) (Math.random() * (height - 1) + 1));
                int rand2 = ((int) (Math.random() * (width - 1) + 1));

                if (mines[rand1][rand2] == false) {
                    mines[rand1][rand2] = true;
                    check = true;
                }

            }
        }
    }

    void displayGrid(char wincheck[][], int arr[][]) {

        System.out.print("  ");
        for (int i = 0; i < width; i++) {       //printing the column numbers 
            System.out.print(" " + (i + 1));
        }
        System.out.println("");

        if (check == false) {
            for (int i = 0; i < width; i++) {
                System.out.print(i + 1);
                System.out.print(" ");
                for (int j = 0; j < height; j++) {

                    System.out.print(" *");
                }
                System.out.println("");
            }
            check = true;
        } else {
            for (int i = 0; i < width; i++) {
                System.out.print(i + 1);
                System.out.print(" ");
                for (int j = 0; j < height; j++) {
                    if (wincheck[i][j] == '*') {
                        System.out.print(" *");
                    } else if (wincheck[i][j] == '-') {
                        if (arr[i][j] != 0) {
                            System.out.print(" " + arr[i][j]);
                        } else {
                            System.out.print("  ");
                        }
                    } else {
                        System.out.print("  ");
                    }
                }
                System.out.println("");
            }

        }

    }

    void displayGridData(boolean mines[][]) {
        System.out.print("  ");
        for (int i = 0; i < width; i++) {       //printing the column numbers 
            System.out.print(" " + (i + 1));

        }
        System.out.println("");

        for (int i = 0; i < height; i++) {
            System.out.print(i + 1);
            System.out.print(" ");
            for (int j = 0; j < width; j++) {
                if (mines[i][j] == true) {
                    System.out.print(" *");
                } else {
                    System.out.print(" .");
                }
            }
            System.out.println("");
        }

    }
}
