import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mushroom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mushroom extends Actor
{
    private GreenfootImage mushroom = new GreenfootImage("Mushroom.png");
    
    public Mushroom()
    {
        mushroom.scale(25, 25);
        setImage(mushroom);
    }
    
    /**
     * Act - do whatever the Mushroom wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(-3);
        
        
        if(getX() <= 0)
        {
            getWorld().removeObject(this);
        }
    }    
}
