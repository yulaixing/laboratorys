package cn.techwolf.experiment.common.chatRoom;

import java.io.IOException;
import java.io.OutputStream;

public class WriteThread extends Thread{

    private OutputStream outputStream;

    public WriteThread(OutputStream outputStream) {
        this.outputStream=outputStream;
    }


    @Override
    public void run() {


        try{

            int i=0;

            while(i<10){
                outputStream.write("client".getBytes("utf-8"));
                i++;
                Thread.sleep(3000);
            }

        }catch (Exception e){

        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
