package  org.vincent.multthread;

import org.junit.Test;
import org.vincent.singleton.EnumSingleton;

/**
 * @Package: PACKAGE_NAME <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/7/15 12:52 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/7/15. <br/>
 */

public class TestDemo {
		@Test
		public void  testDEMO(){
				System.out.println("test demo ...");
		}

	static final int SHARED_SHIFT   = 16;
	static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
	static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
	static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

	static int sharedCount(int c)    { return c >>> SHARED_SHIFT; }
	static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; }

	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(SHARED_SHIFT));
		System.out.println(Integer.toBinaryString(SHARED_UNIT));
		System.out.println(Integer.toBinaryString(MAX_COUNT));
		System.out.println(Integer.toBinaryString(EXCLUSIVE_MASK));
		System.out.println(Integer.toBinaryString(exclusiveCount(10)));
		System.out.println(Integer.toBinaryString(sharedCount(0+SHARED_UNIT)));
		System.out.println(Integer.toBinaryString(sharedCount(0+SHARED_UNIT+SHARED_UNIT)));
		System.out.println(EnumSingleton.class);
	}
}
