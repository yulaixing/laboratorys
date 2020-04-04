package cn.techwolf.experiment.common.chatRoom;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static void main(String[] args) throws Exception {
        int port = 12345;
        ServerSocket serverSocket = new ServerSocket(port);

        Socket accept = serverSocket.accept();

        OutputStream outputStream = accept.getOutputStream();


        Runnable write = new Runnable() {

            @Override
            public void run() {

                try {


                    int i = 0;
                    while (i < 10) {
                        outputStream.write("hello".getBytes("utf-8"));
                        Thread.sleep(3000);
                        i++;
                    }

                    outputStream.close();

                } catch (Exception e) {

                }
            }
        };

        new Thread(write).start();


        InputStream inputStream = accept.getInputStream();

        Runnable read = new Runnable() {

            @Override
            public void run() {

                try {

                    byte[] bytes = new byte[1024];
                    int length = 0;
                    while ((length=inputStream.read(bytes)) != -1) {
                        System.out.println(new String(bytes,0,length, "utf-8"));
                    }


                } catch (Exception e) {

                }
            }
        };

        new Thread(read).start();


    }


}