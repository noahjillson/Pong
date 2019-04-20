import javax.swing.*;
import java.awt.*;

public class Main 
{    
    public static void main(String[] args)
    {
        /*
        Test t = new Test();
        JFrame f = new JFrame("Testing Frame");
        f.add(t);
        f.setSize(500, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.getContentPane().setBackground(Color.black);
        f.setVisible(true);*/
        FrameCreation fc = new FrameCreation();
        fc.add(new Render());
        fc.setVisible(true);
        
    }   
}

