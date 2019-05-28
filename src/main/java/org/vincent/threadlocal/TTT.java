package org.vincent.threadlocal;

/**
 * @author PengRong
 * @package org.vincent.threadlocal
 * @ClassName TTT.java
 * @date 2019/5/12 - 14:20
 * @ProjectName Multthread-in-action
 * @Description: TODO
 */
public class TTT {
    public static void main(String[] args) {
        retry:
        for (;;){
            for (int i=0;i<100;i++){
                if (i==50)break retry;
                System.out.println(i);
            }
        }
        System.out.println("break retry");
    }
}
