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

public class WaitState implements CarState{

    private final String Name = "Waiting";
    private final Elevator Car;

    public WaitState( Elevator Car) {
       
        this.Car = Car;
    }

   

    @Override
    public String getName() {
       return Name;
    }

    @Override
    
    public CarState handleState(int ticks) {
        
        if((ticks % 20) == 0)
        {
            System.out.println( String.format("Car %d is Waiting at Floor %d", Car.CarNum , Car.CurrentFloor));
        }
        
        return this;
        
    }
    
}