package cn.techwolf.experiment.common;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yl.xing
 * @create:2020-07-14
 * @describe
 **/
public class RequestIdUtils {

    static ThreadLocal<Integer> threadLocal=new ThreadLocal();

    private static AtomicInteger atomicInteger=new AtomicInteger();

    public static int getId(){
        return threadLocal.get();
    }

    public static void setId(){
        threadLocal.set(atomicInteger.getAndIncrement());
    }


    public static void main(String[] args) {


//        try {
//
//            for(int i=0;i<5;i++){
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        setId();
//
//                        System.out.println(getId());
//
//
//
//                    }
//                }).start();
//            }
//
//
//
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        String str="3f2d046af2b8a843AcbiDUAuxf7IFgQBh3stnVUeR0dl4JLNBxf5TBNR8Dgljaiq8FAoTG3C7lkRPJG9yVbhS9X00an4FG251LdoJgM~";

        System.out.println(str.length());

    }



}
