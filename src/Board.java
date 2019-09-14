public class Board {

    private final int BOARD_SIZE = 12;
    private final int NUM_FENCES = 20;
    private final int NUM_MHOS = 12;
    private Item[][] board = new Item[BOARD_SIZE][BOARD_SIZE];

    public Board() {
        //Add fences down the left and right of the map
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i][0] = new Fence();
            board[i][BOARD_SIZE] = new Fence();
        }
        //Add fences accross the top and bottom of the map
        for (int j =  0; j < BOARD_SIZE; j++) {
            board[0][j] = new Fence();
            board[BOARD_SIZE][j] = new Fence();
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

    public void print() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].toString());
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
