package swing_tictactoe_game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

class TicTacToe {
    private final JFrame frame = new JFrame();
    private final JPanel buttonPanel = new JPanel();
    private final JPanel functionPanel = new JPanel();
    private GameLabel textField = new GameLabel();
    private final JButton [] buttons = new JButton[9];
    private final JPanel titlePanel = new JPanel();
    private final JButton resetButton = new JButton("Reset");
    private final JButton exitButton = new JButton("Exit");
    private final JButton styleButton = new JButton("Style");
    private final Winner winner;
    private int currentStyleIndex;
    private int currentSoundIndex;
    private int choice;
    private Clip clip;
    private AudioInputStream audioInputStream;

    public TicTacToe() {
        //initial music background
        playSound( "C:\\Users\\Valery Chumakou\\IdeaProjects\\Swing_TicTacToe_Game\\src\\main\\resources\\japanese.wav");

        //pop up window when clicking close button
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(null);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to close the program?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        frame.setVisible(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);
        titlePanel.add(textField);

        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBounds(0, 100, 800, 600);

        //functional buttons
        resetButton.setFocusable(false);
        resetButton.setFont(new Font("Comic Sans MS", Font.BOLD,15));
        resetButton.addActionListener (e -> {
            String [] options = {"0", "X"};
            choice = JOptionPane.showOptionDialog(frame, "Choose your token", "Token Selection",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
                    options[0]);

            resetGame();
         });

        exitButton.setFocusable(false);
        exitButton.setFont(new Font("Comic Sans MS", Font.BOLD,12));
        exitButton.addActionListener(e -> System.exit(0));

        styleButton.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        styleButton.setFocusable(false);
        styleButton.addActionListener(e -> changeBackgroundStyle());

        functionPanel.setLayout(new FlowLayout());
        functionPanel.setBounds(0, 700, 800, 100);
        functionPanel.setBackground(Color.PINK);
        functionPanel.setOpaque(true);
        functionPanel.add(resetButton);
        functionPanel.add(exitButton);
        functionPanel.add(styleButton);

        winner = new Winner(buttons, textField);

        //add buttons to JFrame
        frame.add(titlePanel);
        frame.add(buttonPanel);
        frame.add(functionPanel);

        //draw O or X
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Comic Sans MS", Font.BOLD, 140));
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
                        winner.checkX();
                        winner.checkO();
                    }
                 }
            });
        }


        //option panel for choosing token
        String[] options = {"O", "X"};
        choice = JOptionPane.showOptionDialog(frame, "Choose your token", "Token Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }


    // method for changing background color and sound
    private void changeBackgroundStyle() {
        //Define the colors for different styles
        Color [] colors = {
                Color.GREEN,
                Color.CYAN,
                Color.MAGENTA,
                Color.LIGHT_GRAY

        };

        currentStyleIndex++;
        if (currentStyleIndex == colors.length) {
            currentStyleIndex = 0;
        }
        buttonPanel.setBackground(colors[currentStyleIndex]);
        for (JButton button : buttons) {
            button.setBackground(colors[currentStyleIndex]);
        }

        //Define the sound file paths
        String [] soundPaths = new String [] {"C:\\Users\\Valery Chumakou\\IdeaProjects\\Swing_TicTacToe_Game\\src\\main\\resources\\piano.wav" ,
                "C:\\Users\\Valery Chumakou\\IdeaProjects\\Swing_TicTacToe_Game\\src\\main\\resources\\guitar.wav", "C:\\Users\\Valery Chumakou\\IdeaProjects\\Swing_TicTacToe_Game\\src\\main\\resources\\brass.wav",
                "C:\\Users\\Valery Chumakou\\IdeaProjects\\Swing_TicTacToe_Game\\src\\main\\resources\\japanese.wav"};

        currentSoundIndex++;
        if (currentSoundIndex==soundPaths.length) {
            currentSoundIndex=0;
        }

        String[] options = {"O", "X"};
        choice = JOptionPane.showOptionDialog(frame, "Choose your token", "Token Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        playSound (soundPaths[currentSoundIndex]);
        resetGame();
    }

    private void playSound (String filePath) {
        try {
            File file = new File (filePath);
            if (file.exists()) {
                if (clip!=null && clip.isRunning()) {
                    clip.stop();
                    clip.close();
                }
                audioInputStream = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } else {
                System.err.println("Sound file not found " + filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //using for clicking on the button and reset game board to default
    private void resetButton () {
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
        winner.reset();

    }

    //method to reset game board to default
    private void resetGame() {
        for (JButton button : buttons) {
            resetButton();
            button.setText("");
            button.setEnabled(true);

        }
        winner.reset();
        textField.setText("Welcome to TicTicToe");
        if (choice == 0) {
            textField.setText("O Turn");
        } else {
            textField.setText("X Turn");
        }
    }
}

