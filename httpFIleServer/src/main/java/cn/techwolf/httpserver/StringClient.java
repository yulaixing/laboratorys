package cn.techwolf.httpserver;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author yl.xing
 * @create:2020-05-02
 * @describe
 **/
public class StringClient {

    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap();

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(

                new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new StringEncoder());
                    }
                }
        );

        Channel channel = bootstrap.connect("127.0.0.1", 8180).channel();

        try {
            Thread.sleep(4000);

            channel.writeAndFlush("ojbk").addListener(ChannelFutureListener.CLOSE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Haha");

        eventLoopGroup.shutdownGracefully();

        channel.writeAndFlush("ojbk").addListener(ChannelFutureListener.CLOSE);

    }


}
