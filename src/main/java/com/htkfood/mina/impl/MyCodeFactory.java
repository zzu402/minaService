package com.htkfood.mina.impl;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;

public class MyCodeFactory implements ProtocolCodecFactory {
	//Filter1：编解码器，实现ProtocolCodecFactory解码工厂
    private final TextLineEncoder encoder;
    private final TextLineDecoder decoder;
	 public MyCodeFactory() {
	        this(Charset.forName("utf-8"));
	    }
	    public MyCodeFactory(Charset charset) {
	        encoder = new TextLineEncoder(charset, LineDelimiter.UNIX);
	        decoder = new TextLineDecoder(charset, LineDelimiter.AUTO);
	    }
	    public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
	        // TODO Auto-generated method stub
	        return decoder;
	    }
	    public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
	        // TODO Auto-generated method stub
	        return encoder;
	    }
	    public int getEncoderMaxLineLength() {
	        return encoder.getMaxLineLength();
	    }
	    public void setEncoderMaxLineLength(int maxLineLength) {
	        encoder.setMaxLineLength(maxLineLength);
	    }
	    public int getDecoderMaxLineLength() {
	        return decoder.getMaxLineLength();
	    }
	    public void setDecoderMaxLineLength(int maxLineLength) {
	        decoder.setMaxLineLength(maxLineLength);
	    }
}
