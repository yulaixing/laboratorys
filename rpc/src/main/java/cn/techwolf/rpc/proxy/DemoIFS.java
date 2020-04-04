package cn.techwolf.rpc.proxy;

public interface DemoIFS {

    public String test(String param1,String param2);


    public static void main(String[] args) throws Exception {


        Class<DemoIFS> aClass = (Class<DemoIFS>) Class.forName("cn.techwolf.rpc.proxy.DemoIFS");

        DemoIFS o = aClass.newInstance();







    }
}
