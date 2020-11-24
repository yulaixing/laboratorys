package cn.techwolf.experiment.common;

/**
 * @author yl.xing
 * @create:2020-05-19
 * @describe
 **/
public class Hook {

    static class Message extends Thread {


        @Override
        public void run() {
            System.out.println("good bye");
        }
    }

    public static void main(String[] args) throws Exception {

//        Runtime.getRuntime().addShutdownHook(new);

        String abc = "123";
        System.out.println(abc.hashCode());
//        abc.intern();

        String bcd = "123";
        System.out.println(bcd.hashCode());


        System.out.println("与="+(100&2));

        System.out.println("或="+(1|2));

        System.out.println("异或="+(2^2));



        //01 10

//        Runtime.getRuntime().addShutdownHook(new Message());
//
//        Thread.sleep(3000);
//
//        System.out.println("main thread is closed");
//
//        System.out.println(false&&true||true);

        int hash = hash(abc, 16);
        System.out.println(hash);


        int m=11;

        System.out.println(~m);


    }


    static int hash(Object key, int capitity) {
        int h = key.hashCode();


        System.out.println(h>>>16);

        System.out.println(h^0);
        return (h ^ (h >>> 16)) & (capitity - 1); //capicity表示散列表的大小
    }

}
