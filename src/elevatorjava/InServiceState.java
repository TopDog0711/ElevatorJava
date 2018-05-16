/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorjava;

/**
 *
 * @author Mike
 */
public class InServiceState implements CarState
{
   private final String Name = "In Service";
   private final Elevator Car;
   private final int MaxServiceTicks = 50;
   private int NumServiceTicks = 0;

    public InServiceState(Elevator Car) {
        
        this.Car = Car;   
        this.Car.NumTrips = 0;
    }
  
    @Override
    public String getName() {
       return Name;
    }

    @Override
    public CarState handleState(int ticks) {
        NumServiceTicks += ticks;
        if(NumServiceTicks > MaxServiceTicks)
        {
            System.out.println( String.format("Car %d is finished with service at Floor %d", Car.CarNum , Car.CurrentFloor));
            return new WaitState(this.Car);
        }else{
            if((ticks % 20) == 0)
            {
                System.out.println( String.format("Car %d is being services at Floor %d", Car.CarNum , Car.CurrentFloor));
            }
        }
        
        return this;
    }
    
}
