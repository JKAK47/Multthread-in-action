package org.vincent.threadlocal;

/**
 * @author PengRong
 * @package org.vincent.threadlocal
 * @ClassName SafeSeqCount.java
 * @date 2019/5/11 - 17:29
 * @ProjectName Multthread-in-action
 * @Description: 基于ThreadLocal 实现变量线程安全访问，怎么实现线程安全访问呢？不共享不就可以了。
 * 不共享变量就不会有并发问题
 */
public class SafeSeqCount {
    // 实现initialValue()
    private static ThreadLocal<Integer> seqCount = ThreadLocal.withInitial(() -> 0);
    public int nextSeq(){
        seqCount.set(seqCount.get() + 1);

        return seqCount.get();
    }
    public static void main(String[] args) {
        SafeSeqCount seqCount = new SafeSeqCount();

        SeqThread thread1 = new SeqThread(seqCount);
        SeqThread thread2 = new SeqThread(seqCount);
        SeqThread thread3 = new SeqThread(seqCount);
        SeqThread thread4 = new SeqThread(seqCount);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
    private static class SeqThread extends Thread{
        private SafeSeqCount seqCount;

        SeqThread(SafeSeqCount seqCount){
            this.seqCount = seqCount;
        }

        @Override
        public void run() {
            for(int i = 0 ; i < 3 ; i++){
                System.out.println(Thread.currentThread().getName() + " seqCount :" + seqCount.nextSeq());
            }
        }
    }
}
