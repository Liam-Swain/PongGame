import java.awt.*;
import java.lang.*;

public class thread implements Runnable{ // main thread that the main program runs on

    int x = 0;

    @Override
    public void run() {
        try{
            Thread.sleep(getX());
        }catch(Exception e){
            System.out.println("Why");
        }

    }

    public void setX(int x){
        this.x = x;
    }

    public int getX(){
        return x;
    }


}
