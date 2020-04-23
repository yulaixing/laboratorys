package cn.techwolf.data.suanfa;

import java.text.DecimalFormat;

/**
 * @author yl.xing
 * @create:2020-04-20
 * @describe
 **/
public class SuanFaMain {

    public static void main(String[] args) {

        String pingfangGen = Pingfang.getPingfangGen(9);
        System.out.println(pingfangGen);

        DecimalFormat df = new DecimalFormat("0.00");

        double b=0.228;

        System.out.println(df.format(b));


//        new SuanFaMain().getClass().new

    }
}
