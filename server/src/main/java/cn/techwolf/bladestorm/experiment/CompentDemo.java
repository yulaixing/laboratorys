package cn.techwolf.bladestorm.experiment;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompentDemo  implements InitializingBean {


    @Value("${abc}")
    public String value;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("value is="+value);
    }
}
