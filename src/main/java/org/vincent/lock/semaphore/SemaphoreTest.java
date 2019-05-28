package org.vincent.lock.semaphore;

/**
 * @author PengRong
 * @package org.vincent.lock.semaphore
 * @ClassName SemaphoreTest.java
 * @date 2019/4/27 - 23:31
 * @ProjectName Multthread-in-action
 * @Description: TODO
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Parking parking = new Parking(3);

        for(int i = 0 ; i < 500 ; i++){
            new Car(parking).start();
        }
    }
}
