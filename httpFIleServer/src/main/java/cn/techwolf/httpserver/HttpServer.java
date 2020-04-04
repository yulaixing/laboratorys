package cn.techwolf.httpserver;

import cn.techwolf.httpserver.handler.BizHandler;
import cn.techwolf.httpserver.ssl.SSLContextFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import javax.net.ssl.SSLEngine;
import java.lang.reflect.Proxy;

public class HttpServer {

    private static final String default_url="/netty";

    public static boolean isSSL;

    public static void main(String[] args) {
        int port=8080;
        new HttpServer().run(port,default_url);

//        Proxy.newProxyInstance()

    }

//    default event executor group

    private void run(int port, String url) {



        EventLoopGroup bossGroup = new NioEventLoopGroup(1);

        EventLoopGroup workGroup = new NioEventLoopGroup(2);

        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();

            //bossgroup-->manage connect ,workgroup -->process event
            //NioServerSocketChannel.class
            //SocketChannel.class
            serverBootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {

//                    SSLEngine sslEngine = SSLContextFactory.getSslContext().createSSLEngine();
//                    sslEngine.setUseClientMode(false);
//                    socketChannel.pipeline().addLast("ssl",new SslHandler(sslEngine));
                    socketChannel.pipeline().addLast("httpCodec",new HttpServerCodec());
//                    socketChannel.pipeline().addLast("httpDecoder",new HttpRequestDecoder());
                    socketChannel.pipeline().addLast("httpAggregator",new HttpObjectAggregator(65536));
//                    socketChannel.pipeline().addLast("httpEncoder",new HttpResponseEncoder());
                    socketChannel.pipeline().addLast("httpChunked",new ChunkedWriteHandler());
                    socketChannel.pipeline().addLast("bizHandler",new BizHandler());

                }
            });

            ChannelFuture sync = serverBootstrap.bind("127.0.0.1", port).sync();


//            基本上Netty的server：ChannelFuture channelFuture = serverBootstrap.bind(port).syncUninterruptibly();//绑定端口，开启监听
//
//            后面都会加上这句channelFuture.channel().closeFuture().syncUninterruptibly();那这行代码到底有什么作用呢？具体没有深入源码，只随笔记录一下心得（个人理解，如有错误还请指正）：
//
//            作用：服务端管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程；
//
//          主线程执行到这里就 wait 子线程结束，子线程才是真正监听和接受请求的，子线程就是Netty启动的监听端口的线程；
//
//          即closeFuture()是开启了一个channel的监听器，负责监听channel是否关闭的状态，如果未来监听到channel关闭了，子线程才会释放，syncUninterruptibly()让主线程同步等待子线程结果。
//
//            补充：.channel.close()才是主动关闭通道的方法。

            System.out.println("server has start");

            sync.channel().closeFuture().sync();



        }catch (Exception e){



        } finally{
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }

}
