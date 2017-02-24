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
    private GreenfootImage big = new GreenfootImage("Hero_Big.png");
    private GreenfootImage bigJumping = new GreenfootImage("Hero_Big_Jumping.png");
    
    //Add the following variables here: y, ySpeed, smallUp, up, cannotJump, and lookingRight
    private int y = 0;
    private int ySpeed = 1;
    private int smallUp = -6;
    private int up = -15;
    private boolean cannotJump = false;
    private boolean lookingRight = true;
    private boolean marioBig = false;
    private int invincibility = 0;
    
    /**
     * Hero controls the changes to the Hero objects image
     * @param there are no parameters
     * @return The return type is the Hero object
     */
    public Hero()
    {
        original.scale(32, 32);
        jumping.scale(32, 32);
        big.scale(32, 64);
        bigJumping.scale(32, 64);
        original.mirrorHorizontally();
        setImage(original);
    }
    
    /**
     * The act method calls the movement and checkCollision methods
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
     * input as well as the spawning of the Fireball object
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
                big.mirrorHorizontally();
                bigJumping.mirrorHorizontally();
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
                big.mirrorHorizontally();
                bigJumping.mirrorHorizontally();
            }
            
            lookingRight = false;
            setLocation(getX()-3, getY());
        }
        
        if( Greenfoot.isKeyDown("up"))
        {
            if( cannotJump == false)
            {
                if(marioBig == true)
                {
                    setImage(bigJumping);
                    y = up;
                    fall();
                }
                else if(marioBig == false)
                {
                    setImage(jumping);
                    y = up;
                    fall();
                }
            }
        }
        
        if( getY() >= 360)
        {
            setLocation(getX(), 370);
            y = 0;
        }
        
        if(Greenfoot.isKeyDown("space") && marioBig == true)
        {
            if( getWorld().getObjects(Fireball.class).size() <= 0)
            {
                if(lookingRight == true)
                {
                    getWorld().addObject(new Fireball(this), getX()+16, getY());
                }
                else if(lookingRight == false)
                {
                    getWorld().addObject(new Fireball(this), getX()-16, getY());
                }
            }
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
        
        if(invincibility >= 1)
        {
            invincibility--;
        }
        
        if(getOneObjectAtOffset(0, getImage().getHeight()-15, Enemy.class) != null)
        {
            if(invincibility == 0)
            {
                getWorld().removeObject(getOneObjectAtOffset(0, getImage().getHeight()-15, Enemy.class));
                myWorld.addToScore();
                y = smallUp;
                fall();
            }
            else
            {
                fall();
            }
        }
        else if(isTouching(Enemy.class))
        {
            if(marioBig == true)
            {
                setImage(original);
                marioBig = false;
                invincibility = 100;
                fall();
            }
            else if(invincibility == 0)
            {
                myWorld.gameOver();
            }
        }
        else if(isTouching(Mushroom.class))
        {
            if(marioBig == false)
            {
                setImage(big);
                marioBig = true;
                setLocation(getX(), getY()-30);
                getWorld().removeObject(getOneIntersectingObject(Mushroom.class));
            }
            else
            {
                getWorld().removeObject(getOneIntersectingObject(Mushroom.class));
            }
        }
        else if(getOneObjectAtOffset(0, getImage().getHeight()-15, Platform.class) != null && marioBig == false)
        {
            setImage(original);
            cannotJump = false;
            y = 0;
           
        }
        else if(getOneObjectAtOffset(0, getImage().getHeight()-30, Platform.class) != null && marioBig == true)
        {
            setImage(big);
            cannotJump = false;
            y = 0;
        }
        else
        {
            fall();
        }
    }
    
    /**
     * getLookingRight allows the Fireball actor to access the getLookingRight variable 
     * from the Hero actor's
     * code
     * @param there are no parameters
     * @return the return type is boolean
     */
    public boolean getLookingRight()
    {
        return lookingRight;
    }
}
