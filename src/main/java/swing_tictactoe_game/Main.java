package swing_tictactoe_game;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Main {
    public static void main (String [] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            TicTacToe tictactoe = new TicTacToe();
        } catch (Exception e) {
            System.out.println ("Failed");
        }
     }
}