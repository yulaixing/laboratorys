package cn.techwolf.experiment.common.utils;


import java.io.Serializable;
public class UserGenerial<P> implements Serializable {

    private P p;

    public P getP() {

        System.out.println(p);
        return p;
    }

    public void setP(P p) {

        this.p = p;
    }


    public static <T> T[] getArray(T... arg) {
        return arg;
    }


    public <T> void sys(T... arg) {

        System.out.println(arg.toString());

    }

    public static void main(String[] args) {
//
//        Integer[] array = getArray(1, 3, 5, 6);
//
//        System.out.println(array);
//
//        UserGenerial<Integer> integerUserGenerial = new UserGenerial<>();
//
//        integerUserGenerial.setP(11);
//
//        integerUserGenerial.getP();
//
//        integerUserGenerial.sys("1", "3", "4");
//
        int[] arrays=new int[]{4,3,2,1,6,2,1,2,3,4};

        splitArray(arrays,0,9);

        for(int a:arrays){

            System.out.print(a+",");
        }



    }


    public static void splitArray(int[] arrays, int start, int end) {

        if (start >= end) {
            return;
        }
        int middle = (start + end) / 2;

        splitArray(arrays, start, middle);

        splitArray(arrays, middle + 1, end);

        againSort(arrays, start, middle, end);


    }

    private static void againSort(int[] arrays, int start, int middle, int end) {
        int index = start;

        int newArrayLength = end - start + 1;
        int[] newArray = new int[newArrayLength];
        int i = 0;
        int a1 = start;
        int b1 = middle + 1;
        while (a1 <= middle && b1 <= end) {

            while (a1 <= middle && arrays[a1] <= arrays[b1]) {

                newArray[i++] = arrays[a1++];
            }


            while (b1 <= end && arrays[a1] > arrays[b1]) {

                newArray[i++] = arrays[b1++];
            }
        }

        while (a1 <= middle) {
            newArray[i++] = arrays[a1++];
        }

        while (b1 <= end) {
            newArray[i++] = arrays[b1++];
        }

        for (int newArrayStart = 0; newArrayStart < newArray.length; newArrayStart++) {
            arrays[index + newArrayStart] = newArray[newArrayStart];
        }


    }


}
