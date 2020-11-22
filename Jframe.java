import javax.swing.*;
import java.awt.*;


public class Jframe extends JFrame { // this creates a class that acts as a JFrame

    public final int height = 750; // sets the height of the screen
    public final int width = 1500; // sets the width of the screen

    public panel panel = new panel(width, height); // makes a panel


    public Jframe() { // jframe constructor, adds key listener to panel
        super("PONG");
        panel.setBackground(Color.BLACK);
        add(panel);
        addKeyListener(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pong Game");
        setResizable(false);
        setContentPane(panel);
        getContentPane().setPreferredSize(new Dimension(width, height));
        pack();


        setVisible(true);
    }
