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
public interface CarState {
    String getName();
    CarState handleState(int ticks);
}


