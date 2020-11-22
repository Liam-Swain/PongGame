import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.*;
import java.io.FileWriter;
import java.io.File;


public class panel extends JPanel implements KeyListener{ // this is the panel class
    int tempVar = 0; // this variable is used for power ups
    boolean p2ExtraRect = false; // used for a power up
    int games = 1; // counts the games played
    File gameScores = new File("D:\\FileNames\\gameScores.txt"); // this makes a file in the specified location

    thread t = new thread(); // makes an object for the thread class


    Thread t2;

    double MAX_REBOUND = Math.toRadians(70.0); // this sets the max rebound of the paddle to 70
    double speed = 5; // init speed
    paddle right = new paddle(1485, 325); // right paddle
    paddle left = new paddle(0, 325); // left paddle

    ball b = new ball(750, 350); // makes a ball





    public panel(int width,int height){ // constructor for panel, sets width and height
        super();

        width = 1500;
        height = 750;
        setSize(width, height);
        addKeyListener(this);
        setVisible(true);
    }

    public void paint(Graphics g){ // paints the graphics of the game, like the paddles and ball
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.fillRect(right.getX(), right.getY(), right.getWidth(), right.getHeight());
        g.fillRect(left.getX(), left.getY(), left.getWidth(), left.getHeight());
        g.fillOval(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        g.drawString("Pong Game", 700, 50);

        g.drawString("Player One: " + b.getplayer1Name() + " " + b.getp1Score(), 50, 200);
        g.drawString("Player Two: " + b.getplayer2Name() + " " + b.getp2Score(), 1200, 200);

        g.drawString("Press n to start", 700, 100);

        if(p2ExtraRect){ // if the player uses this power up
            g.fillRect(800, 0, 15, 750);
            if(b.getX() + 25 + b.ballvX >= 800){
                b.ballvX = -b.ballvX;
                b.ballvY = -b.ballvY;
            }
        }



    }

    void endGame(){ // if a players score gets to 11
        if(b.p1Score == 11 || b.p2Score == 11){
            b.setStartGame(!b.getStartGame());
            try{ // thsi writes the file the final score of the game.
                FileWriter write = new FileWriter("D:\\FileNames\\gameScores.txt");
                write.write("\nGame " + games++ + "\n player 1 Score: " + b.getp1Score()
                        + "\n player 2 Score: " + b.getp2Score());
                write.write(System.getProperty( "line.separator" ));
                write.close();
            }catch(Exception e) {
            }

            b.setp1Score(0); // reset the score if they want to playr again
            b.setp2Score(0);

        }

    }

    public void update(){ // this updates the coordinates of the paddles and ball, as well as checks for end game
       left.update();
       right.update();
       b.update();
       repaint();
       endGame();
    }


    public void collision(){ // this gets the collision
        if(b.start == 0){
            b.start++;
            b.ballvX = -5;
        }


        // checks for the right ball collision
        if(b.getX() >= right.getX() - 15 - speed && b.getY() <= right.getY() + right.getHeight() && b.getY() >= right.getY()){
            b.intersectY = (right.getY() + (right.getHeight() / 2) -b.getY());
            b.normalizedIntersection = (b.intersectY/(right.getHeight()/2)); // normalizes the ball intersection to be between - 1 and 1
            if(b.normalizedIntersection >= 0.75 || b.normalizedIntersection <= -0.75)
                speed += 3; // adds 3 to the speed if it hits near the top of bottom
            else if(b.normalizedIntersection >= 0.50 && b.normalizedIntersection < 0.75 || b.normalizedIntersection <= -0.5 && b.normalizedIntersection > -0.75)
                speed += 2;// adds 2 if it hits between the middle and the top or bottom
            else if(b.normalizedIntersection >= 0.25 && b.normalizedIntersection < 0.50 || b.normalizedIntersection <= -0.25 && b.normalizedIntersection > -0.50)
                speed += 1.5; // adds 1.5 if its the middle
            b.bounce = b.normalizedIntersection * MAX_REBOUND;
            b.ballvX = speed * -Math.cos(b.bounce);
            b.ballvY = speed * Math.sin(b.bounce);
        }

        //this does the same thing, except for the left paddle
        if(b.getX() <= left.getX() + speed + 15 && b.getY() <= left.getY() + left.getHeight() && b.getY() >= left.getY()){
            b.intersectY = (left.getY() + (left.getHeight() / 2) -b.getY());
            b.normalizedIntersection = (b.intersectY/(left.getHeight()/2));

            if(b.normalizedIntersection >= 0.75 || b.normalizedIntersection <= -0.75)
                speed += 3;
            else if(b.normalizedIntersection >= 0.50 && b.normalizedIntersection < 0.75 || b.normalizedIntersection <= -0.5 && b.normalizedIntersection > -0.75)
                speed += 2;
            else if(b.normalizedIntersection >= 0.25 && b.normalizedIntersection < 0.50 || b.normalizedIntersection <= -0.25 && b.normalizedIntersection > -0.50)
                speed += 1.5;
            b.bounce = b.normalizedIntersection * MAX_REBOUND;
            b.ballvX = speed * Math.cos(b.bounce);
            b.ballvY = speed * -Math.sin(b.bounce);
        }

        // checks to see if it hits the bottom of the panel
        if(b.getY() + 25 + b.ballvY  >= getHeight() - 2){
            b.ballvY = -b.ballvY;

        }

        // checks to see if it hits the top of the panel
        if(b.getY() - b.ballvY <= 0){
            b.ballvY = Math.abs(b.ballvY);
        }



        // this adds a point for the left paddle if it hits the right wall
        if(b.getX() + speed <= 0 || b.getX() < 0){
            b.ballvY = 0;
            b.ballvX = -5;
            speed = 5; // resets the speed
            t.run();
            b.reset(); // this resets the ball in the middle
            b.setp2Score(++b.p2Score); // this adds a point
        }

        //this adds a point to the right paddle if it hits the left wall
        if(b.getX() + 25 - speed >= 1500 || b.getX() > 1500){
            b.ballvY = 0;
            b.ballvX = 5;
            speed = 5; // resets the speed
            t.run();
            b.reset();
            b.setp1Score(++b.p1Score);
        }

    }




    @Override // This gets where the user wants move the paddle, as well as power ups
    public void keyReleased(KeyEvent e){
        if(e.getKeyChar() == 'w'){
            left.setDirect(left.NOT_MOVE);
        }

        if(e.getKeyChar() == 's'){
            left.setDirect(left.NOT_MOVE);
        }

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            right.setDirect(right.NOT_MOVE);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            right.setDirect(right.NOT_MOVE);
        }

        // first power up, it runs on a thread with a 10 second wait, last 5 seconds
        if(e.getKeyCode() == KeyEvent.VK_1){

            if(tempVar == 0) {
                tempVar = 1;
                Thread t2 = new Thread(new Runnable() {
                    public void run() {
                        try {
                            right.getBigPowerUp();
                            Thread.sleep(5000);
                            right.getSmallPower();
                            Thread.sleep(10000);
                            tempVar = 0;
                        } catch (Exception e) {
                        }
                    }});

                t2.start();
            }





        }
        //second power up, it has a 9 second cool down
        if(e.getKeyCode() == KeyEvent.VK_2){
            if(tempVar == 0){
                tempVar = 1;
                Thread t2 = new Thread(new Runnable(){public void run(){try{
                    b.ballvX = -b.ballvX;
                    b.ballvY = -b.ballvY;

                    Thread.sleep(9000);
                    tempVar = 0;
                }catch(Exception e){

                }
                }});
                t2.start();
            }
        }

        // this power up has a 5 second cool down
        if(e.getKeyCode() == KeyEvent.VK_3){
            if(tempVar == 0){
                tempVar = 1;
                Thread t2 = new Thread(new Runnable(){public void run(){try{
                    int dist;

                    if(b.getX() > 750){
                        dist = (b.getX() - 750);
                        b.setX(750 - dist);
                        b.ballvX = -b.ballvX;
                        b.ballvY = -b.ballvY;
                    }

                    else if(b.getX() < 750){
                        dist = (750 - b.getX());
                        b.setX(750 + dist);
                        b.ballvX = -b.ballvX;
                        b.ballvY = -b.ballvY;
                    }

                    Thread.sleep(5000);
                    tempVar = 0;

                }catch(Exception e){

                }
                }});

                t2.start();
            }
        }


        // this power up lasts for 5 sec, and has a 15 second cool down
        if(e.getKeyCode() == KeyEvent.VK_4){
            if(tempVar == 0){

                Thread t2 = new Thread(new Runnable(){public void run(){try{

                    p2ExtraRect = true;
                    System.out.println(p2ExtraRect);
                    Thread.sleep(5000);
                    p2ExtraRect = false;
                    Thread.sleep(15000);
                    tempVar = 0;

                }catch(Exception e){

                }
                }});
                t2.start();

            }
        }

    }



    @Override
    // this starts or restarts a game
    public void keyTyped(KeyEvent e){
        if(e.getKeyChar() == 'n')
            b.setStartGame(!b.getStartGame());


    }

    @Override
    public void keyPressed(KeyEvent e){
        char x = e.getKeyChar();



        if(x == 'w'){
            left.setDirect(left.UP);
        }
        if(x == 's'){
            left.setDirect(left.DOWN);
        }

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            right.setDirect(right.UP);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            right.setDirect(right.DOWN);
        }



    }

}
