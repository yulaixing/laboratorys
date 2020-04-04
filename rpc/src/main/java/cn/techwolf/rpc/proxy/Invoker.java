package cn.techwolf.rpc.proxy;

import java.lang.reflect.Proxy;
import java.net.InterfaceAddress;

public class Invoker<T> {

    public Class<?>[] interfaces;


    public <T> T getProxyObject(Invoker<T> invoker,Class<?>[] interfaces){
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), interfaces, new InvokerInvocationHandler(invoker));

    }





}
