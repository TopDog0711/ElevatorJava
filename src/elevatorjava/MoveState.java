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
public class MoveState implements CarState{

    private final String Name = "Moving";
    private final Elevator Car;

    public MoveState(Elevator Car) {
        this.Car = Car;
        this.Car.NumTrips+=1;
        
        if(this.Car.CurrentFloor > this.Car.DestinationFloor)
        {
            this.Car.CurrentDirection = eDirection.Down;
        }
        else{
            this.Car.CurrentDirection = eDirection.Up;
        }
        
    }
    
    
   
    @Override
    public String getName() {
       return Name;
    }

    @Override
    public CarState handleState(int ticks) {
        
        CarState returnState = this;
        
        if(ticks % Car.TicksPerFloor == 0)
        {
             if(this.Car.CurrentFloor > this.Car.DestinationFloor)
            {
                this.Car.CurrentDirection = eDirection.Down;
            }
            else{
                this.Car.CurrentDirection = eDirection.Up;
            }
            
            
            
            if(this.Car.CurrentDirection == eDirection.Up) 
            {
                this.Car.CurrentFloor +=1;
                
            }
            else
            {
                this.Car.CurrentFloor -= 1;
            }
        }
            


        if(this.Car.NumTrips >= this.Car.MaxTripsBeforeService)
        {
            returnState =  new InServiceState(this.Car);
        }
        else if(this.Car.CurrentFloor == this.Car.DestinationFloor && this.Car.FloorList.isEmpty())
        {
            System.out.println( String.format("Car %d has arrived at Floor %d", Car.CarNum , Car.CurrentFloor));
            returnState =  new WaitState(this.Car);

        }
        else if(this.Car.CurrentFloor == this.Car.DestinationFloor && !this.Car.FloorList.isEmpty())
        {
            this.Car.DestinationFloor = this.Car.FloorList.get(0);
            this.Car.FloorList.remove(0);

            if(this.Car.CurrentFloor > this.Car.DestinationFloor)
            {
                this.Car.CurrentDirection = eDirection.Down;
            }
            else{
                this.Car.CurrentDirection = eDirection.Up;
            }

            System.out.println( String.format("Car %d has arrived at Floor %d, Taking next call to floor %d", Car.CarNum , Car.CurrentFloor, this.Car.DestinationFloor));
            returnState =  new MoveState(this.Car);
        }
        else if(this.Car.FloorList.contains(Car.CurrentFloor)) //stop here, because we had a call here
        {
             System.out.println( String.format("Car %d is stopping at Floor %d enroute to %d", Car.CarNum , Car.CurrentFloor, Car.DestinationFloor));
             returnState =  new MoveState(this.Car);
        }
        else{
             System.out.println( String.format("Car %d is moving from floor %d to floor %d", Car.CarNum , Car.CurrentFloor, Car.DestinationFloor));

        }
            
     
        

        return returnState;
    }
    
    
}
