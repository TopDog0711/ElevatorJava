/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorjava;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Mike
 */
public class Controller  implements Runnable{
    ArrayList<Elevator> CarList;
    int NumCars;
    int NumFloors;
    int TickTime = 250; //in milliseconds
    

    public Controller(ArrayList<Elevator> CarList) {
        this(); 
        this.CarList = CarList;
    }

    public Controller() {
        System.out.println("Controller Initialised");
        NumCars = 2;
        NumFloors = 10;
        CarList = new ArrayList<>();
        
        
    }

    public Controller(int NumCars, int NumFloors) {
        this(new ArrayList<Elevator>());
        this.NumCars = NumCars;
        this.NumFloors = NumFloors;
        
    }

    public Controller(ArrayList<Elevator> CarList, int NumCars, int NumFloors) {
        
        this.CarList = CarList;
        this.NumCars = NumCars;
        this.NumFloors = NumFloors;
    }
    
    public Boolean Init()
    {
        if(CarList.isEmpty())
        {
            for(int i =0;i< NumCars; i++)
            {
                CarList.add(new Elevator());
                
            }
        }
        
        
        return true;
    }
    
    @Override
    public void run()
    {
        do{
            System.out.println("Waiting...");
            try {
                Thread.sleep(TickTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while(true);
        
    }
    
    
    
    
}
