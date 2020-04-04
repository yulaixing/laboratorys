package cn.techwolf.experiment.common.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Agent {

    public static void main(String[] args) {

//        new InputStreamReader()

//        new InputStream()
//        new InputStreamReader();

//        System.out.println("余数=" + 4 % 3);
//
//        int[] array = new int[]{4, 5, 6, 7, 13};
//
//        int k = 10;
//
//        int min = 0;
//        int max = array.length - 1;
//
//        if (array[max] < k || array[min] > k) {
//            System.out.println("查无此人");
//            return;
//        }
//
//        boolean isHit = false;
//
//
//        while (min <= max) {
//
//            int middle = (min + max) / 2;
//
//            if (array[middle] == k) {
//                System.out.println(middle);
//                isHit = true;
//                break;
//            } else if (array[middle] > k) {
//                max = middle - 1;
//            } else if (array[middle] < k) {
//                min = middle + 1;
//            }
//        }
//
//        System.out.println(array[min]);
//
//
//        if (!isHit) {
//            System.out.println("查无此人");
//        }


        new Agent().aSort();

    }


    public void aSort() {

        int[] array = new int[]{5, 4, 3, 2, 1,8};

        int K = sort2(array, 0, array.length - 1, 9);

        System.out.println(K);



        for (int i : array) {
            System.out.print(i + ",");
        }


    }

    private int sort2(int[] array, int start, int end,int K) {

        if(start>end){
            return -1;
        }

        int middle=getMiddleIndex(array,start,end);
        if(array[middle]==K){
            return middle;
        }

        if(array[middle]>K){
           return sort2(array,0,middle-1,K);
        }else{
           return sort2(array,middle+1,end,K);
        }

    }

    private int getMiddleIndex(int[] array, int start, int end) {

        int value=array[start];

        while(start<end){

            while (start<=end &&value<array[end]){
                end--;
            }

            if(start<end){
                array[start++]=array[end--];
            }

            while(start<=end && value>array[start]){

                start++;
            }

            if(start<end){
                array[end--]=array[start++];
            }
        }

        array[start]=value;

        return start;


    }
}
