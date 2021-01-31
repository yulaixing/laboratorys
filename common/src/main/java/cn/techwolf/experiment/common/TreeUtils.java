package cn.techwolf.experiment.common;

public class TreeUtils {

    public static void main(String[] args) {

        int[] a=new int[]{1,2,3,4,5,6,7,8,9};

        int index=0;
        iterationTree(a,index);

    }

    private static void iterationTree(int[] a, int index) {

        if(index+1>a.length){
            return;
        }
        System.out.println("树的节点="+a[index]);

        iterationTree(a,index*2+1);
        iterationTree(a,index*2+2);
    }


}



