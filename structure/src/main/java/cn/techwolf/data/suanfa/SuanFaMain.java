package cn.techwolf.data.suanfa;

import java.lang.reflect.*;
import java.text.DecimalFormat;

/**
 * @author yl.xing
 * @create:2020-04-20
 * @describe
 **/
public class SuanFaMain<E,F,G> {

    public static void main(String[] args) {

        String pingfangGen = Pingfang.getPingfangGen(9);
        System.out.println(pingfangGen);

        DecimalFormat df = new DecimalFormat("0.00");

        double b=0.228;

        System.out.println(df.format(b));


//        new SuanFaMain().getClass().new

        Type genericSuperclass = new SuanFaMain().getClass().getGenericSuperclass();

        System.out.println(genericSuperclass.getTypeName());


        SuanFaMain<String, Integer, Object> suanfa = new SuanFaMain<>();

//        Class<?> e = find0(suanfa, SuanFaMain.class, "E");


    }



}
