package org.vincent.lock.semaphore;

/**
 * @author PengRong
 * @package org.vincent.lock.semaphore
 * @ClassName Car.java
 * @date 2019/4/27 - 23:31
 * @ProjectName Multthread-in-action
 * @Description: TODO
 */
public class Car extends Thread{
    Parking parking ;

    Car(Parking parking){
        this.parking = parking;
    }

    @Override
    public void run() {
        parking.park();     //进入停车场
    }
}
