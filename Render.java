import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.Random;

public class Render extends JPanel implements ActionListener, KeyListener
{
    //Paddle 1's coordinates and velocity
    private double x1 = 20, y1 = 200, velX1 = 0, velY1 = 0;
    //Paddle 2's coordinates and velocity
    private double x2 = 650, y2 = 200, velX2 = 0, velY2 = 0;
    //The Puck's coordinates and velocity
    private double xP = 335, yP = 235, velYP = (int)((Math.random() * 3) + 1), velXP = 2;    

    private int[] scores = {0, 0};

    private boolean gameOver = true;

    //The Up and Down velocity of both Paddles
    private final double PADDLE_VELOCITY = 3;

    //Timer used to perform an action that causes the Jpanel to repaint
    private Timer t = new Timer(5, this);
    private CollisionDetection cd = new CollisionDetection();

    public Render()
    {
        //t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setOpaque(false);
    }

    /*
     * Paints the Puck, Paddle 1, and Paddle 2 into the JFrame
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.white);
        //Paddle 1
        g2.fill(new Rectangle2D.Double(x1, y1, 30,100));
        //Paddle 2
        g2.fill(new Rectangle2D.Double(x2, y2, 30,100));
        //Puck
        g2.fill(new Rectangle2D.Double(xP, yP, 30,30));
        g2.draw(new Line2D.Double(350, 0, 350, 500));
        g2.drawString(("" + scores[0]), 320, 20);
        g2.drawString(("" + scores[1]), 380, 20);
        if(gameOver)
        {
            g2.drawString("Press Enter To Play", 300, 200);
        }
    }

    /*
     * Repaints the Puck, Paddle 1, and Paddle 2 then changes their X and Y 
     * values
     */
    public void actionPerformed(ActionEvent e)
    {        
        //Inverts the Puck's Y velocity if it begins to exit the screen along the Y Axis
        if(yP + velYP < 0 || yP + velYP > 450)
        {
            velYP = -velYP; 
        }
        //Runs if Puck Exits the Screen along the X axis
        if(xP + velXP < 0)
        {
            scores[1]++;
            if(scores[1] > 9)
            {
                terminateGame();
            }
            //changes the pucks velocity to a random integer
            puckRandomVelocityY();
            //Centers puck on the screen
            yP = 235;
            xP = 335;
        }
        else if(xP + velXP > 670)
        {
            scores[0]++;
            if(scores[0] > 9)
            {
                terminateGame();
            }
            //changes the pucks velocity to a random integer
            puckRandomVelocityY();
            //Centers puck on the screen
            yP = 235;
            xP = 335;
        }
        //Adds the Puck's X and Y velocities to its X and Y coordinates
        yP += velYP;
        xP += velXP;
        //Prevents Paddle 1 from leaving the screen
        if(y1 + velY1 < 20 || y1 + velY1 > 365)
        {
            stop1();
        }
        //Prevents Paddle 2 from leaving the screen
        if(y2 + velY2 < 20 || y2 + velY2 > 365)
        {
            stop2();
        }

        //Adds Paddle 1's Y velocity to its Y coordinate
        y1 += velY1;
        //Adds Paddle 2's Y velocity to its Y coordinate
        y2 += velY2;
        detectIntersection();
        repaint();
    }

    public void detectIntersection()
    {
        if(cd.detectCollision(cd.bounds((int)x1, (int)y1, 30, 100), cd.bounds((int)xP, (int)yP, 30, 30)))
        {
            velYP = -velYP;
            velXP = -velXP;
            puckRandomVelocityY();
            System.out.println("COLISSION1");
        }
        else if(cd.detectCollision(cd.bounds((int)x2, (int)y2, 30, 100), cd.bounds((int)xP, (int)yP, 30, 30)))
        {
            velYP = -velYP;
            velXP = -velXP;
            puckRandomVelocityY();
            System.out.println("COLISSION2");
        }
    }

    public void puckRandomVelocityY()
    {
        if(velYP > 0)
        {
            velYP = (int)((Math.random() * 3) + 1);
        }
        else
        {
            velYP = (int)((Math.random() * 3) + 1) * -1;
        }
    }

    /*
     * Changes Paddle 1's Y velocity to a negative value which moves the 
     * puck twords the TOP of the Jframe. This is used in the actionPerformed()
     * method to change Paddle 1's X and Y value
     */
    public void up1()
    {
        velY1 = -PADDLE_VELOCITY;
        velX1 = 0;
    }

    /*
     * Changes Paddle 1's Y velocity to a positive value which moves the 
     * puck twords the BOTTOM of the Jframe. This is used in the actionPerformed()
     * method to change Paddle 1's X and Y value
     */
    public void down1()
    {
        velY1 = PADDLE_VELOCITY;
        velX1 = 0;
    }

    /*
     * Changes Paddle 1's X and Y velocity to 0 which keeps the 
     * puck stationary during the actionPerformed(), the 
     * method used to change Paddle 1's X and Y value
     */
    public void stop1(){
        velY1 = 0;
        velX1 = 0;
    }

    /*
     * Changes Paddle 2's velocity to a negative value which moves the 
     * puck twords the top of the Jframe. This is used in the actionPerformed()
     * method to change Paddle 2's X and Y value
     */
    public void up2()
    {
        velY2 = -PADDLE_VELOCITY;
        velX2 = 0;
    }

    /*
     * Changes Paddle 2's velocity to a positive value which moves the 
     * puck twords the BOTTOM of the Jframe. This is used in the actionPerformed()
     * method to change Paddle 2's X and Y value
     */
    public void down2()
    {
        velY2 = PADDLE_VELOCITY;
        velX2 = 0;
    }

    /*
     * Changes Paddle 2's X and Y velocity to 0 which keeps the 
     * puck stationary during the actionPerformed(), the 
     * method used to change Paddle 2's X and Y value
     */
    public void stop2()
    {
        velY2 = 0;
        velX2 = 0;
    }
    
    public void terminateGame()
    {
        t.stop();
        gameOver = true;
    }
    
    public void keyPressed(KeyEvent e)
    {
        if(!gameOver)
        {
            if(e.getKeyCode() == KeyEvent.VK_W)
            {
                up1();
            }
            else if(e.getKeyCode() == KeyEvent.VK_S)
            {
                down1();
            }
            else if(e.getKeyCode() == KeyEvent.VK_UP)
            {
                up2();
            }
            else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            {
                down2();
            }
        }
        else
        {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                gameOver = false;
                scores[0] = 0;
                scores[1] = 0;
                t.start();
            }
        }
    }

    public void keyTyped(KeyEvent e)
    {

    }

    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_W
        || e.getKeyCode() == KeyEvent.VK_S)
        {
            stop1(); 
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP
        || e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            stop2(); 
        }
    }
}