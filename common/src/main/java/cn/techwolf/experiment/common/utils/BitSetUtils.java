package cn.techwolf.experiment.common.utils;

import java.util.BitSet;

public class BitSetUtils {

    public static void main(String[] args) {

//        int [] array = new int [] {1,2,3,22,0,3};
//
//        BitSet bitSet = new BitSet(6);
//
//        //将数组内容组bitmap
//        for(int i=0;i<array.length;i++)
//        {
//            bitSet.set(array[i], true);
//        }
//
//        System.out.println(bitSet.size());
//        System.out.println(bitSet.get(23));


        System.out.println('a'<'d');

        String a="abc";
        String b="acd";
        String c="dcd";
        String d="bcd";
        String e="ccd";

        String[] arrays={a,b,c,d,e};

        int length=a.toCharArray().length;
        for(int i=length-1;i>=0;i--){

            for(int j=1;j<arrays.length;j++){
                for(int k=0;k<arrays.length-j;k++){
                    if(arrays[k].toCharArray()[i]>arrays[k+1].toCharArray()[i]){

                        String temp=arrays[k];

                        arrays[k]=arrays[k+1];

                        arrays[k+1]=temp;
                    }
                }
            }

            for(String str:arrays){

                System.out.print(str+",");
            }

            System.out.println("");


        }








    }
}
