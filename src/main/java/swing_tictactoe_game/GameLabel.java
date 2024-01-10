package swing_tictactoe_game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;



public class GameLabel extends JLabel {
    public GameLabel () {

            setText("Welcome to TicTacToe");
            setFont(new Font("Comic Sans MS", Font.BOLD, 70));
            setHorizontalAlignment(SwingConstants.CENTER);
            setPreferredSize(new Dimension(400, 50));
            setBackground(Color.orange);
            setOpaque(true);
        }
    }

