
package ponggame;

/**
 *
 * @author miz
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Paddle_One implements Runnable{
        //Global variable
    private int x ,y,XDirection;
    Rectangle paddle1;
    public Paddle_One(int x,int y){
        this.x = x;
        this.y = y;
        paddle1 = new Rectangle(this.x,this.y,200,20);
        //move the paddle using radndome mottion
        Random r = new Random();
        int randXDir = r.nextInt(2);
        //genrate randomely two number 0 and 1
        if(randXDir == 0){
            randXDir--;
            setXDirection(randXDir);
        }
        if(randXDir == 1){
            randXDir++;
            setXDirection(randXDir);
        }
        
    }
    //setter method for chang direction
    public void setXDirection(int Dir){
        XDirection = Dir;
    }
    //draw 
    public void drawPaddle(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect(paddle1.x, paddle1.y, paddle1.width, paddle1.height);
    }
    //method move
    public void move(){
       paddle1.x += XDirection;
      //collistion detection
      if(paddle1.x <= 0){
            setXDirection(+1);
        }
        if(paddle1.x >= 200){
            setXDirection(-1);
        }
    }
    //implements thread run method
    @Override
    public void run(){
        try{
            while(true){
                move();
                Thread.sleep(2,0);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());  
        }
    }
    
}

