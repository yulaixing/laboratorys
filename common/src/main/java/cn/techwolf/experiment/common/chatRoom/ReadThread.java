package cn.techwolf.experiment.common.chatRoom;

import java.io.InputStream;

public class ReadThread extends Thread {

    private InputStream inputStream;

    public ReadThread(InputStream inputStream){
        this.inputStream=inputStream;
    }

    @Override
    public void run()  {

        try {
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length=inputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes,0,length, "utf-8"));
            }


        }catch (Exception e){

        }

//        super.run();

        System.out.println("eee");
    }
}
