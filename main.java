import java.awt.*;
import java.util.Scanner;



public class Main { // main method

    public static final Scanner scnr = new Scanner(System.in);
    public static void main(String[] args){
        Jframe f = new Jframe(); // makes the jframe object
        thread t = new thread(); // makes a thread object
        thread t2 = new thread();

        // this gets the players name
        System.out.println("Enter player ones name");
        String name1 = scnr.nextLine();
        f.panel.b.setplayer1Name(name1);
        System.out.println("Enter players two name");
        String name2 = scnr.nextLine();
        f.panel.b.setplayer2Name(name2);

        while(true) { // infinite while loop that constantly updates the game and checks for collisions
            t2.run(); // need to make the program wait, so the user is able to start the game
            if(f.panel.b.getStartGame()) {
                do{
                    t.setX(1000 / 60); // sets the sleep period
                    t.run();
                    f.panel.update(); // updates the drawing
                    f.panel.collision(); // checks for collision
                }while(f.panel.b.getStartGame());
            }
        }
    }

}
