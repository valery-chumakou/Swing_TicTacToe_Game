package swing_tictactoe_game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
 

class TicTacToe {
    private final JFrame frame = new JFrame();
    private final JPanel buttonPanel = new JPanel();
    private final JPanel functionPanel = new JPanel();
    private final GameLabel textField = new GameLabel();
    private final JButton [] buttons = new JButton[9];
    private final JPanel titlePanel = new JPanel();
    private final JButton resetButton = new JButton("Reset");        
    private final JButton exitButton = new JButton("Exit");
    private final JButton styleButton = new JButton("Style");
    private int choice;

    public TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(null);
        frame.setVisible(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);
        titlePanel.add(textField);


        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBounds(0, 100, 800, 600);

        //functional buttons
        resetButton.setFocusable(false);
        resetButton.setFont(new Font("MV Boli", Font.ITALIC,12));
       
        exitButton.setFocusable(false);
        exitButton.setFont(new Font("MV Boli", Font.ITALIC,12));
        exitButton.addActionListener(e -> System.exit(0));

        styleButton.setFont(new Font("MV Boli", Font.ITALIC, 12));
        styleButton.setFocusable(false); 
        
        functionPanel.setLayout(new FlowLayout());
        functionPanel.setBounds(0, 700, 800, 100);
        functionPanel.setBackground(Color.pink);
        functionPanel.setOpaque(true);
        functionPanel.add(resetButton);
        functionPanel.add(exitButton);
        functionPanel.add(styleButton);
       

        //add buttons to JFrame
        frame.add(titlePanel);
        frame.add(buttonPanel);
        frame.add(functionPanel);
        
         
        
        

 
        //draw O or X
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.ITALIC, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
                    int index;
                    for (index = 0; index < 9; index++) {
                        if (buttons[index] == clickedButton) {
                            break;
                        }
                    }

                    if (clickedButton.getText().isEmpty()) {
                        if (choice == 0) {
                            buttons[index].setText("O");
                            buttons[index].setForeground(new Color(255, 0, 0));
                            textField.setText("X Turn");
                            choice = 1;
                        } else {
                            buttons[index].setText("X");
                            buttons[index].setForeground(new Color(0, 0, 255));
                            textField.setText("O Turn");
                            choice = 0;
                        }
                        
                    }
                }
            });
        }


        //option panel for choosing token
        String[] options = {"O", "X"};
        choice = JOptionPane.showOptionDialog(frame, "Choose your token", "Token Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

    }
}