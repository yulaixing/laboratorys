package cn.techwolf.httpserver.handler;

import cn.techwolf.httpserver.parser.RequestParser;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

import java.util.Map;

/**
 * @author yl.xing
 * @create:2020-04-25
 * @describe
 **/
public class Biz2Handler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest request) throws Exception {


        Channel channel = channelHandlerContext.channel();

        System.out.println("key=="+channel.attr(Constants.KEY).get());



        RequestParser requestParser = new RequestParser(request);

        Map<String, String> parse = requestParser.parse();

        ByteBuf resContent = Unpooled.copiedBuffer("server ok", CharsetUtil.UTF_8);

        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, resContent);

        ChannelFuture channelFuture = channelHandlerContext.writeAndFlush(response);

        //异步
        channelFuture.addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                System.out.println("response finish");
                channelFuture.channel().close();
            }

        });
    }
}
