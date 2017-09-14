 
/**
 * Write a description of class CelestialBody here.
 * 
 * @author Guillermo Frausto 
 * @version March 22, 2015
 */
public class Celestial
{
    // instance variables - replace the example below with your own
    private double xPosition;
    private double yPosition;
    private double tempxPosition;
    private double tempyPosition;
    //private double zPosition;
    //private double tempzPosition;
    
    private double xVelocity;
    private double yVelocity;
    private double tempxVelocity;
    private double tempyVelocity;
    //private double zVelocity;
    //private double tempzVelocity;
    
    private double mass;
    private double xMomentum;
    private double yMomentum;
    private double Energy;
    /**
     * Constructor for objects of class CelestialBody
     */
    public Celestial(double xposition,double yposition,double xvelocity,double yvelocity, double Mass)
    {
        this.xPosition = xposition;
        this.yPosition = yposition;
        //this.zPosition = zposition;
        this.xVelocity = xvelocity;
        set_xMomentum();
        this.yVelocity = yvelocity;
        set_yMomentum();
        //this.zVelocity = zvelocity;
        this.mass = Mass;
    }
    /**
     * This method will retrieve the vale of the Y-corodinate.
     * 
     * @ param none
     * @ return xPosition
     */
    public double get_xPosition()
    {
        return this.xPosition;
    }
    /**
     * @ param a double value - usually for adjusting for new center of mass for system.
     */
    public void set_xPosition(double newX)
    {
        this.xPosition = newX;
    }
    
    /**
     * This method will retrieve the vale of the X-corodinate.
     * 
     * @ param none
     * @ return yPosition
     */
    public double get_yPosition()
    {
        return this.yPosition;
    }
    
    public void set_yPosition(double newY)
    {
        this.yPosition = newY;
    }
    /*
    public double get_zPosition()
    {
        return this.zPosition;
    }
    public void set_zPosition(newZ)
    {
        this.zPosition = newZ
    }
    */
    
    public double get_xVelocity()
    {
        return this.xVelocity;
    }
    public void set_xVelocity(double newX)
    {
        this.xVelocity = newX;
        set_xMomentum();
    }
    public double get_yVelocity()
    {
        return this.yVelocity;
    }
    public void set_yVelocity(double newY)
    {
        this.yVelocity = newY;
        set_yMomentum();
    }
    /*
    public double get_zVelocity()
    {
        return this.zVelocity;
    }
    public void set_zVelocity(double newZ)
    {
        this.zVelocity = newZ;
    }
    */
    /**
     * This method will retrieve the value of the mass of the object
     * 
     * @ param none
     * @ return double mass
     */
    public double get_mass()
    {
       return this.mass;
    }
    public double get_xMomentum()
    {
        return this.xMomentum;
    }
    public void set_xMomentum()
    {
        this.xMomentum = this.xVelocity * this.mass;    
    }
    public double get_yMomentum()
    {
        return this.yMomentum;
    }
    public void set_yMomentum()
    {
        this.yMomentum = this.yVelocity * this.mass;        
    }
    public double positionMagnitude()
    {
        return Math.sqrt(this.xPosition * this.xPosition + this.yPosition * yPosition);//zPosition *zPosition
    }
    public double velocityMagnitude()
    {
        return Math.sqrt(this.xVelocity * this.xVelocity + this.yVelocity * this.yVelocity);
    }
    /*public double set_zMomentum()
    {
        this.zMomentum = this.zVelocity * this.mass        
    }/*
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int sampleMethod(int y, int x)
    {
        // put your code here
        return x + y;
    }
    /*
    @Override
    public String toString ()
    {
       return("Xposition: \t%d\n Yposition: \t%d\n" Xposition,Yposition);
    }*/
    public void set_tempxPosition(double newX)
    {
        this.tempxPosition = newX;
    }
    public void set_tempyPosition(double newY)
    {
        this.tempyPosition = newY;
    }
    /*
    public void set_tempzPosition(double newZ)
    {
        this.tempzPosition = newZ;
    }*/
    public void set_tempxVelocity(double newX)
    {
        this.tempxPosition = newX;
    }
    public void set_tempyVelocity(double newY)
    {
        this.tempyPosition = newY;
    }
    /*
    public void set_tempzVelocity(double newZ)
    {
        this.tempzPosition = newZ;
    }*/
    public double get_tempxPosition()
    {
        return this.tempxPosition;
    }
    public double get_tempyPosition()
    {
        return this.tempyPosition;
    }
    /*
    public double get_tempzPosition()
    {
        return this.tempzPosition;
    }*/
    public double get_tempxVelocity()
    {
        return this.tempxPosition;
    }
    public double get_tempyVelocity()
    {
        return this.tempyPosition;
    }
    /*
    public double get_tempzVelocity()
    {
        return this.tempzPosition;
    }*/
}

