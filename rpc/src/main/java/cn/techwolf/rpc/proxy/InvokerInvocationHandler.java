package cn.techwolf.rpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvokerInvocationHandler implements InvocationHandler {

    private Invoker<?> invoker;


    public <T> InvokerInvocationHandler(Invoker<T> invoker) {

        this.invoker=invoker;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String methodName = method.getName();

        Class<?>[] parameterTypes = method.getParameterTypes();

//        method.getd




        return null;
    }
}
