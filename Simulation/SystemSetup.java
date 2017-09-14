import java.util.ArrayList;
//import java.util.Collections;
import java.util.Scanner;
import java.math.*;
import java.text.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * In this file the data structures that represent Celestial bodies are intialized with
 * the corresponding neccesary intial conditions such as mass, position, and velocity.
 * It then procedes in time and checks to make sure Conservation laws are being held such
 * Energy and momentum(both linear and angular) and modifies certain values to keep these 
 * laws in check. This iterates many times.
 * 
 * The data points in each iteration is saved in a file and is saved on the Desktop
 * 
 * @author Guillermo Frausto   
 * @version 0.0.2
 */
public class SystemSetup
{
    public static final double G = 6.67e-11;
    public static final double changeTime = 86400.0; // One earth day
    public static double Time = 0;
    public static String Data = "";
    public static int energyDelay = 5;
    public static void main (String[] args)
    {
        double xCM, yCM;
        String directoryname;
        NumberFormat formatter = new DecimalFormat("0.###E0");
        
        ArrayList<CelestialBody> system = new ArrayList<CelestialBody>();
        system = input(system);
        double totalMass = systemMass(system);
        //System.out.println("Name:\t\t\tX-Position:\t\tY-Position:\tDay");
        System.out.println("Bodies #\tX\tY\tDay");
        Data="Bodies #\tX\tY\tDay\n";
        Scanner scan = new Scanner(System.in);
        System.out.println("Where would you like to save your Data Points?(ex. C:/Users/Guillermo/Desktop/data.txt):");
        directoryname=scan.nextLine();
        for (int n = 0;n<=(365);n++)
        {
            //xCM = xCenterOfMass(system, totalMass);
            //yCM = yCenterOfMass(system, totalMass);
            //double zCM = zCenterOfMass(system, totalMass);
            //centerSystem(system, xCM,yCM);//zCM
            pointer(system);
            print(system,formatter);
            Time = Time + changeTime;
            computeNewValues(system);
            set(system);
            //computeEnergy(system);
        }
        System.out.println("\n" + Data);
        try { 
            File file = new File(directoryname);
            file.getParentFile().mkdirs();
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
 
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Data);
            bw.close();
 
            System.out.println("Done");
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method will load an empty ArrayList with the input of the user
     * 
     * @param  input   an empty ArrayList is best
     * @return     A loaded Arraylist with all of the Celestial bodies in the syst 
     */
    public static ArrayList<CelestialBody> input(ArrayList<CelestialBody> input)
    {
        int bodies;
        double Rx,Ry,Vx, Vy, mass;
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello Welcome to a solar system simulation\n"+
                           "First enter the amount of bodies you would like to simulate: " );
        bodies = scan.nextInt();
        for(int i = 1; i <= bodies; i++)
        {
            System.out.print("Enter the details of your #" + i + " celestial body:\n");
            
            System.out.print("Mass(kg): ");
            mass = scan.nextDouble();
            
            System.out.println("Coordinates: ");
            System.out.print("X value: ");
            Rx = scan.nextDouble();
            System.out.print("Y value: ");
            Ry = scan.nextDouble();
            
            System.out.println("Velocity:");
            System.out.print("X value: ");
            Vx = scan.nextDouble();
            System.out.print("Y value: ");
            Vy = scan.nextDouble();
            
            CelestialBody systemElement = new CelestialBody(Rx,Ry,Vx,Vy,mass);
            input.add(systemElement);
            energyDelay--;
        }
        //Collections.reverse(input);
        return input;
    }
    /**
     * This method will add up the entire mass of the user's System.
     * @ param 
     * @ return totalSum
     */
    public static double systemMass(ArrayList<CelestialBody> system)
    {
        double totalSum=0;
        for(CelestialBody m : system)
        {
            totalSum += m.getMass();
        }
        return totalSum;
    }
    /**
     * This method computes the center of mass in the x-direction in the user's System
     * from an ArrayList that holds CelestialBody objects.
     * 
     * @ param ArrayList<CelestialBogy> system 
     * @ return
     */
    public static double xCenterOfMass(ArrayList<CelestialBody> system,double M)
    {
        double xMass=0;
        for(CelestialBody m : system)
        {
            xMass += (m.getXPosition()*m.getMass());            
        }
        return xMass/M;
    }
    public static double yCenterOfMass(ArrayList<CelestialBody> system, double M)
    {
        double yMass=0;
        for(CelestialBody m : system)
        {
            yMass += (m.getYPosition()*m.getMass());            
        }
        return yMass/M;
    }
    /*public static double zCenterOfMass(ArrayList<CelestialBody> system, double M)
    {
        double zMomentum=0;
        for(CelestialBody m : system)
        {
            zMomentum += m.getZMomentum();            
        }
        return zMomentum/M;
    }*/
    public static void centerSystem(ArrayList<CelestialBody> system, double xCM, double yCM)//double zCM
    {
        for(CelestialBody m : system)
        {
            m.setPosition(m.getXPosition() - xCM,m.getYPosition() - yCM);//m.getZPosition() - ZCM
        }
    }
    public static void pointer(ArrayList<CelestialBody> system)
    {
        resetAcceleration(system);
        for(int i = 0; i < system.size(); i++)
        {
            for(int j = i + 1; j < system.size(); j++)
            {                
                computeAcceleration(system.get(i),system.get(j));
            }
        }
    } 
    public static void resetAcceleration(ArrayList<CelestialBody> system)
    {
        for(CelestialBody m : system)
        {
            m.setAcceleration(0,0);
        }
    }
    public static void computeAcceleration(CelestialBody R1,CelestialBody R2)
    {
        double Magnitude = distance(R1,R2);
       
        Magnitude = (G)/Math.pow(Magnitude,3);                    
        R1.addAcceleration((R2.getXPosition() - R1.getXPosition())*R2.getMass()*Magnitude,
        (R2.getYPosition() - R1.getYPosition())*R2.getMass()*Magnitude);
        
        R2.addAcceleration((R1.getXPosition() - R2.getXPosition())*R1.getMass()*Magnitude,
        (R1.getYPosition() - R2.getYPosition())*R1.getMass()*Magnitude);
    }
    public static double distance(CelestialBody R1, CelestialBody R2)
    {
        double D = (Math.sqrt(Math.pow((R1.getXPosition() - R2.getXPosition()),2) + 
        Math.pow(R1.getYPosition() - R2.getYPosition(),2)));
        return D;
    }
    public static void computeNewValues(ArrayList<CelestialBody> system)
    {
        for(CelestialBody m: system)
        {
            m.setNewVelocity((m.getXAcceleration() * changeTime)+ m.getXVelocity(),
            (m.getYAcceleration() * changeTime)+ m.getYVelocity());
            m.setNewPosition(0.5*(m.getXAcceleration() * Math.pow(changeTime,2))+ 
            (m.getXVelocity()*changeTime)+ m.getXPosition(),
            0.5*(m.getYAcceleration() * Math.pow(changeTime,2))+ (m.getYVelocity()*changeTime)
            + m.getYPosition());        
        }
        if(energyDelay ==0)
        {
            computeEnergy(system);
            adjustEnergy(system);
        }
    }
    public static void computeEnergy(ArrayList<CelestialBody> system)
    {
        resetUEnergy(system);
        for(int i = 0; i < system.size(); i++)
        {
            KEnergy(system.get(i));
            for(int j = i + 1; j < system.size(); j++)
            {
                UEnergy(system.get(i),system.get(j));
            }
        }
    }
    public static void resetUEnergy(ArrayList<CelestialBody> system)
    {
       for(CelestialBody m : system)
       {
           m.setUEnergy(0,0);           
       }
    }
    public static void KEnergy(CelestialBody object)
    {
        double K1, K2;
        K1 = 0.5*Math.pow(object.velocityMagnitude(), 2)*object.getMass();
        K2 = 0.5*Math.pow(object.newvelocityMagnitude(), 2)*object.getMass();
        object.setKEnergy(K1,K2);
    }
    public static double newDistance(CelestialBody R1, CelestialBody R2)
    {
        return (Math.sqrt(Math.pow((R1.getNewXPosition() - R2.getNewXPosition()),2) + 
        Math.pow(R1.getNewYPosition() - R2.getNewYPosition(),2)));
    }
    public static void UEnergy(CelestialBody R1,CelestialBody R2)
    {
        double oldU, newU;
        oldU = -1*G*R1.getMass()*R2.getMass()/distance(R1,R2);
        newU = -1*G*R1.getMass()*R2.getMass()/newDistance(R1,R2);
        R1.addUEnergy(oldU, newU);
        R2.addUEnergy(oldU, newU);
    }
    public static void adjustEnergy(ArrayList<CelestialBody> system)
    {
        for(CelestialBody m : system)
        {
            if(energyDelay ==0)
            {
                double MagnitueNewVelocity = computeNewVelocityMagnitude(m);
                m.setNewVelocity(
                (Math.sqrt(2*(m.getOldKEnergy()+m.getOldUEnergy() - m.getNewUEnergy())/m.getMass())/
                MagnitueNewVelocity)*m.getXVelocity(),
                (Math.sqrt(2*(m.getOldKEnergy()+m.getOldUEnergy() - m.getNewUEnergy())/m.getMass())/
                MagnitueNewVelocity)*m.getYVelocity());
            }
        }
    }
    public static double computeNewVelocityMagnitude(CelestialBody m)
    {
        return Math.sqrt(Math.pow(m.getNewXVelocity(),2) + Math.pow(m.getNewYVelocity(),2));
    }
    public static void print(ArrayList<CelestialBody> system, NumberFormat formatter)
    {
        int n = 1;
        String tempData;
        
        for(CelestialBody m : system)
        {
            /*System.out.println("Celestial Body: #" + n + "\t" + formatter.format(m.getXPosition()) + "\t\t\t" +
            formatter.format(m.getYPosition())+ "\t\t" +(Time/changeTime)+"\t"+
            formatter.format(m.getMass()));*/
            System.out.print(n + "\t" + formatter.format(m.getXPosition()) + "\t" + formatter.format(m.getYPosition()) + "\t");
            tempData = (n + "\t" + formatter.format(m.getXPosition()) + "\t" + formatter.format(m.getYPosition()) + "\t");
            Data = Data +tempData;
            n++;
            
        }
        Data = Data + "\n";
        System.out.println();
    }
    public static void set(ArrayList<CelestialBody> system)
    {
        for(CelestialBody m : system)
        {
            m.setVelocity(m.getNewXVelocity(),m.getNewYVelocity());
            m.setPosition(m.getNewXPosition(),m.getNewYPosition());
        }
    }
}