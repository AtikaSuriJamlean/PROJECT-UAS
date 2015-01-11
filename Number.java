import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class Number here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Number extends Actor
{
    Font font1=new Font("Helvetica",Font.BOLD,16); //sets the font to a large, thick, Helvetica. 
    private int velocity = 0; //Keeps track of game score

    /**
     * Score Constructor
     * Creates an image for the Score() class and draw text
     * @param text A parameter
     */
    public Number(String text)
    {
        GreenfootImage img = new GreenfootImage (250, 135);
        img.setFont(font1);
        img.setColor(Color.white);
        img.drawString(text, 5, 45);
        setImage(img);
    }    

    /**
     * Method setText
     * updates score using setText() - see the Enemy's collision event
     * @param text A parameter
     */
    public void setText(String text)
    {
        GreenfootImage img = getImage();
        img.clear();
        img.drawString(text, 5, 45);
    }

    /**
     * Method addToScore
     * Shows the score layout on the screen.
     * @param points A parameter
     */
    public void addToScore (int add)
    {
        velocity+=add;
        setText("Velocity: " + velocity);
    }

    /**
     * Act - do whatever the Score wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
