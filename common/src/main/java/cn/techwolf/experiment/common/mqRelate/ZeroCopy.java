package cn.techwolf.experiment.common.mqRelate;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @author yl.xing
 * @create:2020-08-16
 * @describe
 **/
public class ZeroCopy {


    /**
     * 0 拷贝
     * @param fromFile
     * @param toFile
     * @throws IOException
     */
    public static void fileCpwithFileChannel(File fromFile, File toFile) throws IOException {

        long start = System.currentTimeMillis();


        FileChannel fileChannel = new FileInputStream(fromFile).getChannel();

        FileChannel toChannel = new FileOutputStream(toFile).getChannel();


        long size = fileChannel.size();

        fileChannel.transferTo(size -10>0?10:0, size,toChannel);


        System.out.println("零拷贝耗时="+(System.currentTimeMillis()-start));

    }

    static final int BUFFER_SIZE=1024;

    public static void bufferedCopy(File fromFile,File toFile) throws IOException {

        long start = System.currentTimeMillis();

        //解决了，fileinput 每次都要调用系统read, 提前缓存一部分数据到内存中
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fromFile));

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(toFile));

        byte[] buf = new byte[BUFFER_SIZE];

        int postion=-1;
        //read 如果非-1 返回正常的读取字节位置
        while((postion=bis.read(buf))!=-1){
            bos.write(buf,0,postion);
        }

        bos.close();
        bis.close();

        System.out.println("buffer inoutput end="+(System.currentTimeMillis()-start));

    }


    public static void main(String[] args) {



    }




}




