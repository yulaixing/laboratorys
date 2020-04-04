package cn.techwolf.httpserver;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BootStrap {

    private static final String[] CONFIG_FILES=new String[]{"applicationContext*.xml"};


    public static void main(String[] args) {

        final long start = System.currentTimeMillis();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_FILES);

        context.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            context.close();

        }));


    }


}
