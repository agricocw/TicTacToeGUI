import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private JButton[][] buttons;
    private TicTacToeRunner game;

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game = new TicTacToeRunner();

        JPanel panel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        ButtonListener listener = new ButtonListener();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
                buttons[row][col].addActionListener(listener);
                panel.add(buttons[row][col]);
            }
        }

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        add(panel, BorderLayout.CENTER);
        add(quitButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (e.getSource() == buttons[row][col]) {
                        if (game.makeMove(row, col)) {
                            buttons[row][col].setText(game.getCurrentPlayer());
                            if (game.checkWin()) {
                                JOptionPane.showMessageDialog(null, game.getCurrentPlayer() + " wins!");
                                game.reset();
                                resetButtons();
                            } else if (game.checkTie()) {
                                JOptionPane.showMessageDialog(null, "It's a tie!");
                                game.reset();
                                resetButtons();
                            } else {
                                game.switchPlayer();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid move. Try again.");
                        }
                    }
                }
            }
        }
    }

    private void resetButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeFrame::new);
    }
}

