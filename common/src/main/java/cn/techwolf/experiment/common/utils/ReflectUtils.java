package cn.techwolf.experiment.common.utils;

import java.lang.reflect.Method;

public class ReflectUtils {


    public static void main(String[] args) throws Exception {

        Class<?> target = Class.forName("cn.techwolf.experiment.common.utils.Target");
        Class<?> target2 = Class.forName("cn.techwolf.experiment.common.utils.Target");

        System.out.println(target==target2);


        Method declaredMethod = target.getDeclaredMethod("getTargetName", null);

        Class<?> returnType = declaredMethod.getReturnType();

        System.out.println(returnType.getName());





//        declaredMethod.invoke(目标类，参数)













    }

}
