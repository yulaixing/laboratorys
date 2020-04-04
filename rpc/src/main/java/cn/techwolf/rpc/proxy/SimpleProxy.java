package cn.techwolf.rpc.proxy;

import jdk.internal.dynalink.linker.MethodHandleTransformer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SimpleProxy {

    interface Node{

        public String getName(String str);

    }


    public static void main(String[] args) throws Exception{


        Object proxy = Proxy.newProxyInstance(SimpleProxy.class.getClassLoader(), new Class[]{Node.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


                for(Object obj:args){
                    System.out.println(obj);
                }

                String name = method.getName();

                if (name.equals("getName")) {

                    return "xingyulai";
                } else {
                    return "别闹";
                }
//                return null;
            }
        });


        Method[] methods = proxy.getClass().getMethods();
        for(Method method:methods){
            System.out.println("methodName= "+method.getName());
        }



        Method method = proxy.getClass().getMethod("getName", String.class);

        Object result= method.invoke(proxy, "w");

        System.out.println(result);





    }






}
