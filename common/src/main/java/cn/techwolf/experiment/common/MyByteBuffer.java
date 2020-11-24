package cn.techwolf.experiment.common;

import sun.nio.ch.DirectBuffer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * @author yl.xing
 * @create:2020-06-17
 * @describe
 **/
public class MyByteBuffer {


    public static void main(String[] args) {

        String path = "/Users/admin/Downloads/1.txt";

//        File file = new File(path);

//        long length = file.length();


//        System.out.println("length=" + length);

        try {

//            FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();
//
//            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, length, 20);
//
//
//            byte b1 = mappedByteBuffer.get();
//
//            System.out.println(b1);



//            byte b = 121;
//
//            String string = new String(new byte[]{b});
//
//            System.out.println("string=" + string);
//
//            mappedByteBuffer.position(10);
//
//            int remaining = mappedByteBuffer.remaining();
//
//            System.out.println("remaining=" + remaining);
//
//            while (mappedByteBuffer.hasRemaining()) {
//                mappedByteBuffer.put(b);
//            }
//
//
//            mappedByteBuffer.force();



//            String string = new String(new byte[]{0});
//
//            System.out.println("string=" + string);
//
//
//            int index = (int) (2897093 % 13);
//
//
//            System.out.println(index);

            System.out.println(~2);


        } catch (Exception e) {

            System.out.println(e);

        }

    }


}
