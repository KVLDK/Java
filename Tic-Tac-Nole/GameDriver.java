/**
 V, Kara
 COP-3252
 Project 1
 2/14/17

 Sources: Java How to Program (Early Objects), 9/E; Author(s): (Harvey & Paul) Deitel & Associates
 
 Driver to run game and interact with user
 */

import javax.swing.JOptionPane;
import java.util.Random;

public class GameDriver {

    //constant values to ensure future array and Player values are accurately set
    private static final String CROSS = "X";
    private static final String CIRCLE = "O";
    private static final String EMPTY = " ";
    private static final String p1 = "Player 1";
    private static final String p2 = "Player 2";
    private static final String p3 = "The computer";


    public static void main(String[] args) {

        Random randomSelection = new Random(); //used later to choose player who goes first
        final int length = 3;
        String[][] board = new String[length][length]; //array size is constant



        //player objects initialized with null values, variables set later
        Player playerOne = new Player();
        Player playerTwo= new Player();

        Boolean activeGame = true; //if true, don't exit the game

        do {
            //initialize the board/array with empty values
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board.length; ++j)
                    board[i][j] = EMPTY;
            }

            System.out.printf("%s\n%s\n\n", "Welcome to Tic-Tac-Nole!",
                    "Player 1, please choose the symbol you'd like to play.");

            String selection = null; //users symbol selection
            Boolean validStart = false; //false until a valid symbol selection is made

            //this loop ensures player makes a valid symbol selection
            while (validStart == false) {
                selection = JOptionPane.showInputDialog("Enter X or O");

                if (selection.equals("X") || selection.equals("x")) {
                    validStart = true;
                    System.out.println("Thank you.\nPlayer 2 will play O's\n");
                }
                if (selection.equals("O") || selection.equals("o")) {
                    validStart = true;
                    System.out.println("Thank you.\nPlayer 2 will play X's\n");
                }
            }

            //initialize player objects with user determined values
            if (selection.equals("X") || selection.equals("x")) {
                playerOne.setName(p1);
                playerOne.setSymbol(CROSS);
                playerTwo.setName(p2);
                playerTwo.setSymbol(CIRCLE);
            } else if (selection.equals("O") || selection.equals("o")) {
                playerOne.setName(p1);
                playerOne.setSymbol(CIRCLE);
                playerTwo.setName(p2);
                playerTwo.setSymbol(CROSS);
            }


            System.out.println("Would you like Player 2 to be the computer, or a human?");
            String secondPlayer = JOptionPane.showInputDialog("Play a computer (y) or human (any other key)?");

            if (secondPlayer.equals("Y") || secondPlayer.equals("y"))
                playerTwo.setName(p3);


            //first player decided randomly by choosing from this array below
            Player playerArray[] = {playerOne, playerTwo};
            Player current = playerArray[randomSelection.nextInt(2)];
            Player first;
            Player second;  //placeholders for first and second players in the game

            if (current == playerOne) {
                first = playerOne;
                second = playerTwo;
            } else {
                first = playerTwo;
                second = playerOne;
            }

            System.out.printf("\n%s\n%s%s%s%s\n\n", "The first player is chosen randomly.", first.getName(),
                    " will go first, playing ", first.getSymbol(), "'s.");


            Boolean gameOver = false; //value to determine if the current game is over or not
            DisplayBoard(board);

            //each player gets a turn, if a winner or a draw decided on a turn, loop is exited
            while (gameOver == false) {
                Player playing = first; //placeholder for player who has the turn

                //human vs computer
                if (p3 == first.getName()){

                    if (ComputerPlayGame(playing, board) != gameOver) {
                        gameOver = true;
                        break;}

                playing = second;

                     if (PlayGame(playing, board) != gameOver) {
                        gameOver = true;
                        break; }
             }
                //human vs computer
                else if (p3 == second.getName()) {

                    if (PlayGame(playing, board) != gameOver) {
                        gameOver = true;
                        break;
                    }

                    playing = second;

                    if (ComputerPlayGame(playing, board) != gameOver){
                        gameOver = true;
                        break;
                    }
                }
                //human vs human
                else {
                    if (PlayGame(playing, board) != gameOver) {
                        gameOver = true;
                        break;
                    }

                    playing = second;

                    if (PlayGame(playing, board) != gameOver){
                        gameOver = true;
                        break;
                    }
                }
            }


            String playAgain = JOptionPane.showInputDialog("Quit (n) or play again (any other key)?");
            //exits the game if player chooses n
            if (playAgain.equals("N") || playAgain.equals("n")){
                System.out.println("\nThanks for playing, come again!");
                activeGame = false;}

        }while (activeGame);
    }

    //PlayGame version of the function for human players
    public static Boolean PlayGame(Player playing, String[][] board) {

        //value for PlayGame to return
        Boolean winner = false;

        //row and column initialized with invalid dummy values
        int row = 4;
        int column = 4;

        System.out.printf("\n%s%s%s\n\n", playing.getName(), ", please select a row {1-3} and column {1-3} to place an ",
                playing.getSymbol());

        //value to determine if square is filled
        Boolean empty = false;

        //stays inside loop until an open square is chosen/filled
        while (empty == false) {
            Boolean valid = false;

            //this loop checks user chose a valid row/column
            while (valid == false){
                row = Integer.parseInt(JOptionPane.showInputDialog("Enter row #:")) - 1;
                column = Integer.parseInt(JOptionPane.showInputDialog("Enter column #: ")) - 1;

            if (row < 0 || row > 2) {
                System.out.println("Invalid row selection. Please choose again!\n");
                continue;
            }
            if (column < 0 || column > 2) {
                System.out.println("Invalid column selection. Please choose again!\n");
                continue;

            } else
                valid = true;
            }

            if (!CheckIfEmpty(row, column, board))
                System.out.println("This square is taken. Please choose another!\n");

            else {
                FillSquare(board, row, column, playing);
                DisplayBoard(board);
                empty = true;
            }
        }

        if (CheckForWinner(board, playing)) {
            winner = true;
            System.out.printf("\n%s is the winner! Congratulations!!\n\n", playing.getName());
            return winner;
        }

        if (CheckIfDraw(board)) {
            winner = true;
            System.out.println("\nThe game is a draw. No winners this time.\n\n");
            return winner;
        }
        else
            return winner; //no winners or draws this round, start the next round


    }

    //PlayGame version of the function for the computer "player"
    public static Boolean ComputerPlayGame(Player playing, String[][] board)
    {
        //value for ComputerPlayGame to return
        Boolean winner = false;
        Random randomSelection = new Random(); //selects rows and columns for the computer

        //row and column initialized with invalid dummy values
        int row;
        int column;

        System.out.printf("\n%s%s%s\n\n", playing.getName(), " will now choose a row {1-3} and column {1-3} to place an ",
                playing.getSymbol());

        //value to determine if square is filled
        Boolean empty = false;

        //stays inside loop until an open square is chosen/filled
        while (empty == false) {
            Boolean valid = false;

            //parameters ensure a number between 0-2 will be chosen
            row = (randomSelection.nextInt(3));
            column = (randomSelection.nextInt(3));


            if (CheckIfEmpty(row, column, board))
            {
                FillSquare(board, row, column, playing);
                DisplayBoard(board);
                empty = true;
            }
        }

        if (CheckForWinner(board, playing)) {
            winner = true;
            System.out.printf("\nCongratulations, %s, you're the winner!!\n\n", playing.getName());
            return winner;
        }

        if (CheckIfDraw(board)) {
            winner = true;
            System.out.println("\nThe game is a draw. No winners this time.\n\n");
            return winner;
        }
        else
            return winner; //no winners or draws this round, start the next round
    }

    //check if a particular square is available to fill
    public static Boolean CheckIfEmpty(int row, int column, String[][] board)
    {
        Boolean empty = true;

        if (board[row][column] != EMPTY){
            empty = false;
            return empty; }

        return empty;
    }

    //fill the selected square
    public static void FillSquare(String[][] board, int row, int column, Player active)
    {
        board[row][column] = active.getSymbol();

    }

    public static Boolean CheckForWinner(String[][] board, Player active)
    {
        Boolean winner = false;

        //check rows for winner
        for (int i = 0; i < board.length; ++i) {
            if ((board[i][0]) == active.getSymbol() && (board[i][1]) == active.getSymbol() && (board[i][2]) == active.getSymbol()) {
                winner = true;
                return winner;
            }
        }

        //check columns for winner
        for (int i = 0; i < board.length; ++i) {
            if ((board[0][i]) == active.getSymbol() && (board[1][i]) == active.getSymbol() && (board[2][i]) == active.getSymbol()) {
                winner = true;
                return winner;}
        }

        //check right diagonal for winner
        if (((board[0][0]) == active.getSymbol() && (board[1][1]) == active.getSymbol() && (board[2][2]) == active.getSymbol())){
            winner = true;
            return winner; }

        //check left diagonal for winner
        if (((board[0][2]) == active.getSymbol() && (board[1][1]) == active.getSymbol() && (board[2][0]) == active.getSymbol())) {
            winner = true;
            return winner; }

        return winner;
    }

    public static Boolean CheckIfDraw(String[][] board)
    {
        Boolean draw = false;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {

                if (board[i][j] == EMPTY)
                    return draw; //cannot be a draw if any squares are still empty
            }
        }
        draw = true;
        return draw;
    }

    public static void DisplayBoard(String[][] array)
    {
        int rowMarker = 1;
        System.out.printf("%s\n", "--------------------");
        System.out.printf("%s\n", "-    ___ __   <-----");
        System.out.printf("%s\n", "->  |__ |__ |  | <--");
        System.out.printf("%s\n", "--> |    __||__|  <-");
        System.out.printf("%s\n", "------>            -");
        System.out.printf("%s\n", "--------------------");
        System.out.printf("%s\n", "     1    2    3     \n");

        for (int i = 0; i < array.length; ++i){
            if (i < array.length)
                System.out.printf("   %d  ", rowMarker);
            for (int j = 0; j < array.length; ++j) {
                if (j<=1)
                    System.out.printf("%s | ", (array[i][j]));
                else
                    System.out.printf("%s", (array[i][j]));
            }

            if (i <= 1)
                    System.out.println("\n     ---+---+---  \n");
        ++rowMarker;
        }

        System.out.printf("\n%s\n", "--------------------");
        System.out.printf("%s\n", " ~^ *                ");
        System.out.printf("%s\n", "     By: Kara V     ");
        System.out.printf("%s\n", "               * ^~ ");
        System.out.printf("%s\n", "--------------------");

    }
}
