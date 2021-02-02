package cn.techwolf.httpserver.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author yl.xing
 * @create:2020-05-02
 * @describe
 **/
public class BizHandler3 extends SimpleChannelInboundHandler<FullHttpRequest> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {

        ctx.fireChannelRead(request.retain());

    }




}
