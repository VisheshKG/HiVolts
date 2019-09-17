public class Board {

    private final int BOARD_SIZE = 12;
    private final int NUM_FENCES = 20;
    private final int NUM_MHOS = 12;
    private Item[][] board = new Item[BOARD_SIZE][BOARD_SIZE];

    public Board() {
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
            int x = (int) Math.random() * (BOARD_SIZE - 2) + 1;
            int y = (int) Math.random() * (BOARD_SIZE - 2) + 1;

            while (board[x][y] != null) {
                x = (int) Math.random() * (BOARD_SIZE - 2) + 1;
                y = (int) Math.random() * (BOARD_SIZE - 2) + 1;
            }

            board[x][y] = new Fence();
        }

        //randomly add 12 Mhos
        for (int m = 0; m < NUM_MHOS; m++) {
            int x = (int) Math.random() * (BOARD_SIZE - 2) + 1;
            int y = (int) Math.random() * (BOARD_SIZE - 2) + 1;

            while (board[x][y] != null) {
                x = (int) Math.random() * (BOARD_SIZE - 2) + 1;
                y = (int) Math.random() * (BOARD_SIZE - 2) + 1;
            }

            board[x][y] = new Mho();
        }

        //randomly add a player
        int x = (int) Math.random() * (BOARD_SIZE - 2) + 1;
        int y = (int) Math.random() * (BOARD_SIZE - 2) + 1;

        while (board[x][y] != null) {
            x = (int) Math.random() * (BOARD_SIZE - 2) + 1;
            y = (int) Math.random() * (BOARD_SIZE - 2) + 1;
        }

        board[x][y] = new Player();
    }

    public void update() {

    }
  
    public int[] playerMove(char direction) {
        int x = 0;
        int y = 0;

        for (int i = 1; i < board.length - 1; i++)  {
            for (int j = 1; j < board[i].length - 1; j++) {
                if (board[i][j].toString().equals("P"));
                x = i;
                y = j;
            }
        }

        switch (direction) {
            case 'q':
                x--;
                y++;
                break;
            case 'w':
                break;
            case 'e':
                break;
            case 'a':
                break;
            case 's':
                break;
            case 'd':
                break;
            case 'z':
                break;
            case 'x':
                break;
            case 'c':
                break;
            case 'j':
                break;
        }
    }

    public int[] mhoMove(int mhoX, int mhoY, int playerX, int playerY) {
        int[] coords = new int[2];
    /*
    1. if the mho is on the same x-value but lower in height than the player
    2. if the mho is on the same x-value but higher in height than the player
    3. if the mho is on the same y-value but higher in width than the player
    4. if the mho is on the same y-value but lower in width than the player
    */
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
    /*
    1. if the mho is directly diagonal and must go northeast
    2. if the mho is directly diagonal and must go southeast
    3. if the mho is directly diagonal and must go southwest
    4. if the mho is directly diagonal and must go northwest
    */
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
    /*
    1. if the mho's x-distance is greater than it's y distance and it must go northeast
    2. if the mho's x distance is lower than it's y distance and it must go northeast
    3. if the mho's x distance is greater than it's y distance and it must go southeast
    4. if the mho's x distance is lower than it's y distance and it must go southeast
    5. if the mho's x distance is greater than it's y distance and it must go southwest
    6. if the mho's x-distance is lower than it's y distance and it must go southwest
    7. if the mho's x-distance is greater than it's y distance and it must go northwest
    8. if the mho's x-distance is lower than it's y distance and it must go northwest
    */
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

        return coords;
    }


    public Item[][] getBoard() {
        return board;
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
