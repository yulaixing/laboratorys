package cn.techwolf.httpserver.handler;

import cn.techwolf.httpserver.parser.RequestParser;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;

import java.util.Iterator;
import java.util.Map;

public class BizHandler extends SimpleChannelInboundHandler<FullHttpRequest> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest request) throws Exception {

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
}
