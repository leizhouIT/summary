package com.lei.zhou;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class NioServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        EventLoopGroup bossNioEventLoopGroup = new NioEventLoopGroup();
        EventLoopGroup workNioEventLoopGroup = new NioEventLoopGroup(8);
        serverBootstrap.group(bossNioEventLoopGroup,workNioEventLoopGroup)
                       .channel(NioServerSocketChannel.class)
                       .localAddress(new InetSocketAddress(6000))
                       .childHandler(new ChannelInitializer<SocketChannel>() {
                           @Override
                           protected void initChannel(SocketChannel ch) throws Exception {
                               ch.pipeline().addLast(new ServerHandler()).addLast(new ServerHandler2());
                           }
                       });
        try {
            ChannelFuture future = serverBootstrap.bind().sync();
            if (future.isSuccess()){
                System.out.println("服务器监听 :"+future.channel().localAddress());
            }else {
                bossNioEventLoopGroup.shutdownGracefully();
                workNioEventLoopGroup.shutdownGracefully();
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ServerHandler extends ChannelInboundHandlerAdapter{

    /**
     * 每次接收到消息的时候调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务器接收到消息："+byteBuf.toString(CharsetUtil.UTF_8));
        ctx.write(byteBuf);//将接收到的数据返回给发送者
    }

    /**
     * 通知handler当前消息是处理的最后一条消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("刷新");
        //刷新所有数据到远程节点，然后关闭通道
        ctx.writeAndFlush("server hello world".getBytes()).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 读取数据时出现异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //出现异常关闭通道
        ctx.close();
    }
}
class ServerHandler2 extends ChannelInboundHandlerAdapter{

    /**
     * 每次接收到消息的时候调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务器接收到消息2："+byteBuf.toString(CharsetUtil.UTF_8));
        ctx.write(byteBuf);//将接收到的数据返回给发送者
    }

    /**
     * 通知handler当前消息是处理的最后一条消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("刷新");
        //刷新所有数据到远程节点，然后关闭通道
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 读取数据时出现异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //出现异常关闭通道
        ctx.close();
    }
}
