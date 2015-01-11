
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.math.*;

/**
 * Write a description of class Arrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arrow extends SmoothMover
{
    ////// VARIABLES //////  

    private int rotation;   
    private double vSpeed = 0;
    private double acceleration = 0.5;
    private int jumpStrength = 6;
    private boolean time = false;
    private double vert1;
    private double vert2;
    private int sign;
    private double vertSpeed;
    private boolean onTarget;
    private Score scoreNum;
    private boolean scored = false;

    ////// CODE //////
    /**
     * Arrow Constructor
     * Creates the arrow with movement and rotation and sets up the acceleration.
     * @param vector A parameter
     * @param startRotation A parameter
     * @param score_field A parameter
     */
    public Arrow(Vector vector, int startRotation, Score score_field)
    {
        super(vector);
        rotation = startRotation;
        setRotation(rotation);
        vSpeed = -jumpStrength;
        scoreNum = score_field;
    }

    /**
     * Method fall
     * Moves the arrow downward with an acceleration.
     */
    public void fall()
    {
        setLocation ( getX(), getY() + vSpeed);
        vSpeed = vSpeed + acceleration;
        setRotation((sign*((int)vertSpeed))* 2);
    }

    /**
     * Method jump
     * Initial boost to the arrow right off the string.
     */
    public void jump()
    {
        vSpeed = -jumpStrength;
        fall();  // gravity counteracts jumpStrength
    }

    /**
     * Method checkPoints
     * Checks the location of the arrow on the target and assigns points accordingly.
     */
    public void checkPoints()
    {
        //white ring top
        if ( onTarget == true && getY() < 112 && getY() >= 87 && scored == false)
        { scoreNum.addToScore(1);
            Greenfoot.playSound("hit-target.wav");
            scored = true; }
        //black ring top
        else if ( onTarget == true && getY() < 137 && getY() >= 112 && scored == false)
        { scoreNum.addToScore(3);
            Greenfoot.playSound("hit-target.wav");
            scored = true; }
        //blue ring top
        else if ( onTarget == true && getY() < 161 && getY() >= 137 && scored == false)
        { scoreNum.addToScore(5);
            Greenfoot.playSound("hit-target.wav");
            scored = true; }
        //red ring top
        else if ( onTarget == true && getY() < 187 && getY() >= 161 && scored == false)
        { scoreNum.addToScore(7);
            Greenfoot.playSound("hit-target.wav");
            scored = true; }
        //yellow dot
        else if ( onTarget == true && getY() < 236 && getY() >= 187 && scored == false)
        { scoreNum.addToScore(10);
            Greenfoot.playSound("hit-target.wav");
            scored = true; }
        //red ring bottom
        else if ( onTarget == true && getY() < 261 && getY() >= 236 && scored == false)
        { scoreNum.addToScore(7);
            Greenfoot.playSound("hit-target.wav");
            scored = true; }
        //blue ring bottom
        else if ( onTarget == true && getY() < 287 && getY() >= 261 && scored == false)
        { scoreNum.addToScore(5);
            Greenfoot.playSound("hit-target.wav");
            scored = true; }
        //black ring bottom
        else if ( onTarget == true && getY() < 311 && getY() >= 287 && scored == false)
        { scoreNum.addToScore(3);
            Greenfoot.playSound("hit-target.wav");
            scored = true; }
        //white ring bottom
        else if ( onTarget == true && getY() < 325 && getY() >= 311 && scored == false)
        { scoreNum.addToScore(1);
            Greenfoot.playSound("hit-target.wav");
            scored = true; }
        //87, 112, 137, 161, 187, 236, 261, 287, 311, 338
    }

    /**
     * Act
     * Runs location checks, moves and accelerates the arrows, calculates vertical speed, and controls the in-air rotation.
     */
    public void act() 
    {
        //moves and accelerates the falling of the arrow when it is not on the target.
        if ( onTarget == false)
        {
            move();
            fall();
        }
        //checks if the arrow is in the target area
        if (getX() >= 449 && getY() < 325 && getY() > 87 )
        {
            onTarget = true; 
        }
        checkPoints();
        //calculates a rough vertical speed every other frame.
        if ( time == false)
        {
            time = true;
            vertSpeed = Math.abs(vert1 - vert2);
            vert1 = getExactY();
        }
        else
        {
            time = false;
            vert2 = getExactY();
        }
        //checks if the vertical speed is less negative or positive and gives variable sign 1 or -1.
        if ( vSpeed < 0)
        {
            sign = -1;
        }
        else
        {
            sign = 1;
        }
        //removes ofjects at the edge of the world.
        if ( getY() > 398 || getX() > 598)
        {
            getWorld().removeObject(this);
        }
    }
}    

