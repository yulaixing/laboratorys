package cn.techwolf.dubbo.relate.Provider;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Provider {

    private static final String[] CONFIG_FILES = new String[]{"provider.xml"};


    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Provider.class);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_FILES);

        context.start();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
