import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button  extends Actor
{
    private String text;

    public Button(String text, int width, int x)
    {
        GreenfootImage myPic = new GreenfootImage(width, 20);
        myPic.setColor(Color.BLUE);
        myPic.fill();
        myPic.setColor(Color.WHITE);
        myPic.drawString(text, x, 14);
        myPic.setColor(Color.BLACK);
        myPic.drawRect(0, 0, width - 1, 19);
        setImage(myPic);
        this.text = text;
    }

    public void act() 
    {
        Background bg = (Background) getWorld();
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(Greenfoot.mouseMoved(this))
            getImage().setTransparency(128);
        else if (Greenfoot.mouseMoved(null))
            getImage().setTransparency(255);
        if (Greenfoot.mouseClicked(this) && getText().equals("Play")) {
            bg.clearWorld();
            bg.Start();
        } else if  (Greenfoot.mouseClicked(this) && getText().equals("Instructions")) {
            bg.clearWorld();
            bg.buildInstructions();
        } else if (Greenfoot.mouseClicked(this) && getText().equals("Go Back")) {
            bg.clearWorld();
            bg.buildIntro();
        } else if (Greenfoot.mouseClicked(this) && getText().equals("Quit")) {
            bg.clearWorld();
            bg.buildIntro();
        }
    }
    
    public String getText()
    {
        return text;
    }
}

