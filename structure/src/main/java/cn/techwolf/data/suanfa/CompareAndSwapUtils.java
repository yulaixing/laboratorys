package cn.techwolf.data.suanfa;

/**
 * 比较交换
 */
public class CompareAndSwapUtils {

    /*初始化索引*/
    public static void main(String[] args) {

        int[] initArray = new int[]{1, 5, 5, 8, 3, 0, 12, 4};


        //

        quickSort(initArray, 0, initArray.length - 1);

        for (int i : initArray) {
            System.out.print(i + ",");
        }

    }

    public static void quickSort(int[] initArray, int start, int end) {

        if (start >= end) {
            return;
        }

        //获取分片索引
        int p = getOtherPartion(initArray, start, end);

        System.out.println(p);

        for (int i : initArray) {
            System.out.print(i + ",");

        }
        System.out.println();

        quickSort(initArray, start, p - 1);

        quickSort(initArray, p + 1, end);


    }


    //有一个指针在查找 交换，两个指针是相互制约，相互合作的。比较 交换

    private static int getOtherPartion(int[] arr, int start, int end) {

        int i=start;
        int j=start;
        int value=arr[end];

        for(;j<end-1;j++){

            if(arr[j]>value){

            }
            if(arr[j]<value){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }

        }

        int temp = arr[i];
        arr[i] = arr[end];
        arr[end] = temp;





        return i;



    }

        private static int getPartion(int[] arr, int start, int end) {

        //i是开始索引
        int i = start;
        //j是结束索引
        int j = end;
        //基准值
        int value = arr[i];
        //当i<j时可以进行交换
        //j最后的位置就是小于基准值的位置，所以要从右向左比较
        while (i < j) {

            //小于基准值
            while (i < j && arr[j] >= value) {
                j--;
            }

            //大于基准值
            while (i < j && arr[i] <= value) {
                i++;
            }

            if (i < j) {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        //
        int temp = arr[start];
        arr[start] = arr[i];
        arr[i] = temp;

        return i;


//        //基准值
//        int value=arr[end];
//
//        //j指针从左到右划
//        for(j=0;j<end;j++){
//
//            //i是记录比基准值小的
//            while(arr[i]<value){
//                i++;
//            }
//            //卡在大于基准值
//            if(i==end){
//                return i;
//            }
//
//            if(arr[j]>value){
//
//                int temp=
//                arr[i]=
//
//            }
//
//        }

    }


}
