import javax.swing.*;
import java.awt.*;
public class FrameCreation extends JFrame
{
    private final static int WIDTH = 700;
    private final static int HEIGHT = 500;

    public FrameCreation()
    {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pong");
        setResizable(false);
        getContentPane().setBackground(Color.black);
        //add(new Render());
        System.out.println("code past paddles");
        //add(new Puck());
    }
}
