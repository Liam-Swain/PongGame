import java.util.Scanner;

public class ball { // ball class
    public final Scanner scnr = new Scanner(System.in);
    int x, y, direct;
    public double ballvY, ballvX = 0;
    public double intersectY = 0;
    public double normalizedIntersection = 0;
    public double bounce = 0;
    public static boolean startGame = false;
    String player1Name = "";
    String player2Name = "";
    int width = 25;
    int height = 25;

    int p1Score, totalScore, p2Score, start = 0;



    public ball(int x, int y){ // ball constructor
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public void update(){ // updates the ball
        y += ballvY;
        x += ballvX;

    }

    public void reset(){ // this resets the ball in the middle when a player scores
        this.x = 750;
        this.y = 350;
    }


    // the rest of these functions are setters and getters for various variables
    public void setplayer1Name(String player1Name){
        this.player1Name = player1Name;
    }

    public String getplayer1Name(){
        return player1Name;
    }

    public void setplayer2Name(String player2Name) {this.player2Name = player2Name; }

    public String getplayer2Name(){
        return player2Name;
    }

    public void setStartGame(boolean startGame){
        this.startGame = startGame;
    }

    public boolean getStartGame(){
        return startGame;
    }


    public void setp1Score(int p1Score){
        this.p1Score = p1Score;
    }

    public int getp1Score(){
        return p1Score;
    }

    public void setp2Score(int p2Score){
        this.p2Score = p2Score;
    }

    public int getp2Score(){
        return p2Score;
    }

    public void setDirect(int direct){
        this.direct = direct;
    }

    public int getDirect(){
        return direct;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getX(){
        return x;
    }

    public void setY(int y){
        this.y = y;
    }
    public int getY(){
        return y;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public int getWidth(){
        return width;
    }
    public void setHeight(int height){
        this.height = height;
    }

    public int getHeight(){
        return height;
    }


}
