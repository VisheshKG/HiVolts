import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board {

    public static final int BOARD_SIZE = 12;
    public static final int NUM_FENCES = 20;
    public static final int NUM_MHOS = 12;

    private int playerCol;
    private int playerRow;

    private Item[][] board = new Item[BOARD_SIZE][BOARD_SIZE];
    private int turns = 0;

    private static BufferedImage fence;
    private static BufferedImage mho;
    private static BufferedImage player;

    public Board() throws IOException {
        //Add fences down the left and right of the map
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i][0] = new Fence();
            board[i][BOARD_SIZE - 1] = new Fence();
        }
        //Add fences accross the top and bottom of the map
        for (int j =  0; j < BOARD_SIZE; j++) {
            board[0][j] = new Fence();
            board[BOARD_SIZE - 1][j] = new Fence();
        }

        //randomly add 20 fences
        for (int f = 0; f < NUM_FENCES; f++) {

            int x = randomBoardInt();
            int y = randomBoardInt();
            while (board[x][y] != null) {
                x = randomBoardInt();
                y = randomBoardInt();
            }

            board[x][y] = new Fence();
        }

        //randomly add 12 Mhos
        for (int m = 0; m < NUM_MHOS; m++) {
            int x = randomBoardInt();
            int y = randomBoardInt();

            while (board[x][y] != null) {
                x = randomBoardInt();
                y = randomBoardInt();
            }

            board[x][y] = new Mho();
        }

        //randomly add a player
        int x = randomBoardInt();
        int y = randomBoardInt();

        while (board[x][y] != null) {
            x = randomBoardInt();
            y = randomBoardInt();
        }

        playerRow = x;
        playerCol = y;
        board[x][y] = new Player();

        try {
            fence = ImageIO.read(new File("Fence.png"));
            mho = ImageIO.read(new File("Mho.png"));
            player = ImageIO.read(new File("Player.png"));
        }
        catch (IOException e) {
            System.out.println("Image files not found");
            throw e;
        }
    }

    public void updateBoard(char keyPressed) {
        turns++;

        boolean moveMhos = playerMove(keyPressed);

        //loops through the board and for every Mho and move it
        if (moveMhos) {
            for (int i = 1; i < board.length - 1; i++) {
                for (int j = 1; j < board[i].length - 1; j++) {
                    if (board[i][j] != null) {
                        if (board[i][j].toString().equals("M")) {
                            mhoMove(i, j);
                        }
                    }
                }
            }
        }

    }


    public boolean playerMove(char direction) {

        boolean moveMhos = true;
        board[playerRow][playerCol] = null;

        switch (direction) {
            case 'q':
                playerCol--;
                playerRow--;
                break;
            case 'w':
                playerRow--;
                break;
            case 'e':
                playerCol++;
                playerRow--;
                break;
            case 'a':
                playerCol--;
                break;
            case 's':
                break;
            case 'd':
                playerCol++;
                break;
            case 'z':
                playerCol--;
                playerRow++;
                break;
            case 'x':
                playerRow++;
                break;
            case 'c':
                playerCol++;
                playerRow++;
                break;
            case 'j':
                playerCol = randomBoardInt();
                playerRow = randomBoardInt();
                moveMhos = false;
                break;
            default:
                moveMhos = false;
                turns--;
                break;
        }

        // if new cell is occupied it can only be a Mho or Fence, hence player loses.
        if (board[playerRow][playerCol] != null) {
                 lose();
        }
        else {
            board[playerRow][playerCol] = new Player();
        }
        System.out.println("Player moved to {"+playerRow+","+playerCol+"}");
        return moveMhos;
    }

    public void mhoMove(int row, int column) {
        int newRow = row;
        int newCol = column;

        // MOVEMENT PART 1
        // 1. if the mho is on the same x-value but lower in height than the player
        // 2. if the mho is on the same x-value but higher in height than the player
        // 3. if the mho is on the same y-value but higher in width than the player
        // 4. if the mho is on the same y-value but lower in width than the player

        if (playerCol == column && playerRow > row) {
            newCol = column;
            newRow = row + 1;
        } else if (playerCol == column && playerRow < row) {
            newCol = column;
            newRow = row - 1;
        } else if (playerCol > column && playerRow == row) {
            newCol = column + 1;
            newRow = row;
        } else if (playerCol < row && playerRow == row) {
            newCol = column - 1;
            newRow = row;
        }

        // MOVEMENT PART 2 - 1
        // 1. if the mho is directly diagonal and must go northeast
        // 2. if the mho is directly diagonal and must go southeast
        // 3. if the mho is directly diagonal and must go southwest
        // 4. if the mho is directly diagonal and must go northwest

        if (playerCol - column == 0 - (playerRow - row) && playerCol - column > 0) {
            newCol = column + 1;
            newRow = row - 1;
        } else if (playerCol - column == playerRow - row && playerCol - column > 0) {
            newCol = column + 1;
            newRow = row + 1;
        } else if (playerCol - column == 0 - (playerRow - row) && playerCol - column < 0) {
            newCol = column - 1;
            newRow = row + 1;
        } else if (playerCol - column == playerRow - row && playerCol - column < 0) {
            newCol = column - 1;
            newRow = row - 1;
        }

        // MOTION PART 2 - 2
        // 1. if the mho's x-distance is greater than it's y distance and it must go northeast
        // 2. if the mho's x distance is lower than it's y distance and it must go northeast
        // 3. if the mho's x distance is greater than it's y distance and it must go southeast
        // 4. if the mho's x distance is lower than it's y distance and it must go southeast
        // 5. if the mho's x distance is greater than it's y distance and it must go southwest
        // 6. if the mho's x-distance is lower than it's y distance and it must go southwest
        // 7. if the mho's x-distance is greater than it's y distance and it must go northwest
        // 8. if the mho's x-distance is lower than it's y distance and it must go northwest

        //this area needs more work
        if(playerCol - column > 0 - (playerRow - row) && playerCol > column && playerRow < row) {
            newCol = column + 1;
            newRow = row;
            System.out.println("one");
        } else if(playerCol - column < 0 - (playerRow - row) && playerCol > column && playerRow < row) {
            newCol = column;
            newRow = row - 1;
            System.out.println("two");
        } else if(playerCol - column > playerRow - row && playerCol > column && playerRow > row) {
            newCol = column + 1;
            newRow = row;
            System.out.println("three");
        } else if(playerCol - column < playerRow - row && playerCol > column && playerRow > row) {
            newCol = column;
            newRow = row + 1;
            System.out.println("four");
        } else if(playerCol - column < 0 - (playerRow - row) && playerCol < column && playerRow > row) {
            newCol = column - 1;
            newRow = row;
            System.out.println("five");
        } else if(playerCol - column > 0 - (playerRow - row) && playerCol < column && playerRow > row) {
            newCol = column;
            newRow = row + 1;
            System.out.println("six");
        } else if(playerCol - column < playerRow - row && playerCol < column && playerRow < row) {
            newCol = column - 1;
            newRow = row;
            System.out.println("seven");
        } else if(playerCol - column > playerRow - row && playerCol < column && playerRow < row) {
            newCol = column;
            newRow = row - 1;
            System.out.println("eight");
        }


        if(board[newRow][newCol] == null) {
            board[row][column] = null;
            board[newRow][newCol] = new Mho();
        } else if(board[newRow][newCol].toString().equals("F")) {
            board[row][column] = null;
        } else if(board[newRow][newCol].toString().equals("P")) {
            lose();
        }
    }

    private void lose() {
        System.out.println("You lose");
    }

    //find a random number between 1 and 10 (index of board is 0 to 11 - excludes outsides of board)
    public int randomBoardInt() {
        return (int) (Math.random() * (BOARD_SIZE - 2)) + 1;
    }


    public Item[][] getBoard() {
        return board;
    }


    public void paintBoard(Graphics g) {
        BufferedImage img = fence;
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].toString().equals("F")) {
                        img = fence;
                    }
                    if (board[i][j].toString().equals("M")) {
                        img = mho;
                    }
                    if (board[i][j].toString().equals("P")) {
                        img = player;
                    }
                    g.drawImage(img, j * 60, i * 60, 60, 60, null);
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].toString());
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }

}
