import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platform extends Actor
{
    /**
     * Platform scales the platforms to be smaller
     * @param there are no parameters
     * @return The return type is the Platform object.
     */
    public Platform()
    {
        getImage().scale(100, 16);
    }
    
    /**
     * Act causes the platforms to move and despawn
     * @param there are no parameters
     * @return There is no return type
     */
    public void act() 
    {
        // Add code to move platforms if right key is pressed
        if( Greenfoot.isKeyDown("right") )
        {
            move(-1);
        }
        // Add code to remove platforms if it's x coordinate is < 0
        
        
        if( getX() < 0)
        {
            getWorld().removeObject(this);
        }
    }    
}
