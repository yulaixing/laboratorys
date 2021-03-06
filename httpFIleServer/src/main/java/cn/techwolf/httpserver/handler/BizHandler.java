package cn.techwolf.httpserver.handler;

import cn.techwolf.httpserver.parser.RequestParser;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.Map;

public class BizHandler extends SimpleChannelInboundHandler<FullHttpRequest> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest request) throws Exception {

        String uri = request.uri();


        if(uri.contains("favicon.ico")){

            sendError(channelHandlerContext,HttpResponseStatus.FORBIDDEN);
            return;
        }

        RequestParser requestParser = new RequestParser(request);

        Map<String, String> parse = requestParser.parse();

        ByteBuf resContent = Unpooled.copiedBuffer("server ok", CharsetUtil.UTF_8);

        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, resContent);

        ChannelFuture channelFuture = channelHandlerContext.writeAndFlush(response);

        //异步
        channelFuture.addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                channelFuture.sync().removeListener(this).channel().close();
                System.out.println("response finish");
                channelFuture.channel().close();
            }

        });

    }

    private static void sendError(ChannelHandlerContext ctx,HttpResponseStatus status){

        FullHttpResponse response=new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,status,Unpooled.copiedBuffer(status.toString(),CharsetUtil.UTF_8));

        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset-UTF-8");

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

    }
}
