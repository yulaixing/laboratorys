package cn.techwolf.experiment.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtils {

    public static void main(String[] args) {

//        byte c='C';
//        int j=(int)c;
//        System.out.println(j);
//        int i = c & 0xFF;
//        System.out.println(i);

        String str="123";

        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());

            System.out.println("encodestr="+encodestr);

            System.out.println("encodestr="+encodestr.length());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
