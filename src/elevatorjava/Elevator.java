/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorjava;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Mike
 */
public class Elevator implements Runnable{
    int CurrentFloor;
    int DestinationFloor;
    List<Integer> FloorQueue;
    int TickTime = 125; //milliseconds
    int CarNum;

    public Elevator() {
    }

    public Elevator(int CurrentFloor, int CarNum) {
        this.CurrentFloor = CurrentFloor;
        this.CarNum = CarNum;
    }
    
    

    @Override
    public void run() {
         try {
                
                Thread.sleep(TickTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
   
    
    
    
}
