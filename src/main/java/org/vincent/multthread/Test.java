package org.vincent.multthread;

/**
 * @Package: org.vincent.multthread <br/>
 * @Description： TODO <br/>
 * @author: lenovo <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2019 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by lenovo on 2018/12/26 <br/>
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Test test =new Test();
        test.waittest();
    }
    public  synchronized void  waittest()  {
        notify();
    }
}
