package cn.techwolf.experiment.common.utils;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UdpUtils {

    class ReceiveThread extends Thread {

        private final int max_receive_buffer = 1024*1024;

        private DatagramSocket server;

        private DatagramPacket packet;

        byte[] buffer = new byte[max_receive_buffer];

        ReceiveThread(DatagramSocket server) {

            this.server = server;
            packet = new DatagramPacket(buffer, buffer.length);
        }


        @Override
        public void run() {
            FileOutputStream output = null;
            try {

                String name = new Random().nextInt(100) + ".txt";
                System.out.println("filename "+name);
                File dest = new File("/Users/admin/udpFile/" + name);  //要接收的文件存放路径
                output = new FileOutputStream(dest);
                int len = 0;   //数据长度

                //接受数据死循环
                while (len == 0) {  //无数据则开始循环接收数据
                    //接收数据包
                    server.receive(packet);
                    len = packet.getLength();
                    if (len > 0) {

                        System.out.println("length="+len);
                        //指定接收到数据的长度，可使程序正常接收数据
                        output.write(buffer, 0, len);
                        output.flush();
                        len = 0;//循环接收
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("jieshujieshu");
        }
    }


    class Send_Thread extends Thread {

        //发送的socket端
        private DatagramSocket sender = null;
        //待发送的目标地址，InetSocketAddress用来储存IP套接字地址
        private InetSocketAddress address = null;

        //构造函数
        public Send_Thread(DatagramSocket sender, InetSocketAddress address) {
            this.sender = sender;
            this.address = address;
        }

        public void run() {

            InputStream inputstream = null;
            try {
                File source = new File("/Users/admin/Desktop/working/netty.txt");  //要传输的文件路径
                inputstream = new FileInputStream(source);
                byte[] data = new byte[1024*1024];
                //创建UDP数据报
                int length = 0;
                while ((length = inputstream.read(data)) >0) {
                    DatagramPacket pack = new DatagramPacket(data, length, address);
                    sender.send(pack);
                    TimeUnit.MICROSECONDS.sleep(1); //限制传输速度
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }finally {
                try {
                    inputstream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("fasongjieshu");
        }
    }


    public class Create {   //创建服务器和客户端的类与方法
        private int DEST_PORT = 0;
        private int SEND_PORT = 0;
        private int RECE_PORT = 0;
        private String IP = null;

        //构造函数储存IP和端口
        public Create(int DEST_PORT, int SEND_PORT, int RECE_PORT, String IP) {
            this.DEST_PORT = DEST_PORT;
            this.SEND_PORT = SEND_PORT;
            this.RECE_PORT = RECE_PORT;
            this.IP = IP;
        }

        public void run() {
            try {
                Send_Thread send_thread = null;
                ReceiveThread receive_thread = null;
                InetSocketAddress address = null;
                //创建待接受数据包的目的机的端口号和IP地址
                address = new InetSocketAddress(IP, DEST_PORT);
                //创建发送的Socket端
                DatagramSocket sendsocket = new DatagramSocket(SEND_PORT);
                //创建接受的Socket端
                DatagramSocket recesocket = new DatagramSocket(RECE_PORT);
                //发送线程建立
                send_thread = new Send_Thread(sendsocket, address);
                //接受线程的建立
                receive_thread = new ReceiveThread(recesocket);
                send_thread.start();  //开启线程
                receive_thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createClient() {

        int DEST_PORT = 6666;
        int SEND_PORT = 10001;
        int RECE_PORT = 8888;
        String IP = "127.0.0.1";
        Create client = new Create(DEST_PORT, SEND_PORT, RECE_PORT, IP);
        client.run();

    }


    public void createServer() {

        final int DEST_PORT = 8888;
        final int SEND_PORT = 10000;
        final int RECE_PORT = 6666;
        final String IP = "127.0.0.1";

        Create client = new Create(DEST_PORT, SEND_PORT, RECE_PORT, IP);
        client.run();

    }


    public static void main(String[] args) {

        new UdpUtils().createClient();
        new UdpUtils().createServer();




//        String name = new Random().nextInt(100) + ".txt";

//        System.out.println(name);

    }
}
