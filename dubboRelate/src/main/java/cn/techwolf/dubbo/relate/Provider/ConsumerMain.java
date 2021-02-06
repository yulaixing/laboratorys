package cn.techwolf.dubbo.relate.Provider;

import cn.techwolf.dubbo.relate.Provider.instance.ProviderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerMain {

    private static final String[] CONFIG_FILES = new String[]{"consumer.xml"};

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_FILES);

        context.start();

        ProviderService providerService = (ProviderService)context.getBean("providerService");

       String res= providerService.test();

        System.out.println("result= "+res);




    }
}
