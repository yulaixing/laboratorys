package cn.techwolf.experiment.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionTest {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        stack.push("1");
        stack.push("2");


        String peek = stack.peek();
        String peek2 = stack.peek();

        System.out.println(peek.equals(peek2));

//        stack.search()
        //whether equals
        stack.empty();


        //push ,pop, peek

        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();


//自旋锁
//        while(){
//            Thread.{yield();
//        }
//


        System.out.println("123");

        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(new File("")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


}
