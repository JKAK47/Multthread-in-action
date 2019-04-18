package org.vincent.multthread.notify;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PengRong
 * @package org.vincent.multthread.notify
 * @ClassName DateSource.java
 * @date 2019/3/23 - 14:06
 * @ProjectName Multthread-in-action
 * @Description: 作为生产者和消费者之间 数据的容器
 */
public class DateSource {
    public static  int CAPACITY =50;
    private static volatile List<String> date = new ArrayList<>();

    public static String getDate() {
        return (date.isEmpty() ? null : date.get(0));
    }

    public static void setDate(String dates) {
         date.add(dates);
    }
    public static int getDateSize(){
        return date.size();
    }
}
