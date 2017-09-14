
/**
 * Write a description of class CelestialBody here.
 * 
 * @author Guillermo Frausto 
 * @version March 22, 2015
 */
public class CelestialBody
{
    // instance variables - replace the example below with your own
    private double[][] Position = new double[2][2];    
    private double[][] Velocity = new double[2][2];
    private double[] Acceleration = new double [2];
    
    //private double zVelocity;
    //private double tempzVelocity;
    
    private double mass;
    private double[][] Momentum = new double[2][2];
    private double [][]Energy = new double[2][2];   
    /**
     * Constructor for objects of class CelestialBody
     */
    public CelestialBody(double xposition,double yposition,double xvelocity,double yvelocity, double Mass)
    {
        setPosition(xposition,yposition);
        setVelocity(xvelocity, yvelocity);
        setMomentum();
        this.mass = Mass;
    }
    /**
     * @ param 
     */
    public void setPosition(double newX,double newY)
    {
        this.Position[0][0] = newX;
        this.Position[0][1] = newY;
        //this.Position[0][2] = newZ;
    }
    /**
     * This method will retrieve the y value of the Position vector.
     * 
     * @ param none
     * @ return double value
     */
    public double getXPosition()
    {
        return this.Position[0][0];
    }
    /**
     * This method will retrieve the x value of the Position vector.
     * 
     * @ param none
     * @ return double value
     */
    public double getYPosition()
    {
        return this.Position[0][1];
    }
     /*
    public double getZPosition()
    {
        return this.Position[0][2];
    }  */  
    public void setVelocity(double newX,double newY)
    {
        this.Velocity[0][0] = newX;
        this.Velocity[0][1] = newY;
        //this.Velocity[0][2] = newZ;
        setMomentum();
    }
    public double getXVelocity()
    {
        return this.Velocity[0][0];
    }
    public double getYVelocity()
    {
        return this.Velocity[0][1];
    }
    /*
    public double getZVelocity()
    {
        return this.zVelocity[0][2];
    }   
    */
    public void setAcceleration(double newX,double newY)
    {
        this.Acceleration[0] = newX;
        this.Acceleration[1] = newY;
        //this.Acceleration[2] = newZ;
    }
    public void addAcceleration(double newX,double newY)
    {
        this.Acceleration[0] += newX;
        this.Acceleration[1] += newY;
        //this.Acceleration[2] = newZ;
    }
    public double getXAcceleration()
    {
        return this.Acceleration[0];
    }
    public double getYAcceleration()
    {
        return this.Acceleration[1];
    }
    public double getZAcceleration()
    {
        return this.Acceleration[2];
    }
    /**
     * This method will retrieve the value of the mass of the object
     * 
     * @ param none
     * @ return double mass
     */
    public double getMass()
    {
       return this.mass;
    }
    public void setMomentum()
    {
        this.Momentum[0][0] = this.Velocity[0][0] * this.mass; 
        this.Momentum[0][1] = this.Velocity[0][1] * this.mass;
        //this.xMomentum[0][2] = this.Velocity[0][2] * this.mass; 
    }
    public double getXMomentum()
    {
        return this.Momentum[0][0];
    }
    public double getYMomentum()
    {
        return this.Momentum[0][1];
    }
    public double positionMagnitude()
    {
        return Math.sqrt(Math.pow(this.Position[0][0],2) + Math.pow(this.Position[0][1],2));
    }
    public double velocityMagnitude()
    {
        return Math.sqrt(Math.pow(this.Velocity[0][0],2) + Math.pow(this.Velocity[0][1],2));
    }
    /*
    @Override
    public String toString ()
    {
       return("Xposition:\t%d\n Yposition: \t%d\n" Xposition,Yposition);
    }*/
    public void setNewPosition(double newX,double newY)
    {
        this.Position[1][0] = newX;
        this.Position[1][1] = newY;
        //this.Position[1][2] = newZ;
    }
    public double getNewXPosition()
    {
        return this.Position[1][0];
    }
    public double getNewYPosition()
    {
        return this.Position[1][1];
    }
    public double positionNewMagnitude()
    {
        return Math.sqrt(Math.pow(this.Position[1][0],2) + Math.pow(this.Position[1][1],2));
    }
    /*
    public double getNewZPosition()
    {
        return this.Position[1][2];
    }*/
    public void setNewVelocity(double newX, double newY)
    {
        this.Velocity[1][0] = newX;
        this.Velocity[1][1] = newY;
        //this.Velocity[1][2] = newZ;
        //setNewMomentum();
    }
    public double getNewXVelocity()
    {
        return this.Velocity[1][0];
    }
    public double getNewYVelocity()
    {
        return this.Velocity[1][1];
    }
    public double newvelocityMagnitude()
    {
        return Math.sqrt(Math.pow(this.Velocity[1][0],2) + Math.pow(this.Velocity[1][1],2));
    }
    public void setNewMomentum()
    {
        this.Momentum[1][0] = this.Velocity[1][0] * this.mass; 
        this.Momentum[1][1] = this.Velocity[1][1] * this.mass;
        //this.xMomentum[1][2] = this.Velocity[1][2] * this.mass; 
    }
    public void setKEnergy(double oldE,double newE)
    {
        this.Energy[0][0] = oldE;
        this.Energy[1][0] = newE;
    }
    public void setUEnergy(double oldE, double newE)
    {
        this.Energy[0][1] = oldE;
        this.Energy[1][1] = newE;
    }
    public void addUEnergy(double oldE, double newE)
    {
        this.Energy[0][1] += oldE;
        this.Energy[1][1] += newE;
    }
    public double getOldKEnergy()
    {
        return this.Energy[0][0];
    }
    public double getNewKEnergy()
    {
        return this.Energy[1][0];
    }
    public double getOldUEnergy()
    {
        return this.Energy[0][1];
    }
    public double getNewUEnergy()
    {
        return this.Energy[1][1];
    }
    /*
    public double getNewZVelocity()
    {
        return this.Velocity[1][2];
    }*/
}


