package creation.sahif;

import java.util.Scanner;

/*
 *This program create tic-tac-toe
 *tic-tac-toe can be played with 2 player
 *Create unbeatable AI for tic-tac-toe
 *AI playing second turn
 *Not handle error input exception
 *@author: Khoerudin
 */

public class TicTacToe{
    private static int row, col;
    private static char[][] board = new char[3][3];
    private static char turn = 'X';
    private static int countMove = 0;
    private static Scanner scan = new Scanner(System.in);
    
    //contractor
    public TicTacToe(){
        //initial board
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                board[i][j] = '_';
            }
        }
    }
    
    public void play(){
        boolean playing = true;
        int sd;
        System.out.println("1 or 2 player(1/2):");
        sd = scan.nextInt();
        
        //input row and column for first turn
        System.out.println("You turn(X), insert row and column:");
        System.out.println("insert row(1/2/3):");
        row = scan.nextInt() - 1;
        System.out.println("insert column(1/2/3):");
        col = scan.nextInt() - 1;
        board[row][col] = turn;//fill the board
        countMove += 1;
        
        while(playing){
            if(sd == 2){
                printBoard();
                do{
                    turn = 'O';
                    System.out.println("You turn(O), insert row and column:");
                    System.out.println("insert row(1/2/3):");
                    row = scan.nextInt() - 1;
                    System.out.println("insert row(1/2/3):");
                    col = scan.nextInt() - 1;
                    if(board[row][col] != '_'){//check if already fill
                        System.out.println("already fill");
                        printBoard();
                    }
                }while(board[row][col] != '_');
                board[row][col] = turn;
                countMove += 1;
            }else{
                ai();//make ai move
            }
            printBoard();
            if(isLastMove(row, col)){//check if O is win
                playing = false;
                System.out.println("Game over! You(X) Lost!" );
            }
            if(playing){
                do{
                    turn = 'X';
                    System.out.println("You turn(X), insert row and column:");
                    System.out.println("insert row(1/2/3):");
                    row = scan.nextInt() - 1;
                    System.out.println("insert row(1/2/3):");
                    col = scan.nextInt() - 1;
                    
                    if(board[row][col] != '_'){//check if already fill
                        System.out.println("already fill");
                        printBoard();
                    }
                }while(board[row][col] != '_');
                board[row][col] = turn;
                countMove += 1;
                
                if(isLastMove(row, col)){//check if player win
                    playing = false;
                    printBoard();
                    System.out.println("Game over! You(X) Win!" );
                }else if(countMove == 9){//check if draw
                    playing = false;
                    printBoard();
                    System.out.println("Game over! Draw!");
                }
            }
        }
    }
    
    //print the current board
    public void printBoard(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(j == 0){
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    //check for winner
    private static boolean isLastMove(int r, int c){
        //check win in row and column
        if(board[0][c] == board[1][c] &&
            board[0][c] == board[2][c]){
            return true;
        }else if(board[r][0] == board[r][1] &&
            board[r][0] == board[r][2]){
            return true;
        //check diagonal win
        }else if(board[0][0] == board[1][1] &&
            board[0][0] == board[2][2] && board[1][1] != '_'){
            return true;
        }else if(board[0][2] == board[1][1] &&
            board[0][2] == board[2][0] && board[1][1] != '_'){
            return true;
        }
        return false;
    }
    
    //create ai move
    private static void ai(){
        turn = 'O';
        //first move, try get middle or corner
        if((board[1][1] != 'O') && (board[0][0] != 'O')){
            if(board[1][1] == '_'){
                row = 1; col = 1;
            }else{
                row = 0; col =0;
            }
        //search move to get win
        //(player got middle, ai got corner 0,0)
        }else if((board[0][0] == 'O') && (board[0][1] == 'O') &&
            (board[0][2] == '_')){
            row = 0; col = 2;
        }else if((board[0][0] == 'O') && (board[0][2] == 'O') &&
            (board[0][1] == '_')){
            row = 0; col = 1;
        }else if((board[0][0] == 'O') && (board[1][0] == 'O') &&
            (board[2][0] == '_')){
            row = 2; col = 0;
        }else if((board[0][0] == 'O') && (board[2][0] == 'O') &&
            (board[1][0] == '_')){
            row = 1; col = 0;
        }else if((board[0][2] == 'O') && (board[1][2] == 'O') &&
            (board[2][2] == '_')){
            row = 2; col = 2;
        }else if((board[2][0] == 'O') && (board[2][1] == 'O') &&
            (board[2][2] == '_')){
            row = 2; col = 2;
        //handle player dominate middle
        }else if(board[1][1] == 'X'){
            //block player to win(player got middle)
            if((board[0][1] == 'X') && (board[2][1] == '_')){
                row = 2; col = 1;
            }else if((board[0][2] == 'X') && (board[2][0] == '_')){
                row = 2; col = 0;
            }else if((board[1][0] == 'X') && (board[1][2] == '_')){
                row = 1; col = 2;
            }else if((board[1][2] == 'X') && (board[1][0] == '_')){
                row = 1; col = 0;
            }else if((board[2][0] == 'X') && (board[0][2] == '_')){
                row = 0; col = 2;
            }else if((board[2][1] == 'X') && (board[0][1] == '_')){
                row = 0; col = 1;
            //find chance to get win
            }else if((board[0][0] == 'O') && (board[1][0] == '_') &&
                board[2][0] == '_'){
                row = 2; col = 0;
            //rest move handle, try to get corner first
            }else if(board[0][2] == '_'){
                row = 0; col = 2;
            }else if(board[0][1] == '_'){//try close to corner 0,0
                row = 0; col = 1;
            }else if(board[1][0] == '_'){//try close to corner 0,0
                row = 1; col = 0;
            }
        //search move to get win(ai got middle)
        }else if((board[1][1] == 'O') && (board[0][1] == 'O') &&
            (board[2][1] == '_')){
            row = 2; col = 1;
        }else if((board[1][1] == 'O') && (board[0][2] == 'O') &&
            (board[2][0] == '_')){
            row = 2; col = 0;
        }else if((board[1][1] == 'O') && (board[1][0] == 'O') &&
            (board[1][2] == '_')){
            row = 1; col = 2;
        }else if((board[1][1] == 'O') && (board[1][2] == 'O') &&
            (board[1][0] == '_')){
            row = 1; col = 0;
        }else if((board[1][1] == 'O') && (board[2][0] == 'O') &&
            (board[0][2] == '_')){
            row = 0; col = 2;
        }else if((board[1][1] == 'O') && (board[2][1] == 'O') &&
            (board[0][1] == '_')){
            row = 0; col = 1;
        }else if((board[1][1] == 'O') && (board[0][0] == 'O') &&
            (board[2][2] == '_')){
            row = 2; col = 2;
        }else if((board[1][1] == 'O') && (board[2][2] == 'O') &&
            (board[0][0] == '_')){
            row = 0; col = 0;
        //block player to win(ai got middle)
        }else if((board[0][1] == 'X') && (board[0][2] == 'X') &&
            (board[0][0] == '_')){
            row = 0; col = 0;
        }else if((board[0][0] == 'X') && (board[0][2] == 'X') &&
            (board[0][1] == '_')){
            row = 0; col = 1;
        }else if((board[0][0] == 'X') && (board[0][1] == 'X') &&
            (board[0][2] == '_')){
            row = 0; col = 2;
        }else if((board[1][0] == 'X') && (board[2][0] == 'X') &&
            (board[0][0] == '_')){
            row = 0; col = 0;
        }else if((board[0][0] == 'X') && (board[2][0] == 'X') &&
            (board[1][0] == '_')){
            row = 1; col = 0;
        }else if((board[0][0] == 'X') && (board[1][0] == 'X') &&
            (board[2][0] == '_')){
            row = 2; col = 0;
        }else if((board[1][2] == 'X') && (board[2][2] == 'X') &&
            (board[0][2] == '_')){
            row = 0; col = 2;
        }else if((board[0][2] == 'X') && (board[2][2] == 'X') &&
            (board[1][2] == '_')){
            row = 1; col = 2;
        }else if((board[0][2] == 'X') && (board[1][2] == 'X') &&
            (board[2][2] == '_')){
            row = 2; col = 2;
        }else if((board[2][1] == 'X') && (board[2][2] == 'X') &&
            (board[2][0] == '_')){
            row = 2; col = 0;
        }else if((board[2][0] == 'X') && (board[2][2] == 'X') &&
            (board[2][1] == '_')){
            row = 2; col = 1;
        }else if((board[2][0] == 'X') && (board[2][1] == 'X') &&
            (board[2][2] == '_')){
            row = 2; col = 2;
        //other handle if ai dominate middle
        }else if((board[1][1] == 'O')){
            //find chance to win and try get corner
            if((board[2][0] == '_') && (board[0][2] == '_')){
                //eliminate double chance player win
                if(board[0][1] == 'X' || board[1][2] == 'X'){
                    row = 0; col = 2;
                }else{
                    row = 2; col = 0;
                }
            }else if((board[0][0] == '_') && (board[2][2] == '_')){
                //eliminate double chance player win
                if(board[2][1] == 'X' || board[1][2] == 'X'){
                    row = 2; col =2;
                }else{
                    row = 0; col = 0;
                }
            }else if((board[0][1] == '_') && (board[2][1] == '_')){
                row = 0; col = 1;//close to corner 0,0
            }else if((board[1][0] == '_') && (board[1][2] == '_')){
                row = 1; col = 0;//close to corner 0,0
            //rest move handle, try close to corner 0,0
            }else if(board[0][1] == '_'){
                row = 0; col = 1;
            }else if(board[1][0] == '_'){
                row = 1; col = 0;
            }else if(board[2][0] == '_'){
                row = 2; col = 0;
            }else if(board[2][1] == '_'){
                row = 2; col = 1;
            }
        }
        board[row][col] = turn;
        countMove += 1;
    }
}
