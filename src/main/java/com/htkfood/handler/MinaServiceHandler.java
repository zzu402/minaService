package com.htkfood.handler;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class MinaServiceHandler extends IoHandlerAdapter{
	  @Override
	    public void exceptionCaught(IoSession session, Throwable cause ) throws Exception
	    {
	        cause.printStackTrace();
	    }

	    @Override
	    public void messageReceived( IoSession session, Object message ) throws Exception
	    {
	    	System.out.println("服务器接收到数据："+ message);
	        String content = message.toString();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        String datetime = sdf.format(new Date());
	        // 拿到所有的客户端Session
	        Collection<IoSession> sessions = session.getService().getManagedSessions().values();
	        // 向所有客户端发送数据
	        for (IoSession sess : sessions) {
	            sess.write(datetime + "\t" + content);
	        }
	     
	    }
	    @Override
	    public void messageSent(IoSession session, Object message) throws Exception {
	    	System.out.println("服务器发送消息："+message);
	    }

	  
	    @Override
	    public void sessionCreated(IoSession session) throws Exception {
	    	System.out.println("创建一个新连接:"+ session.getRemoteAddress());
	        //session.write("welcome to the chat room !");
	    }
	 
	    @Override
	    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
	    	System.out.println("当前连接"+ session.getRemoteAddress()+"处于空闲状态："+ status);
	    }
	 
	    @Override
	    public void sessionOpened(IoSession session) throws Exception {
	    	System.out.println("打开一个session"+session.getId()+"#"+session.getBothIdleCount());
	    }
}
