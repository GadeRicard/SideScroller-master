import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    // Add imageCounter variable here
    private int imageCounter = 6;
    
    /**
     * Changes the scale of the Enemy object
     * @param there are no parameters
     * @return The return type is the Enemy object
     */
    public Enemy()
    {
        getImage().scale(25, 25);
    }
    
    /**
     * act causes the enemy object to move to the left, change it's image and
     * despawn when it leaves the world
     * @param there are no parameters
     * @return there is no return type
     */
    public void act() 
    {
        // Add code to have enemy move left here
        move(-2);
        
        /**
         * Check if imageCounter is >= 3. If so, mirror the image horizontally and
         * set imageCounter to 0. Otherwise, increase the imageCounter by 1.
         */
        GreenfootImage image1 = new GreenfootImage("Goomba.png");
        if( imageCounter >= 6)
        {
            image1.mirrorHorizontally();
            imageCounter = 0;
        }
        else
        {
            imageCounter++;
        }
        
        //Check if the Enemy's x location is <= 0. If so, remove it from the world
        if( getX() <= 0)
        {
            getWorld().removeObject(this);
        }
    }    
}
