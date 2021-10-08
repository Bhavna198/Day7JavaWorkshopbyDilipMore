package com.bridgelabz;

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {

        /**
         // Possible construction of OOP
         // Class : TicTacToe
         // Methods : constructor, findMatchingItem(), printCurrentBoard(),
         // testUserInput(),*/

        final int ROWS = 3;
        final int COLUMNS = 3;
        int filler = 2;
        Scanner in = new Scanner(System.in);
        int choice = 0;
        // winnerDecided is Variable to play game until a Winner is decided ->
        // draws don't count
        boolean winnerDecided = false;

        // Matrix
        //
        // 1 | 2 | 3
        // 4 | 5 | 6
        // 7 | 8 | 9

        int[][] tickTackToeFields = new int[ROWS][COLUMNS];

        // int[] arrayPlayerOne = new int[5];
        // int[] arrayPlayerTwo = new int[5];

        do {
            System.out.println("What do you want to do?");
            System.out.println(" 1. Play against Bot");
            System.out.println(" 2. Play against Human");
            // Bo3 or Bo5
            System.out.println(" 3. Stop Programm");
            System.out.println(""); // seperator
            choice = in.nextInt();
            System.out.println(""); // seperator
            switch (choice) {
                case 1:

                    // Mechanism to Fill array with numbers indicating each quadrant
                    // of the
                    // Matrix
                    // Dunno if int[][] tickTackToeFields = { {1,2,3} , {4,5,6] ,
                    // {7,8,9} }
                    // would even work
                    for (int i = 0; i < ROWS; i++) {
                        for (int j = 0; j < COLUMNS; j++) {
                            // Filler Variable goes from 2-10 to Fill the Array with
                            // 2-10 --> As to why is mentionend at the printBoard
                            // method below
                            tickTackToeFields[i][j] = filler;
                            filler++;
                        }
                    }
                    // USER CHOICE VARIABLE FOR THE QUADRANTS
                    int userChoiceInt = 0;
                    // i is mechanism to choose which Players turn it is
                    int i = 1;
                    do {

                        printBoard(ROWS, COLUMNS, tickTackToeFields);

                        System.out.println(""); // seperator

                        if (i % 2 == 0 && i < 10) {
                            System.out.println("\nBot is choosing (O)");
                            // see if Field has already been used
                            boolean alreadyUsed = true;
                            do {
                                alreadyUsed = true;
                                // generating a random number then casting it to an
                                // int might use ROUND here since 9 is never gonna
                                // be used I guess?
                                userChoiceInt = (int) (1 + Math.round(Math.random() * 8));
                                // userChoiceInt = 1 + (int)(Math.random()*8);

                                for (int p = 0; p < ROWS; p++) {
                                    for (int g = 0; g < COLUMNS; g++) {
                                        // don't forget -1 since it's 1 higher
                                        // explanation above
                                        // this is obviously botChoiceInt but why
                                        // would
                                        // you make a new Variable to waste
                                        // resources?
                                        if (tickTackToeFields[p][g] - 1 == userChoiceInt) {
                                            alreadyUsed = false;
                                        }
                                    }
                                }

                            } while (alreadyUsed);

                            // actually doing the insert into the array
                            for (int p = 0; p < ROWS; p++) {
                                for (int g = 0; g < COLUMNS; g++) {
                                    if (tickTackToeFields[p][g] - 1 == userChoiceInt) {
                                        tickTackToeFields[p][g] = 1;
                                    }
                                }
                            }

                        } else if (i % 2 == 1 && i < 10) {
                            System.out.println("\nPlayer 1 is chosing (X) --> choose between 1-9");

                            boolean alreadyUsed = true;

                            // Check if user Input is correct and if it's not
                            // already used so you can't cheat
                            do {
                                // CHECKING IF USERINPUT CAN BE USED
                                try {
                                    // when I used nextInt without the converting,
                                    // and typed a letter or generally not a number
                                    // it ended in a loop for some reason??
                                    userChoiceInt = Integer.parseInt(in.next());
                                } catch (Exception e) {
                                    System.out.println("Use a number between 1-9 Exception");
                                    // so the whole thing restarts are same user
                                    // without actually doing anything
                                    // i--;

                                }

                                for (int p = 0; p < ROWS; p++) {
                                    for (int g = 0; g < COLUMNS; g++) {
                                        // don't forget -1 since it's 1 higher
                                        // explanation above
                                        if (tickTackToeFields[p][g] - 1 == userChoiceInt) {
                                            alreadyUsed = false;
                                        }
                                    }
                                }

                            } while (alreadyUsed);

                            // actually doing the insert into the array
                            for (int p = 0; p < ROWS; p++) {
                                for (int g = 0; g < COLUMNS; g++) {
                                    if (tickTackToeFields[p][g] - 1 == userChoiceInt) {
                                        tickTackToeFields[p][g] = 0;
                                    }
                                }
                            }
                        }

                        i++;

                        // I don't know how to make this easier, here it actually
                        // looks if you've 3 consecutive hits
                        // diagonal,horizontal,vertical I guess you could just check
                        // if they are equal but it's the same
                        if (tickTackToeFields[0][0] + tickTackToeFields[0][1] + tickTackToeFields[0][2] == 3
                                || tickTackToeFields[1][0] + tickTackToeFields[1][1] + tickTackToeFields[1][2] == 3
                                || tickTackToeFields[2][0] + tickTackToeFields[2][1] + tickTackToeFields[2][2] == 3
                                || tickTackToeFields[0][0] + tickTackToeFields[1][0] + tickTackToeFields[2][0] == 3
                                || tickTackToeFields[0][1] + tickTackToeFields[1][1] + tickTackToeFields[2][1] == 3
                                || tickTackToeFields[0][2] + tickTackToeFields[1][2] + tickTackToeFields[2][2] == 3
                                || tickTackToeFields[0][0] + tickTackToeFields[1][1] + tickTackToeFields[2][2] == 3
                                || tickTackToeFields[2][0] + tickTackToeFields[1][1] + tickTackToeFields[0][2] == 3) {
                            winnerDecided = true;

                            printBoard(ROWS, COLUMNS, tickTackToeFields);

                            System.out.println("\nBOT WON!!!!! CONGRATULATIONS\n");
                        } else if (tickTackToeFields[0][0] + tickTackToeFields[0][1] + tickTackToeFields[0][2] == 0
                                || tickTackToeFields[1][0] + tickTackToeFields[1][1] + tickTackToeFields[1][2] == 0
                                || tickTackToeFields[2][0] + tickTackToeFields[2][1] + tickTackToeFields[2][2] == 0
                                || tickTackToeFields[0][0] + tickTackToeFields[1][0] + tickTackToeFields[2][0] == 0
                                || tickTackToeFields[0][1] + tickTackToeFields[1][1] + tickTackToeFields[2][1] == 0
                                || tickTackToeFields[0][2] + tickTackToeFields[1][2] + tickTackToeFields[2][2] == 0
                                || tickTackToeFields[0][0] + tickTackToeFields[1][1] + tickTackToeFields[2][2] == 0
                                || tickTackToeFields[2][0] + tickTackToeFields[1][1] + tickTackToeFields[0][2] == 0) {
                            winnerDecided = true;

                            printBoard(ROWS, COLUMNS, tickTackToeFields);

                            System.out.println("\nPLAYER 1 WON!!!!! CONGRATULATIONS\n");
                        }

                        // ask user if they want to stop or not
                        if (winnerDecided != true && i == 10) {
                            System.out.println("Do you want to continue or stop? Y / N");
                            String userChoiceString = in.next();
                            // toLowerCase will ensure that any user choice will be
                            // accepted, includes yEs yES y ye es etc.. think this
                            // is the best solution
                            if ("yes".contains(userChoiceString.toLowerCase())) {
                                winnerDecided = true;
                            } else if ("no".contains(userChoiceString.toLowerCase())) {
                                winnerDecided = false;
                            } else {
                                System.out.println("Instructions unclear - going back to main Menu");
                                winnerDecided = true;
                            }
                        }

                    } while (!winnerDecided);

                    break;
                case 2:
                    break;
                case 3:
                    break;
            }

        } while (choice != 3);

        in.close();
    }

    public static void printBoard(int Rows, int Column, int Array1[][]) {
        for (int k = 0; k < Rows; k++) {
            for (int j = 0; j < Column; j++) {
                if ((k == 1 || k == 2) && j == 0) {
                    System.out.print("---+---+---");
                    System.out.println("");
                }

                if (Array1[k][j] == 0) {
                    System.out.print(" X");
                } else if (Array1[k][j] == 1) {
                    System.out.print(" O");
                } else {

                    /** I had to fill Array with Numbers greater
                     // 1 since the User choices will be 1 and 0
                     // depending on the answer
                     // And then I check the Array if it already
                     // has
                     // a 1 or 0 in it
                     // numbers from 1-9 it would say that the
                     // first
                     // quadrant has been used
                     // hope this somewhat explains my weird
                     // thought
                     // process in here - and sorry for bad
                     // explanation probably should've just used a random high
                     // number for user choices or constants */
                    System.out.print(" " + (Array1[k][j] - 1));
                }

                if (j < 2) {
                    System.out.print(" |");
                }

            }
            System.out.println(""); // just a new line seperator
            // Maybe make a function
            // that
            // just makes a new line?
        }
    }

}

