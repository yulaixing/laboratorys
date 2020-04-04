package cn.techwolf.experiment.common.chatRoom;

import java.net.Socket;

public class Client {


    public static void main(String[] args) throws Exception{

        String host="127.0.0.1";

        int port=12345;

        Socket socket = new Socket(host, port);


        new ReadThread(socket.getInputStream()).start();

        new WriteThread(socket.getOutputStream()).start();




    }
}
