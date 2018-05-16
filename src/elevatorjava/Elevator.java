/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorjava;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author Mike
 */
public class Elevator implements Runnable{
    int CurrentFloor = 1;
    int DestinationFloor = 1;
    List<Integer> FloorList;
    int TickTime = 125; //milliseconds
    int CarNum;
    int NumTicks =0;
    eDirection CurrentDirection;
    CarState CurrentState;
    CarState PreviousState;
    int NumTrips = -1;
    int MaxTripsBeforeService = 100;
    int TicksPerFloor = 5;
    

    public Elevator() {
        CurrentState = new WaitState(this);
        FloorList = new ArrayList<>();
        

    }

    public Elevator(int CurrentFloor, int CarNum) {
        this();
        this.CurrentFloor = CurrentFloor;
        this.CarNum = CarNum;
        this.CurrentDirection = eDirection.Up;
    }


    public int NumStopsToFloor(int floor)
    {
        return Math.abs( DestinationFloor - floor);
    }
    
    public void AcceptRequestToFloor(int floor)
    {
        if(!FloorList.contains(floor))
        {
            FloorList.add(floor);
            Collections.sort(FloorList);
 
        }
        
        if(CurrentState instanceof WaitState)
        {
           CurrentState = new MoveState(this);
        }
    }

    public void Think(int ticks)
    {
        if(CurrentState != null)
        {
           CurrentState = CurrentState.handleState(ticks);
        }

    }

    @Override
    public void run() {
         try {
             do{
                Think(NumTicks++);
                Thread.sleep(TickTime);
             }while(true);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
   
    
    
    
}
