import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Actor
{
    //Add GreenfootImage variables for original image and jumping image here
    private GreenfootImage original = new GreenfootImage("Hero.png");
    private GreenfootImage jumping = new GreenfootImage("Hero_Jumping.png");
    
    //Add the following variables here: y, ySpeed, smallUp, up, cannotJump, and lookingRight
    private int y = 0;
    private int ySpeed = 1;
    private int smallUp = -6;
    private int up = -15;
    private boolean cannotJump = false;
    private boolean lookingRight = true;
    
    /**
     * Hero controls the changes to the Hero objects image
     * @param there are no parameters
     * @return The return type is the Hero object
     */
    public Hero()
    {
        original.scale(30, 30);
        jumping.scale(32, 32);
        original.mirrorHorizontally();
        setImage(original);
    }
    
    /**
     * The act method calls the movement and checkCollision objects
     * @param there are no parameters
     * @return There is no return type
     */
    public void act() 
    {
        // Add method call to movement method here
        movement();
        
        //Add method call to checkCollision here
        checkCollision();
    }
    
    /**
     * The movement method controls the movement of the Hero object in response to the player's
     * input
     * @param there are no parameters
     * @return There is no return type
     */
    private void movement()
    {
        if( Greenfoot.isKeyDown("right"))
        {
            if(lookingRight == false)
            {
                original.mirrorHorizontally();
                jumping.mirrorHorizontally();
            }
            
            lookingRight = true;
            setLocation(getX()+3, getY());
        }
        
        if( Greenfoot.isKeyDown("left"))
        {
            if(lookingRight == true)
            {
                original.mirrorHorizontally();
                jumping.mirrorHorizontally();
            }
            
            lookingRight = false;
            setLocation(getX()-3, getY());
        }
        
        if( Greenfoot.isKeyDown("up"))
        {
            if( cannotJump == false)
            {
                setImage(jumping);
                y = up;
                fall();
            }
        }
        
        if( getY() >= 360)
        {
            setLocation(getX(), 370);
            y = 0;
        }
    }
    
    /**
     * The fall method causes the Hero object to fall to the platform after jumping
     * @param There are no parameters
     * @return There is no return type
     */
    private void fall()
    {
        cannotJump = true;
        setLocation(getX(), getY()+y);
        y = y + ySpeed;
    }
    
    /**
     * The checkCollision method controls the responses to the Hero object touching the various
     * actors of the game
     * @param there are no parameters
     * @return There is no return type
     */
    private void checkCollision()
    {
        ScrollerWorld myWorld = (ScrollerWorld)getWorld();
        
        if(getOneObjectAtOffset(0, getImage().getHeight()-15, Enemy.class) != null)
        {
            getWorld().removeObject(getOneObjectAtOffset(0, getImage().getHeight()-15, Enemy.class));
            myWorld.addToScore();
            y = smallUp;
            fall();
        }
        else if(isTouching(Enemy.class))
        {
            myWorld.gameOver();
        }
        else if(getOneObjectAtOffset(0, getImage().getHeight()-15, Platform.class) != null)
        {
            setImage(original);
            cannotJump = false;
            y = 0;
        }
        else
        {
            fall();
        }
    }
}
