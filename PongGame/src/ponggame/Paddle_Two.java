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
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;


public class Paddle_Two implements Runnable{
    int x , y ,XDirection;
    Rectangle paddle;
    public Paddle_Two(int x ,int y){
        this.x = x;
        this.y = y;
        paddle = new Rectangle(this.x,this.y,50,20);
    }
    //draw paddle two
    public void darwPaddle(Graphics g){
        
        g.setColor(Color.WHITE);
        g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
    }
    //setter for xDirection
    public void setXDirection(int XDir){
        this.XDirection = XDir;
    }
    public void keyPressed(KeyEvent ke){
        int keycode = ke.getKeyCode();
        if(keycode == ke.VK_LEFT){
            setXDirection(-1);
        }
        if(keycode == ke.VK_RIGHT){
            setXDirection(+1);
        }
    }
    public void keyReleased(KeyEvent ke){
        int keycode = ke.getKeyCode();
        if(keycode == ke.VK_LEFT){
            setXDirection(0);
        }
        if(keycode == ke.VK_RIGHT){
            setXDirection(0);
        }
    }
    //method move the paddle
    public void move(){
        paddle.x += XDirection;
        //collistion detection
        if(paddle.x <= 0)
            paddle.x = 0;
        if(paddle.x >= 350)
            paddle.x = 350; 
    }
    //the thread of paddle two
    
    @Override
    public void run(){
        try{
           while(true){
                move();
           Thread.sleep(2,40);
           }
        }catch(InterruptedException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
    
}

