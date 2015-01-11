import greenfoot.*;
import java.util.*;
import java.awt.Color;
import java.awt.Font;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Background here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Background extends World
{
    private Score score_field = null;
    /**
     * Constructor for objects of class Background.
     * Creates a bow and arrow and sets up velocity and score fields.
     */
    public Background()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        setBackground("background4.png");
        Button bt1 = new Button("Play", 70, 23);
        Button bt2 = new Button("Instructions", 91, 15);
        //Title title = new Title( );
        
        addObject(bt1, getWidth()/2, getHeight()/2+2);
        addObject(bt2, getWidth()/2, getHeight()/2+40);
    }
    
    public void Start()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        Number velocity_field = new Number ("VELOCITY : 0");
        score_field = new Score ("SCORE : 0");
        addObject(score_field, 600, 50);
        addObject(new BowAndArrow(velocity_field, score_field), 70, 200);
        addObject(velocity_field, 150, 50);
    }

    /**
     * Method restart
     * Removes all arrows
     */
    public void restart()
    {
        java.util.List<Arrow> arrows = getObjects(Arrow.class);
        removeObjects(arrows);
        score_field.setScore(0);
    }
    
    /**
     * Include "Start", "Password" and "Instructions"
     */
    public void buildIntro()
    {
        setBackground("background4.png");
        Button bt1 = new Button("Play", 70, 23);
        Button bt2 = new Button("Instructions", 90, 15);
        
        addObject(bt1, getWidth()/2, getHeight()/2+2);
        addObject(bt2, getWidth()/2, getHeight()/2+40);
    }
    
    public void buildInstructions()
    {
        //addFloor();
        GreenfootImage bg = getBackground();
        String aim1 = "[LEFT] and [RIGHT] - AIM";
        String aim2 =  "[P] - RESET";
        String moving = "[UP] and [DOWN] - ADJUST POWER"; 
        String up = "[O] - VIEW RANK";
        String liftDrop = "[SPACE] - FIRE";
        String escape = "[I] - VIEW VELOCITY";
        Font fnt = new Font("Arial", Font.BOLD, 16);
        //Color clr = new Color(Color.WHITE);
        bg.setFont(fnt);
        bg.setColor(Color.WHITE);
        int w = getWidth();
        int h = getHeight();
        int MARGIN_SIDE = 15;
        int MARGIN_TOP = 50;
        bg.drawString(aim1, MARGIN_SIDE, MARGIN_TOP);
        bg.drawString(aim2, MARGIN_SIDE, 2*MARGIN_TOP-10);
        bg.setColor(Color.WHITE);
        bg.drawString(moving, MARGIN_SIDE, 3*MARGIN_TOP);
        bg.setColor(Color.WHITE);
        bg.drawString(up, MARGIN_SIDE, 4*MARGIN_TOP);
        bg.setColor(Color.WHITE);
        bg.drawString(liftDrop, MARGIN_SIDE, 5*MARGIN_TOP);
        bg.setColor(Color.WHITE);
        bg.drawString(escape, MARGIN_SIDE, 6*MARGIN_TOP);
        
        //addObject(new ArrowKeys(), 22, 6);
        
        addObject(new Button("Go Back", 70, 12), getWidth()/2, 16);
        
        //addObject(new Block(), 9, 1);
    }
    
    public void clearWorld()
    {
        removeObjects(getObjects(null));
    }
    
    /**
     * Designed for level 11;
     * sets all objects to be translucent.
     */
    public void setAllTransparent()
    {
        List<Actor> actors = getObjects(null);
        for (Actor a: actors)
            a.getImage().setTransparency(128);
     }
    
}