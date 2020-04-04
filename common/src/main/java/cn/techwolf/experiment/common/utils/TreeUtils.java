package cn.techwolf.experiment.common.utils;

public class TreeUtils<T> {


    private T t ;


    public T addString(T t){

        this.t=t;
        return t;
    }


    public <K> K getK(K k){

        if(k instanceof String){
            System.out.println("ok");
        }

        if(k instanceof  TreeUtils){

        }

        if(k instanceof  Integer){
            System.out.println("integer");
        }


        return k;
    }

    public static void main(String[] args) {

        TreeUtils<String> stringTreeUtils = new TreeUtils<>();

//        stringTreeUtils.addString()

        stringTreeUtils.getK(123L);



    }



}
