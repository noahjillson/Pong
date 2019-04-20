import java.awt.Rectangle;

public class CollisionDetection
{
    public CollisionDetection()
    {
    }
    
    public Rectangle bounds(int x, int y, int height, int width)
    {
        return (new Rectangle(x, y, width, height));
    }
    
    public boolean detectCollision(Rectangle rect1, Rectangle rect2)
    {
        if(rect2.intersects(rect1))
        {
            return true;
        }
        return false;
    }
}
