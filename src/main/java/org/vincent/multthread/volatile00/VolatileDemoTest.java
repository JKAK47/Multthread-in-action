package org.vincent.multthread.volatile00;

/**
 * Multthread-in-action.org.vincent.multthread <br/>
 * Created by PengRong on 2018/7/10. <br/>
 *https://www.cnblogs.com/dzblog/p/6913809.html
 *
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-07-10 9:57 <br/>
 */
public class VolatileDemoTest {

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo test= new VolatileDemo();
        test.start();
        System.out.println("begin sleep");
        Thread.sleep(2000);
        test.flag.setFlag(true);
        System.out.println(test.i);
    }

}
