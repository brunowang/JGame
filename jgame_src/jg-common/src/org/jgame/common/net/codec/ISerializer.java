package org.jgame.common.net.codec;

public interface ISerializer {
	
	public <T> T read(Class<T> clazz, Object data);
	
	public Object read(byte[] data);
	
	public byte[] write(Object msg);
}
