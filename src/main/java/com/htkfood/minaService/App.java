package com.htkfood.minaService;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.htkfood.handler.MinaServiceHandler;

public class App 
{
	private static final int PORT=2730;
    public static void main( String[] args )
    {
    	try {
        // code will go here next
        IoAcceptor acceptor = new NioSocketAcceptor();//监听传入连接的对象
        acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );//记录所有的信息，比如创建session(会话)，接收消息，发送消息，关闭会话等
        //这里你可以自己实现ProtocolCodecFactory接口,例如MyCodeFactory就是一个实现类
        acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new PrefixedStringCodecFactory( Charset.forName( "UTF-8" ))));//ProtocolCodecFilter(协议编解码过滤器).这个过滤器用来转换二进制或协议的专用数据到消息对象中， 反之亦然。
                                                                                                                                     // 我们这里使用一个已经存在的TextLine工厂，因为我们这里只处理一些文字消息（你不需要再去写编解码部分）。
        acceptor.setHandler(  new MinaServiceHandler());//创建一个handler来实时处理客户端的连接和请求，这个handler 类必须实现 IoHandler这个接口。
        acceptor.getSessionConfig().setReadBufferSize( 2048 );
        acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 10 );
        
		acceptor.bind( new InetSocketAddress(PORT) );
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
}
