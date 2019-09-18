import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board {

    public static final int BOARD_SIZE = 12;
    public static final int NUM_FENCES = 20;
    public static final int NUM_MHOS = 12;

    private int playerX;
    private int playerY;

    private Item[][] board = new Item[BOARD_SIZE][BOARD_SIZE];

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

        playerX = x;
        playerY = y;
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

    public void update(char keyPressed) {
        playerMove(keyPressed);

    }


    public void playerMove(char direction) {

        board[playerX][playerY] = null;

        switch (direction) {
            case 'q':
                playerX--;
                playerY--;
                break;
            case 'w':
                playerY--;
                break;
            case 'e':
                playerX++;
                playerY--;
                break;
            case 'a':
                playerX--;
                break;
            case 'd':
                playerX++;
                break;
            case 'z':
                playerX--;
                playerY++;
                break;
            case 'x':
                playerY++;
                break;
            case 'c':
                playerX++;
                playerY++;
                break;
            case 'j':
                playerX = randomBoardInt();
                playerY = randomBoardInt();
                break;
            default:
                break;
        }
        if (board[playerX][playerY] != null) {
            if (board[playerX][playerY].toString().equals("F")) {
                lose();
            }
            else if (board[playerX][playerY].toString().equals("M")) {
                lose();
            }
            else if (board[playerX][playerY].toString().equals("P")) {
                board[playerX][playerY] = new Player();
            }
        }
        else {
            board[playerX][playerY] = new Player();
        }


    }

    public void mhoMove(int mhoX, int mhoY, int playerX, int playerY) {
        int[] coords = new int[2];

    // 1. if the mho is on the same x-value but lower in height than the player
    // 2. if the mho is on the same x-value but higher in height than the player
    // 3. if the mho is on the same y-value but higher in width than the player
    // 4. if the mho is on the same y-value but lower in width than the player

        if (playerX == mhoX && playerY > mhoY) {
            coords[0] = mhoX;
            coords[1] = mhoY + 1;
        } else if (playerX == mhoX && playerY < mhoY) {
            coords[0] = mhoX;
            coords[1] = mhoY - 1;
        } else if (playerX > mhoX && playerY == mhoY) {
            coords[0] = mhoX + 1;
            coords[1] = mhoY;
        } else if (playerX < mhoY && playerY == mhoY) {
            coords[0] = mhoX - 1;
            coords[1] = mhoY;
        }

        // 1. if the mho is directly diagonal and must go northeast
        // 2. if the mho is directly diagonal and must go southeast
        // 3. if the mho is directly diagonal and must go southwest
        // 4. if the mho is directly diagonal and must go northwest

        if (playerX - mhoX == 0 - (playerY - mhoY) && playerX - mhoX > 0) {
            coords[0] = mhoX + 1;
            coords[1] = mhoY - 1;
        } else if (playerX - mhoX == playerY - mhoY && playerX - mhoX > 0) {
            coords[0] = mhoX + 1;
            coords[1] = mhoY + 1;
        } else if (playerX - mhoX == 0 - (playerY - mhoY) && playerX - mhoX < 0) {
            coords[0] = mhoX - 1;
            coords[1] = mhoY + 1;
        } else if (playerX - mhoX == playerY - mhoY && playerX - mhoX < 0) {
            coords[0] = mhoX - 1;
            coords[1] = mhoY - 1;
        }

        // 1. if the mho's x-distance is greater than it's y distance and it must go northeast
        // 2. if the mho's x distance is lower than it's y distance and it must go northeast
        // 3. if the mho's x distance is greater than it's y distance and it must go southeast
        // 4. if the mho's x distance is lower than it's y distance and it must go southeast
        // 5. if the mho's x distance is greater than it's y distance and it must go southwest
        // 6. if the mho's x-distance is lower than it's y distance and it must go southwest
        // 7. if the mho's x-distance is greater than it's y distance and it must go northwest
        // 8. if the mho's x-distance is lower than it's y distance and it must go northwest

        //this area needs more work
        if(playerX - mhoX > 0 - (playerY - mhoY) && playerX > mhoX && playerY < mhoY) {
            coords[0] = mhoX + 1;
            coords[1] = mhoY;
            System.out.println("one");
        } else if(playerX - mhoX < 0 - (playerY - mhoY) && playerX > mhoX && playerY < mhoY) {
            coords[0] = mhoX;
            coords[1] = mhoY - 1;
            System.out.println("two");
        } else if(playerX - mhoX > playerY - mhoY && playerX > mhoX && playerY > mhoY) {
            coords[0] = mhoX + 1;
            coords[1] = mhoY;
            System.out.println("three");
        } else if(playerX - mhoX < playerY - mhoY && playerX > mhoX && playerY > mhoY) {
            coords[0] = mhoX;
            coords[1] = mhoY + 1;
            System.out.println("four");
        } else if(playerX - mhoX < 0 - (playerY - mhoY) && playerX < mhoX && playerY > mhoY) {
            coords[0] = mhoX - 1;
            coords[1] = mhoY;
            System.out.println("five");
        } else if(playerX - mhoX > 0 - (playerY - mhoY) && playerX < mhoX && playerY > mhoY) {
            coords[0] = mhoX;
            coords[1] = mhoY + 1;
            System.out.println("six");
        } else if(playerX - mhoX < playerY - mhoY && playerX < mhoX && playerY < mhoY) {
            coords[0] = mhoX - 1;
            coords[1] = mhoY;
            System.out.println("seven");
        } else if(playerX - mhoX > playerY - mhoY && playerX < mhoX && playerY < mhoY) {
            coords[0] = mhoX;
            coords[1] = mhoY - 1;
            System.out.println("eight");
        }
        if(board[coords[0]][coords[1]] == null) {
            board[mhoX][mhoY] = null;
            board[coords[0]][coords[1]] = new Mho();
        } else if(board[coords[0]][coords[1]].toString().equals("F")) {
            board[mhoX][mhoY] = null;
        } else if(board[coords[0]][coords[1]].toString().equals("P")) {
            lose();
        }
    }

    private void lose() {

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
