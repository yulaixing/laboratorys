package cn.techwolf.experiment.common.utils;

public class StringUtils {


    public static void main(String[] args) {

        String str="0123456789";

        char[] chars = str.toCharArray();

        StringBuilder sbStr = new StringBuilder("");

        for(int i=chars.length-1;i>=0;i--){
            sbStr.append(chars[i]);
        }

        System.out.println(sbStr.toString());


        int h=10;
        int l=100;

        for(int i=0;i<h;i++){

             int middle=l/2;
             int min=middle-i;
             int max=middle+i;
             for(int j=0;j<l;j++){
                 if(j>=min &&j<=max){
                     System.out.print("1");
                 }else{
                     System.out.print(" ");
                 }
             }
            System.out.print("\n");
        }



    }

}
