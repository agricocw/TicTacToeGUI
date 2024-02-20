public class TicTacToeRunner {
    private static final int ROW = 3;
    private static final int COL = 3;
    private String[][] board;
    private String currentPlayer;

    public TicTacToeRunner() {
        board = new String[ROW][COL];
        reset();
    }

    public void reset() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = " ";
            }
        }
        currentPlayer = "X";
    }

    public boolean makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public boolean checkWin() {
        return isRowWin() || isColWin() || isDiagonalWin();
    }

    public boolean checkTie() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col].equals(" ")) {
                    return false; // Found an empty cell, game is not a tie yet
                }
            }
        }
        return true; // All cells are filled, game is a tie
    }

    private boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private boolean isRowWin() {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(board[row][1]) && board[row][0].equals(board[row][2]) && !board[row][0].equals(" ")) {
                return true;
            }
        }
        return false;
    }

    private boolean isColWin() {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(board[1][col]) && board[0][col].equals(board[2][col]) && !board[0][col].equals(" ")) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin() {
        return (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals(" ")) ||
                (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].equals(" "));
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
    }
}


