package org.vincent.multthread.volatile00;

/**
 * Multthread-in-action.org.vincent.multthread.Volatile <br/>
 * Created by PengRong on 2018/7/10. <br/>
 *
 * @author PengRong <br/>
 * @Description volatile 线程访问可见性，保证线程读取到最新值
 * @ClassName: ${CLASS}
 * @since 2018-07-10 10:05 <br/>
 */
public class VolatileDemo extends Thread {
    public VolatileDemo.flag flag = new flag();
    int i = 0;

    @Override
    public void run() {
        while (!flag.isFlag()) {
            i++;
        }
    }

    class flag {
        /*volatile 线程访问可见性，保证线程读取到最新值 */
        volatile boolean flag = false;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }

}