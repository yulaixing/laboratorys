package cn.techwolf.experiment.common.utils;

public class Outer {

    public class Inner{


    }

    public static void main(String[] args) {

        Outer outer = new Outer();

//        outer.Inner inner1 = new outer.Inner();

        Inner inner = outer.new Inner();



    }


}



