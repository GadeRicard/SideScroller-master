import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Actor
{
    /**
     * -GreenfootImages fireball and invFireball set up the images for use by the fireball
     * -shouldMoveRight controls the direction the fireball will move
     * -ySpeed controls how fast the fireball should move vertically
     * -bounceCounter controls when the fireball should start moving downwards
     * -imageCounter controls when the fireball should change the image it uses
     * -fireSprite controls which image should be changed to
     */
    private GreenfootImage fireball = new GreenfootImage("Fireball.png");
    private GreenfootImage invFireball = new GreenfootImage("Fireball2.png");
    private boolean shouldMoveRight = true;
    private int ySpeed;
    private int bounceCounter = -1;
    private int imageCounter = 3;
    private boolean fireSprite = true;
    /**
     * Fireball controls the changes to the Fireball objects image and links a variable to the Hero class
     * @param The parameter is the Hero class
     * @return the return type is the Fireball class
     */
    public Fireball(Hero h)
    {
        fireball.scale(12, 12);
        invFireball.scale(12, 12);
        setImage(fireball);
        shouldMoveRight = h.getLookingRight();
    }
    
    /**
     * Act - do whatever the Fireball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(imageCounter >= 3)
        {
            if(fireSprite == true)
            {
                setImage(fireball);
                fireSprite = false;
            }
            else if(fireSprite == false)
            {
                setImage(invFireball);
                fireSprite = true;
            }
            imageCounter = 0;
        }
        else if(imageCounter < 3)
        {
            imageCounter++;
        }
        checkCollisionAndMovement();
    }
    
    /**
     * checkCollisionAndMovement controls the reactions the Fireball object has to contacting other actors
     * and controls the movement of the Fireball object
     * @param there are no parameters
     * @return there is no return type
     */
    private void checkCollisionAndMovement()
    {
        ScrollerWorld myWorld = (ScrollerWorld)getWorld();
        if(isTouching(Platform.class))
        {
            ySpeed = -2;
            bounceCounter = 16;
        }
        
        if(bounceCounter > 0)
        {
            bounceCounter--;
        }
        else if(bounceCounter == 0)
        {
            ySpeed = 2;
        }
        else
        {
            ySpeed = 3;
        }
        
        if(shouldMoveRight == true)
        {
            setLocation(getX()+3, getY()+ySpeed);
        }
        else if(shouldMoveRight == false)
        {
            setLocation(getX()-3, getY()+ySpeed);
        }
        
        if(isTouching(Enemy.class))
        {
            getWorld().removeObject(getOneIntersectingObject(Enemy.class));
            delete();
            myWorld.addToScore();
        }
        
        else if(getX() <= 0)
        {
            delete();
        }
        else if(getX() >= 599)
        {
            delete();
        }
    }
    
    /**
     * The delete method deletes the Fireball object when called
     * @param there are no parameters
     * @return there is no return type
     */
    private void delete()
    {
        getWorld().removeObject(this);
    }
}
