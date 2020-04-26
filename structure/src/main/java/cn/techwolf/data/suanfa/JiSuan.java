package cn.techwolf.data.suanfa;

import java.util.Calendar;
import java.util.Date;

/**
 * @author yl.xing
 * @create:2020-04-25
 * @describe
 **/
public class JiSuan {

    public static void main(String[] args) {

        int maxValue = Integer.MAX_VALUE;

        System.out.println(maxValue);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Calendar startTimeV2Calendar = (Calendar) calendar.clone();
        startTimeV2Calendar.add(Calendar.DAY_OF_YEAR, 1);
        startTimeV2Calendar.set(Calendar.HOUR_OF_DAY, 17);
        Date time = startTimeV2Calendar.getTime();

        System.out.println(time);



    }
}
