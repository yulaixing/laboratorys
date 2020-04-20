package cn.techwolf.data.suanfa;

import java.text.DecimalFormat;

/**
 * @author yl.xing
 * @create:2020-04-20
 * @describe
 **/
public class Pingfang {


    public static String getPingfangGen(int shuz){

        double i=0;
        double j=shuz;

        while(i<=j){
            double m=i+ (j-i)/2;
            if(m*m>shuz){
               j=m;
            }else if(shuz-m*m <0.000001){
                System.out.println(m);
                DecimalFormat df = new DecimalFormat("0.000000");
                return df.format(m);
            }else{
              i=m;
            }

        }

        return "0";

    }




}
