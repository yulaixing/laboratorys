package cn.techwolf.experiment.common.utils;

import jdk.nashorn.internal.runtime.ECMAException;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class IOUtils {

    public static void test() throws Exception {

        String filePath = "/Users/admin/Downloads/4702716-62cfe9353808ec47.jpg";
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {

            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(filePath)));

            int length = bufferedInputStream.available();

            System.out.println(length);

            byte[] bytes = new byte[length];

            bufferedInputStream.read(bytes, 0, length - 1);


            String newPath = "/Users/admin/Downloads/picture";
            File file = new File(newPath);
            if (!file.exists()) {
                file.mkdir();
            }
            File newFile = new File(newPath + "/1.jpg");
            newFile.createNewFile();
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(newFile));
            bufferedOutputStream.write(bytes);

        } catch (Exception e) {

            System.out.println(e);

        } finally {
            bufferedOutputStream.flush();
            bufferedOutputStream.close();

            bufferedInputStream.close();
        }


    }


    //update write read

    public static void updateWriteAndRead(String inPath, String outPath) throws Exception {

        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inPath));

        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outPath));

        //1b ,1kb ,1Mb ==>1,1024,1024*1024
        byte[] buffer = new byte[102400];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }


        outputStream.flush();
        outputStream.close();

        inputStream.close();


    }


    public static void copyFileByChannel(File source, File des) throws IOException {
        try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
             FileChannel targetChannel = new FileOutputStream(des).getChannel();)
        {

            for (long count = sourceChannel.size(); count > 0; ) {
                long transferred = sourceChannel.transferTo(
                        sourceChannel.position(), count, targetChannel);
                count -= transferred;
            }
        }
    }


    public static void main(String[] args) throws Exception {

//        test();

//        SocketChannel open = SocketChannel.open();

//        open.write(Charset.defaultCharset().encode(""));


        updateWriteAndRead("/Users/admin/Downloads/4702716-62cfe9353808ec47.jpg", "/Users/admin/Downloads/updaterw.jpg");
    }
}
