package org.vincent.threadlocal;

/**
 * @author PengRong
 * @package org.vincent.threadlocal
 * @ClassName UnSafeSeqCount.java
 * @date 2019/5/11 - 17:42
 * @ProjectName Multthread-in-action
 * @Description: 基于ThreadLocal 实现变量线程不安全访问，threadlocal.initialValue方法返回不同的值
 */
public class UnSafeSeqCount {
    private A a = new A();
    private ThreadLocal<A> threadLocal = ThreadLocal.withInitial(() -> a);

    /**
     * 每个线程持有相同的变量a
     */

    public int nextSeq() {
        threadLocal.get().setNumber(threadLocal.get().getNumber() + 1);

        return threadLocal.get().getNumber();
    }

    public static void main(String[] args) throws InterruptedException {
        UnSafeSeqCount unSafeSeqCount = new UnSafeSeqCount();
        SeqThread thread1 = new SeqThread(unSafeSeqCount);
        SeqThread thread2 = new SeqThread(unSafeSeqCount);
        SeqThread thread3 = new SeqThread(unSafeSeqCount);
        SeqThread thread4 = new SeqThread(unSafeSeqCount);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    private static class SeqThread extends Thread {
        private UnSafeSeqCount seqCount;

        SeqThread(UnSafeSeqCount seqCount) {
            this.seqCount = seqCount;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " seqCount :" + seqCount.nextSeq());
            }
        }
    }

}

class A {
    public A() {

    }

    public A(int number) {
        this.number = number;
    }

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
