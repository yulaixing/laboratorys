package cn.techwolf.data;

import java.util.ArrayList;
import java.util.List;

public class TopK {


    public List<Integer> result=new ArrayList<>();


    public int partition(int[] a,int low,int high){

        int privotKey=a[low];

        while(low<high){

            while(low<high && a[high] >=privotKey){
                high--;
            }

            int temp=a[high];
            a[high]=a[low];
            a[low]=temp;

            while(low<high && a[low]<=privotKey){
                low++;
            }

            temp=a[low];

            a[low]=a[high];

            a[high]=temp;
        }

        return low;

    }


    public void quickSort(int[] a,int low,int high){

        if(low<high){
            int privotLoc=partition(a,low,high);
            quickSort(a,low,privotLoc-1);
            quickSort(a,privotLoc+1,high);
        }
    }


    public int quickSelect(int[] arr,int k,int left,int right){

        int index=partition(arr,left,right);

        if(right-index+1>k){
            return quickSelect(arr,k,index+1,right);
        }else if(right-index +1==k){

            for(int i=index;i<=arr.length-1;i++){
                result.add(arr[i]);
            }
            return arr[index];
        }else {
            return quickSelect(arr,k-right+index-1,left,index-1);
        }


    }


    public int findKthLargest(int[] nums,int k){

        return quickSelect(nums,k,0,nums.length-1);
    }


    public static void main(String[] args){
        int [] a ={47,25,16,24,15,23,1548,255,445,47,54,0,99999,2312213,1223123123,2};
        TopK quickSort = new TopK();
        int num = quickSort.findKthLargest(a,3);
        for (Integer i:quickSort.result) {
            System.out.println(i);
        }
    }





}
