package cn.techwolf.experiment.common.utils;


/**
 * @author yl.xing
 * @create:2019-11-19
 * @describe
 **/
public class HeapUtils {


    public void prepare(){
        int[] array = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3 };

        System.out.println("start=");
        for(int i:array){
            System.out.print(i+",");
        }
        //sort
        HeapSort(array);

        System.out.println("end=");
        for(int i:array){
            System.out.print(i+",");
        }




    }

    private void HeapSort(int[] array) {

        int floar=array.length/2;

        for(int i=floar;i>=0;i--){
            maxHeap(array,array.length,i);
        }

        for(int i=array.length-1;i>=1;i--){
            swapValue(array,i,0);
            maxHeap(array,i,0);
        }

    }

    private void maxHeap(int[] array, int length, int i) {


        int left=i*2+1;
        int right=i*2+2;

        int largest=i;


        if(left<length &&array[left]>array[largest]){
            largest=left;
        }



        if(right<length && array[right]>array[largest]){
            largest=right;
        }

        if(largest!=i){
            swapValue(array,i,largest);
            //交换完 最大已经变成最小
            maxHeap(array,length,largest);
        }
    }

    private void swapValue(int[] array, int i, int largest) {
        int temp=array[i];

        array[i]=array[largest];

        array[largest]=temp;


    }


}
