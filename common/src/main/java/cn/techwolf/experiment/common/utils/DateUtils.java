package cn.techwolf.experiment.common.utils;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yl.xing
 * @create:2019-11-12
 * @describe
 **/
public class DateUtils {

    @Test
    public void test() {

        long day=howLongDidTheWorkTake(new Date(), "20170628");
        System.out.println("年轮=="+day);
    }



    public static long howLongDidTheWorkTake(Date now, String yyyymmdd) {

        try {

            long dayFormat = 24 * 60 * 60 * 1000;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

            Date parse = dateFormat.parse(yyyymmdd);

            long l = (now.getTime() - parse.getTime()) / dayFormat;

            return l;


        } catch (Exception e) {

        }


        return 1;
    }
}
