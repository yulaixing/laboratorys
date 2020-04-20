package cn.techwolf.experiment.common;

import java.util.ArrayList;

/**
 * @author yl.xing
 * @create:2020-04-07
 * @describe
 **/
public class MainTest {

    public static void main(String[] args) {

        String special = null;

        String test = "abc" + special;

        System.out.println(test);

        test.intern();

        ArrayList<Object> objects = new ArrayList<>();



        final Object obj = new Object();


        for (int i = 0; i < 5; i++) {

            final int j = i;

            new Thread(new Runnable() {
                @Override
                public void run() {

                    synchronized (obj) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(j);
                    }

                }
            }).start();


        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (obj) {
                    obj.notify();
                }
            }
        }).start();

        Class<?> aClass = objects.getClass();

        System.out.println(aClass.getName());



    }

    @Override
    protected void finalize() throws Throwable {

        System.out.println("yixianshengji");
        super.finalize();
    }
}
