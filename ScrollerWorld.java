import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScrollerWorld extends World
{
    //Add platformCounter and score variables here
    private int platformCounter = 25;
    private int score = 0;
    
    /**
     * Controls the creation of the world and calls the code for adding objects and 
     * displaying the score.
     * @param There are no parameters
     * @return The return type is the ScrollerWorld object
     */
    public ScrollerWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        //Method call to prepareWorld method which will add objects to world
        prepareWorld();
        
        //when created, add method call to displayScore here
        displayScore();
    }
    
    /**
     * prepareWorld adds objects to world to prepare the game for use
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void prepareWorld()
    {
       
       for( int i = 0; i <= getWidth()/50; i++ )
       {
           addObject( new Platform(), i * 50, getHeight() - 8);
       }
        
        
        //Add Hero object to the world
        addObject( new Hero(), 30, getHeight() - 30);
    }
    
    /**
     * Adds platforms to the right side of the world as they come into view, and adds
     * enemies 0.67% of the time to the right side of the world.
     * @param There are no parameters
     * @return There is no return type
     */
    public void act()
    {
        if( Greenfoot.isKeyDown("right") )
        {
            if( platformCounter >= 25)
            {
                platformCounter = 0;
                addObject( new Platform(), getWidth()-25, getHeight()-8);
            }
            platformCounter = platformCounter + 1;
        }
        
        
        if(Greenfoot.getRandomNumber(150) < 1)
        {
            addObject( new Enemy(), 599, getHeight()-27);
        }
    }
    
    /**
     * gameOver displays the player's score in the middle of the world and stops the game
     * @param There are no parameters
     * @return there is no return type
     */
    public void gameOver()
    {
        showText("You have been defeated! Score: " + score, getWidth()/2, getHeight()/2);
        Greenfoot.stop();
    }
    
    /**
     * displayScore shows the player's current score in the top right of the world
     * @param there are no parameters
     * @return There is no return type
     */
    private void displayScore()
    {
        showText("Score: " + score, 50, 25);
    }
    
    /**
     * addToScore adds 1 to the player's score
     * @param there are no parameters
     * @return there is no return type
     */
    public void addToScore()
    {
        score = score + 1;
    }
}
