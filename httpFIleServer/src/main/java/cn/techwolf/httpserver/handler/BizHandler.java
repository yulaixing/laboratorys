package cn.techwolf.httpserver.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.Iterator;
import java.util.Map;

public class BizHandler extends SimpleChannelInboundHandler<FullHttpRequest> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest request) throws Exception {

        System.out.println("du");


        ChannelPipeline pipeline = channelHandlerContext.pipeline();

        Channel channel = channelHandlerContext.channel();

        channel.attr(Constants.KEY).set(123L);

        Iterator<Map.Entry<String, ChannelHandler>> iterator = pipeline.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, ChannelHandler> next = iterator.next();

            System.out.println("key=" + next.getKey() + " value=" + next.getValue());

        }


        String uri = request.uri();


        if (uri.contains("favicon.ico")) {

            sendError(channelHandlerContext, HttpResponseStatus.FORBIDDEN);
            return;
        }

        channelHandlerContext.fireChannelRead(request.retain());



    }

    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer(status.toString(), CharsetUtil.UTF_8));

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset-UTF-8");

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

        ChannelId id = ctx.channel().id();
        System.out.println("通道id"+id.asLongText());

        System.out.println("注册");
//        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("激活");
//        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
