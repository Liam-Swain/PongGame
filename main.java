import java.awt.*;
import java.util.Scanner;

public class Main {
    public static final Scanner scnr = new Scanner(System.in);
    public static void main(String[] args){
        Jframe f = new Jframe();
        thread t = new thread();
        thread t2 = new thread();

        System.out.println("Enter player ones name");
        String name1 = scnr.nextLine();
        f.panel.b.setplayer1Name(name1);
        System.out.println("Enter players two name");
        String name2 = scnr.nextLine();
        f.panel.b.setplayer2Name(name2);

        while(true) {
            t2.run();
            if(f.panel.b.getStartGame()) {
                while (true) {
                    t.setX(1000 / 60);
                    t.run();
                    f.panel.update();
                    f.panel.collision();
                }
            }
        }


    }

}
