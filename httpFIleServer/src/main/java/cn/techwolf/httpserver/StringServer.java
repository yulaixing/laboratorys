package cn.techwolf.httpserver;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.net.Socket;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author yl.xing
 * @create:2020-05-02
 * @describe
 **/
public class StringServer {


    public static void main(String[] args) {

        int port = 8180;

        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup(2);

        bootstrap.group(boss, work).channel(NioServerSocketChannel.class).childHandler(

                new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {

                        ch.pipeline().addLast("stringcoder", new MyStringDecoder());

                        ch.pipeline().addLast("stringhandler",new StringHandler());
                    }
                }
        );
        try {
        ChannelFuture sync = bootstrap.bind("127.0.0.1", port).sync();
        System.out.println("server has start");

            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @ChannelHandler.Sharable
    static class StringHandler extends SimpleChannelInboundHandler<String> {


        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {

            ctx.fireChannelRead(s);
            System.out.println(s);
            ctx.close();
        }


        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("注册");
            super.channelRegistered(ctx);
        }
    }


    static class MyStringDecoder extends MessageToMessageDecoder<ByteBuf> {


        private final Charset charset;

        public MyStringDecoder() {
            this(Charset.defaultCharset());
        }

        public MyStringDecoder(Charset charset) {
            if (charset == null) {
                throw new NullPointerException("charset");
            } else {
                this.charset = charset;
            }
        }

        protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {



            out.add(msg.toString(this.charset));
            out.add(msg.toString(this.charset));
        }



    }


}
