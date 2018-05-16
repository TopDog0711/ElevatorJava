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
public class ElevatorJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        
        Thread runThread;
        Controller cont = new Controller(1,10);
        if(cont.Init())
        {
           runThread = new Thread(cont);
           runThread.start();
           runThread.join();
            
          
        }
        
        
    }
    
}
