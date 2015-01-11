import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BowAndArrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BowAndArrow extends Actor
{
    ////// VARIABLES //////

    private int strength = 0;  
    private int canShoot = 0; 
    private int shots = 0;
    private boolean running = true;
    private boolean oKey = false;
    private Number velocityNum;
    Score score_field;
    UserInfo user;

    ////// CODE ///////
    /**
     * BowAndArrow Constructor
     * Sets up the velocity display.
     * @param velocity_field A parameter
     */
    public BowAndArrow(Number velocity_field, Score score_field)
    {
        velocityNum = velocity_field;
        this.score_field=score_field;
        if (UserInfo.isStorageAvailable()) 
        {
            user = UserInfo.getMyInfo();
        }
    }

    /**
     * Method keyPresses
     * All of the controls used in the game: rotation, velocity, and shooting.
     */
    public void keyPresses()
    {
        if ( Greenfoot.isKeyDown("left") && getRotation() != 300 )
        { setRotation(getRotation()-1); }
        if ( Greenfoot.isKeyDown("right") && getRotation() != 60 )
        { setRotation(getRotation()+1); }
        if ( Greenfoot.isKeyDown("up") && strength < 20)
        {
            velocityNum.addToScore(1);
            strength++;
        }
        if ( Greenfoot.isKeyDown("down") && strength > 0)
        {
            velocityNum.addToScore(-1);
            strength--;
        }
        if (Greenfoot.isKeyDown("space")  && canShoot < 0)
        {
            getWorld().addObject(new Arrow(new Vector(getRotation(), (strength + Greenfoot.getRandomNumber(10))-5), getRotation(), score_field), getX(), getY());
            Greenfoot.playSound("arrow_woosh.wav");
            shots++;
            canShoot = 90;
        }
        if ( Greenfoot.isKeyDown("p") )
        {

            if (score_field.getScore() > user.getScore())
            {
                user.setScore(score_field.getScore());
                user.store();
            }

            Background world = (Background) getWorld();
            world.restart();
            running = true;
            shots = 0;
            canShoot = 2;
            setRotation(0);
            velocityNum.setText("Velocity: " + strength);
        }
        if ( Greenfoot.isKeyDown("o") )
        {
            velocityNum.setText(user.getUserName() + ": Rank " + user.getRank());
        }
         else if ( Greenfoot.isKeyDown("i") )
        {
            velocityNum.setText("Velocity: " + strength);
        }
    }

    /**
     * Method imageSwitch
     * Switches the image after shooting and resets it.
     */
    public void imageSwitch()
    {
        if ( canShoot == 89 )
        {setImage("bowfinished200.png");}
        if ( canShoot == 1 )
        {setImage("bowandarrow200.png");}
        if ( canShoot == 15 )
        {Greenfoot.playSound("bow_shoot_pull_short.wav"); }
    }

    /**
     * Act - do whatever the BowAndArrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        keyPresses();
        imageSwitch();
        if ( running )
        { canShoot--; }
        if ( shots >= 10 )
        { 
            running = false;
            velocityNum.setText("Press \"P\" to restart.");
        }

    }    
}
