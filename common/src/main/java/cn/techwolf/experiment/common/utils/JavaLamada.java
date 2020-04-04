package cn.techwolf.experiment.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yl.xing
 * @create:2019-12-05
 * @describe
 **/
public class JavaLamada {

    public static void main(String[] args) {

        List<Integer> list =new ArrayList<>();

        list.add(1);list.add(2);list.add(2);

        Set<Integer> collect = list.stream().collect(Collectors.toSet());

//        collect.forEach(e->{
//            System.out.println(e);
//        });

        String[] strArray = {"1", "2"};

//        Arrays.stream(strArray).

//        Arrays.stream(strArray)


//        System.out.println(2<<7);
//        System.out.println(2<<15);
//        System.out.println(2<<30);
//        System.out.println(2<<64);


    }




}
