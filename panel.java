import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.*;

public class panel extends JPanel implements KeyListener{

    thread t = new thread();
    double MAX_REBOUND = Math.toRadians(70.0);
    double speed = 5;
    paddle right = new paddle(1485, 325);
    paddle left = new paddle(0, 325);

    ball b = new ball(750, 350);

    public panel(int width,int height){
        super();
        width = 1500;
        height = 750;
        setSize(width, height);
        addKeyListener(this);
        setVisible(true);
    }

    public void paint(Graphics g){
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



    }

    public void update(){
       left.update();
       right.update();
       b.update();
       repaint();
    }

    public void collision(){
        if(b.start == 0){
            b.start++;
            b.ballvX = -5;
        }



        if(b.getX() >= right.getX() - 15 - speed && b.getY() <= right.getY() + right.getHeight() && b.getY() >= right.getY()){
            b.intersectY = (right.getY() + (right.getHeight() / 2) -b.getY());
            b.normalizedIntersection = (b.intersectY/(right.getHeight()/2));
            if(b.normalizedIntersection >= 0.75 || b.normalizedIntersection <= -0.75)
                speed += 3;
            else if(b.normalizedIntersection >= 0.50 && b.normalizedIntersection < 0.75 || b.normalizedIntersection <= -0.5 && b.normalizedIntersection > -0.75)
                speed += 2;
            else if(b.normalizedIntersection >= 0.25 && b.normalizedIntersection < 0.50 || b.normalizedIntersection <= -0.25 && b.normalizedIntersection > -0.50)
                speed += 1.5;
            b.bounce = b.normalizedIntersection * MAX_REBOUND;
            b.ballvX = speed * -Math.cos(b.bounce);
            b.ballvY = speed * Math.sin(b.bounce);
        }

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

        if(b.getY() + 25 + b.ballvY  >= getHeight() - 2){
            b.ballvY = -b.ballvY;

        }

        if(b.getY() - b.ballvY <= 0){
            b.ballvY = speed;
                    //* Math.sin(b.bounce);
        }




        if(b.getX() + speed <= 0 || b.getX() < 0){
            b.ballvY = 0;
            b.ballvX = -5;
            speed = 5;
            t.run();
            b.reset();
            b.setp2Score(++b.p2Score);
        }

        if(b.getX() + 25 - speed >= 1500 || b.getX() > 1500){
            b.ballvY = 0;
            b.ballvX = 5;
            speed = 5;
            t.run();
            b.reset();
            b.setp1Score(++b.p1Score);
        }

    }




    @Override
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

        if(e.getKeyCode() == KeyEvent.VK_1){

            right.getBigPowerUp();
        }

    }

    @Override
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
