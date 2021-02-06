package cn.techwolf.dubbo.relate.Provider.instance;

public class ProviderServiceImpl implements ProviderService {
    @Override
    public String test() {
        System.out.println("hello world");

        return "hello world";
    }
}
