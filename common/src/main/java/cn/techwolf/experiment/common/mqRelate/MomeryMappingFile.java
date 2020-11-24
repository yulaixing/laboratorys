package cn.techwolf.experiment.common.mqRelate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author yl.xing
 * @create:2020-08-17
 * @describe 内存与文件映射
 **/
public class MomeryMappingFile {

    final static int BUFFER_SIZE = 8192;

    public static void fileReadWithMmap(File file) {

        long begin = System.currentTimeMillis();

        byte[] b = new byte[BUFFER_SIZE];

        int len = (int) file.length();

        MappedByteBuffer buff;

        try (FileChannel channel = new FileInputStream(file).getChannel()) {

            buff = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

            buff.put("我爱中国".getBytes(Charset.defaultCharset()));

            buff.flip();

            for (int offset = 0; offset < len; offset += BUFFER_SIZE) {
                if (len - offset > BUFFER_SIZE) {
                    buff.get(b);
                } else {
                    byte[] temp = new byte[len - offset];
                    buff.get(temp);

                    System.out.println(new String(temp, Charset.defaultCharset()));

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }





    public static void main(String[] args) {

        //

//        String filePath="/data/logs/trace/trace.log";
//
//        File file = new File(filePath);
//
//        fileReadWithMmap(file);


        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile("/data/logs/trace/trace.log", "rw");
            FileChannel fileChannel = raf.getChannel();

            String a = "我要写入文件2312311";
            MappedByteBuffer mbb = fileChannel.map(FileChannel.MapMode.READ_WRITE, fileChannel.size(), a.getBytes(Charset.defaultCharset()).length);
            mbb.put(a.getBytes());

            raf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
