
public class paddle{ // paddle class
    public int x, y, direct;
    public  int width = 15;
    public  int height = 100;
    public  final int UP = 0;
    public  final int DOWN = 1;
    public  final int NOT_MOVE = 2;


    public paddle(int x, int y){ // paddle constructors, sets the x, y coordinate, and the direction
        this.x = x;
        this.y = y;
        this.direct = 2;
    }

    public void update(){ // updates the paddles

        if(direct == UP){
            setY(y - 5);
        }

        if(direct == DOWN){
            setY( y + 5);
        }
    }



    public void getBigPowerUp(){
        setHeight(getHeight() * 2);

    }

    // the rest of these function are all setters and getters for various variables
    public void getSmallPower(){
        setHeight(getHeight() / 2);
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
