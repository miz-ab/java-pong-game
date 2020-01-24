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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class Templet extends JFrame{
        int width =  400;
        int height = 650;
    //static Paddle_One p1 = new Paddle_One(200,40);
    //static Paddle_Two p2 = new Paddle_Two(200,610);
    static TheBall ball = new TheBall(200,325);
    //double buffering 
    Image dbImage;
    Graphics dbg;
    Dimension Dim = new Dimension(width,height);
        //constructor 
    public Templet(){
        this.setTitle("Pong Game with Computer");
        this.setSize(Dim);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.darkGray);
        this.addKeyListener(new KL());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    //double buffering implementation
    @Override
    public void paint(Graphics g){
        dbImage = createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        drawAll(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }
    public void drawAll(Graphics g){
        
        ball.paddleone.drawPaddle(g);
        ball.paddletwo.darwPaddle(g);
        ball.drawBall(g);
        g.setColor(Color.WHITE);
        g.drawString(""+ball.p1score, 375, 40);
        g.drawString(""+ball.p2score, 375, 640);
        //draw score diff by palyer and system
        g.setColor(Color.PINK);
        g.drawString(""+ball.systemdiff, 40,40);
        g.drawString(""+ball.playerdiff, 40,640);
        
        repaint();
    }
    //add keyListener Class in main class
    public class KL extends KeyAdapter{
        //key pressed method 
        @Override
        public void keyPressed(KeyEvent ke){
            ball.paddletwo.keyPressed(ke);
        }
        @Override
        public void keyReleased(KeyEvent ke){
            ball.paddletwo.keyReleased(ke);
        }
        }
   
    //main method
    public static void main(String a[]){
        Templet tmple = new Templet();
            //create Thread Object
         Thread paddleone = new Thread(ball.paddleone);
         Thread paddletwo = new Thread(ball.paddletwo);
         Thread ballt = new Thread(ball);
        
        ballt.start();
        paddleone.start();
        paddletwo.start();
        
    }
    
}

