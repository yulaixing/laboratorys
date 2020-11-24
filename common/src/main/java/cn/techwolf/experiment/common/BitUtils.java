package cn.techwolf.experiment.common;

/**
 * @author yl.xing
 * @create:2020-04-01
 * @describe 记录对于字节的一些操作
 **/
public class BitUtils {

    public static void main(String[] args) {

        strHashCode();

        /* Java中int的位数为32，
           因此若左移运算符右侧的数字大于32的话，
           实际运算的位移的位数为其对32取余的数字*/
        System.out.println(1<<30);
//        System.out.println(4>>2|2);

        long i=0xee;

        System.out.println(i);

        String s = Integer.toBinaryString(12121);

        System.out.println(12121<<1);

//        String s = Integer.toBinaryString(12121<<1);

        System.out.println(s);

//        byte byZT=00001111; 0x01 0x02 0x03 0x04

        byte bye=0x12;

        int n0 = (bye & 0x01) == 0x01 ? 1 : 0;
        int n1 = (bye & 0x02) == 0x02 ? 1 : 0;
        int n2 = (bye & 0x04) == 0x04 ? 1 : 0;
        int n3 = (bye & 0x08) == 0x08 ? 1 : 0;
        int n4 = (bye & 0x10) == 0x10 ? 1 : 0;
        int n5 = (bye & 0x20) == 0x20 ? 1 : 0;
        int n6 = (bye & 0x40) == 0x40 ? 1 : 0;
        int n7 = (bye & 0x80) == 0x80 ? 1 : 0;

        System.out.print(n7);
        System.out.print(n6);
        System.out.print(n5);
        System.out.print(n4);
        System.out.print(n3);
        System.out.print(n2);
        System.out.print(n1);
        System.out.print(n0);



    }


    public static void strHashCode(){

        String str="a";

        for(int i=0;i<str.length();i++){

            char c = str.charAt(i);

            System.out.println("字符c="+c);

            System.out.println("字符c 强转="+(int)c);


        }


        System.out.println(str.hashCode());

        String str2="123";

        System.out.println("str2="+str2.hashCode());


        String str3="321";

        System.out.println("str3="+str3.hashCode());

    }



}
