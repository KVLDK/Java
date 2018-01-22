/**
 Vahldieck, Kara
 COP-3252
 Project 1
 2/14/17
 Class that creates gameboard
 */

import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.Random;

public class GameDriver {

    private enum Status {WINNER, NOWINNER};

    public static void main(String[] args){

        final int length = 3;
        String[][] board = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};

        DisplayBoard(board);

    }

    //gameboard output
    public static void DisplayBoard(String[][] array)
    {
        System.out.printf("%18s\n", "-------------------");
        System.out.printf("%18s\n", "       F S U       ");
        System.out.printf("%18s\n", "-------------------");
        System.out.printf("%18s\n", "      1   2   3    ");
        System.out.printf("  A   %s | %s | %s \n", array[0][0], array[0][1], array[0][2]);
        System.out.printf("%18s\n", "    - -+- -+- -  ");
        System.out.printf("  B   %s | %s | %s \n", array[1][0], array[1][1], array[1][2]);
        System.out.printf("%18s\n", "    - -+- -+- -  ");
        System.out.printf("  C   %s | %s | %s \n", array[2][0], array[2][1], array[2][2]);
        System.out.printf("%18s\n", "-------------------");
        System.out.printf("%18s\n", "  Kara  Vahldieck  ");
        System.out.printf("%18s\n", "-------------------");


    }



}
