package org.vincent.multthread.volatile00;

/**
 * Multthread-in-action.org.vincent.multthread.Volatile <br/>
 * Created by PengRong on 2018/7/10. <br/>
 *
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-07-10 10:05 <br/>
 */
public class  VolatileDemo extends  Thread{
    volatile boolean flag=false;
    int i=0;

    @Override
    public void run() {
        while (!flag){
            i++;
        }
    }
}