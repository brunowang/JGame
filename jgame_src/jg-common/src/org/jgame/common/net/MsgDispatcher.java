package org.jgame.common.net;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jgame.common.net.msg.Message;
import org.jgame.common.net.msg.MsgDelegateMethod;
import org.jgame.common.net.msg.MsgFactory;
import org.jgame.common.net.msg.MsgHandler;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class MsgDispatcher {
	private static MsgDispatcher instance = new MsgDispatcher();
	public static MsgDispatcher getInstance() {
		return instance;
	}
	
	ExecutorService  executor = Executors.newSingleThreadExecutor();
	MsgFactory factory = new MsgFactory();
	//创建bufferSize ,也就是RingBuffer大小，必须是2的N次方
	int ringBufferSize = 1024 * 1024;
	
	Disruptor<Message> disruptor;
	MsgHandler msgHandler;
	
	@SuppressWarnings("unchecked")
	public void init() {
		disruptor = new Disruptor<Message>(factory, ringBufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());
		// 连接消费事件方法
		msgHandler = new MsgHandler();
		disruptor.handleEventsWith(msgHandler);
		// 启动
		disruptor.start();
	}
	
	public void onReceiveMsg(Message msg) {
		//Disruptor 的事件发布过程是一个两阶段提交的过程：
		//发布事件
		RingBuffer<Message> ringBuffer = disruptor.getRingBuffer();
		long sequence = ringBuffer.next();//请求下一个事件序号；
	    
		try {
			Message event = ringBuffer.get(sequence);//获取该序号对应的事件对象；
			event.copyFrom(msg);
		} finally{
		    ringBuffer.publish(sequence);//发布事件；
		}
	}
	
	public void onRegister(int msgId, MsgDelegateMethod method) {
		msgHandler.onRegister(msgId, method);
	}
	
	public void onDestroy() {
		disruptor.shutdown();//关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
		executor.shutdown();//关闭 disruptor 使用的线程池；如果需要的话，必须手动关闭， disruptor 在 shutdown 时不会自动关闭；
	}
}
