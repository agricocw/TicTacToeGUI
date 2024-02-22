import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private JButton[][] buttons;
    private String[][] board;
    private String currentPlayer;

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        currentPlayer = "X";
        board = new String[3][3];

        JPanel panel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        ButtonListener listener = new ButtonListener();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
                buttons[row][col].addActionListener(listener);
                panel.add(buttons[row][col]);
                board[row][col] = " ";
            }
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (e.getSource() == buttons[row][col]) {
                        if (board[row][col].equals(" ")) {
                            buttons[row][col].setText(currentPlayer);
                            board[row][col] = currentPlayer;
                            if (checkWin()) {
                                JOptionPane.showMessageDialog(null, currentPlayer + " wins!");
                                handleEndOfGame();
                            } else if (checkTie()) {
                                JOptionPane.showMessageDialog(null, "It's a tie!");
                                handleEndOfGame();
                            } else {
                                switchPlayer();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid move. Try again.");
                        }
                    }
                }
            }
        }
    }

    private void reset() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                board[row][col] = " ";
            }
        }
        currentPlayer = "X";
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
    }

    private boolean checkWin() {

        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(currentPlayer) && board[i][1].equals(currentPlayer) && board[i][2].equals(currentPlayer)) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[0][i].equals(currentPlayer) && board[1][i].equals(currentPlayer) && board[2][i].equals(currentPlayer)) {
                return true;
            }
        }

        if (board[0][0].equals(currentPlayer) && board[1][1].equals(currentPlayer) && board[2][2].equals(currentPlayer)) {
            return true;
        }
        if (board[0][2].equals(currentPlayer) && board[1][1].equals(currentPlayer) && board[2][0].equals(currentPlayer)) {
            return true;
        }
        return false;
    }

    private boolean checkTie() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }
        return !checkWin();
    }

    private void handleEndOfGame() {
        int option = JOptionPane.showConfirmDialog(null, "Do you want to play another game?", "Play Again",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            reset();
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeFrame::new);
    }
}