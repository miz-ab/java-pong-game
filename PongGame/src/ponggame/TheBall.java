/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;

/**
 *
 * @author miz
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.JOptionPane;


public class TheBall implements Runnable{
    int x , y,XDirection,YDirection;
    Rectangle  ball;
    int p1score,p2score;
    
    int systemdiff , playerdiff;
    //object of class panddle one and panddle two
     Paddle_One paddleone = new Paddle_One(200,40);
     Paddle_Two paddletwo = new Paddle_Two(200,610);
    
    public TheBall(int x , int y){
        this.x = x;
        this.y = y;
          p1score = p2score = 0;
          systemdiff = playerdiff = 0;
        Random r = new Random();
        int XRand = r.nextInt(1);
        int YRand = r.nextInt(1);
        
        if(XRand == 0){
            XRand--;
            setXDirection(XRand);
        }
        if(YRand == 0){
            YRand--;
            setYDirection(YRand);
        }
        ball = new Rectangle(this.x ,this.y ,10,10);
          
    }
    //draw method
    public void drawBall(Graphics g){
       
           
        g.setColor(Color.YELLOW);
        g.fillRect(ball.x, ball.y,ball.width,ball.height);
//       
           
        
    }
    //settter method for x and y directions
    public void setXDirection(int XDir){
        XDirection = XDir;
    }
    public void setYDirection(int YDir){
        YDirection = YDir;
    }
    //method collistion detection
    public  void collistion(){
       if(ball.intersects(paddleone.paddle1))
           setYDirection(+1);
        if(ball.intersects(paddletwo.paddle))
            setYDirection(-1);
    }
    //move method
    public void move(){
        //collistion();
        ball.x += XDirection;
        ball.y += YDirection;
        if(ball.intersects(paddleone.paddle1)){
            setYDirection(+1);
            systemdiff++;
        }
        if(ball.intersects(paddletwo.paddle)){
            setYDirection(-1);
            playerdiff++;
        }
        if(ball.x <= 0){
            setXDirection(+1);
        }
        if(ball.x >= 393){
            setXDirection(-1);
        }
        if(ball.y <= 35 ){
            setYDirection(+1);
            p2score++;
        }
        if(ball.y >= 643){
            setYDirection(-1);
            p1score++;
        }
    }
    //the thread
    @Override
    public void run(){
       try{
           while(true){
               move();
               
               Thread.sleep(3);
           }
       } catch(InterruptedException ex){
           JOptionPane.showMessageDialog(null,ex.getMessage());
       }
    }
    
}
