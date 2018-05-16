/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevatorjava;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class Controller implements Runnable {

    ArrayList<Elevator> CarList;
    ArrayList<Thread> Threads;
    int NumCars;
    int NumFloors;
    int TickTime = 250; //in milliseconds
    Random rng = new Random();
    int MaxTimeToWaitForCarCall = 10000; //in milliseconds
    

    

    public Controller(ArrayList<Elevator> CarList) {
        this();
        this.CarList = CarList;
    }

    public Controller() {
        System.out.println("Controller Initialised");
        NumCars = 2;
        NumFloors = 10;
        CarList = new ArrayList<>();
        Threads = new ArrayList<>();

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

    public Boolean Init() {
        if (CarList.isEmpty()) {
            for (int i = 0; i < NumCars; i++) {
                CarList.add(new Elevator(i + 1, i + 1));

            }
        }

        return true;
    }

    @Override
    public void run() {
        try {
            for (Elevator car : CarList) {
                Thread t = new Thread(car);
                t.start();
                Threads.add(t);
            }

            do {
                System.out.println("Waiting...");
                int floorRequested = WaitForCarRequest();
                System.out.println(String.format("Got call to floor %d...", floorRequested));

                Elevator closestCar = null;
                int longestRun = NumFloors * 2;

                do {
                    for (Elevator car : CarList) {
                        int thisCarRun = car.NumStopsToFloor(floorRequested);
                        if (thisCarRun <= longestRun) {
                            closestCar = car;
                            longestRun = thisCarRun;
                        }
                       
                    }
                    if(closestCar == null) //All cars might be in service... wait for them to tick off service
                    {
                        Thread.sleep(TickTime);
                    }
                   
                    
                } while (closestCar == null && longestRun !=0);
                
                if(longestRun == 0)
                {
                    System.out.println(String.format("Car is at floor %d...", floorRequested));
                }
                else{
                    closestCar.AcceptRequestToFloor(floorRequested);
                }
                
                 
                Thread.sleep(TickTime);

            } while (true);

        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    int WaitForCarRequest() throws InterruptedException {
        int numMillisecondsToWait = rng.nextInt(MaxTimeToWaitForCarCall);

        Thread.sleep(numMillisecondsToWait);

        return rng.nextInt(9) + 1;

    }

}
