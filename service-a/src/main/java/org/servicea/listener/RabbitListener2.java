package org.servicea.listener;

import org.servicea.constent.InputOutputConst;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;

@EnableBinding(Processor.class)
public class RabbitListener2 {
	/*@StreamListener(InputOutputConst.INPUT_TEST1)
	public void recieve(Message<String> msgReq){
		String msg = msgReq.getPayload();
		System.out.println("接受到消息了：" + msg);
	}*/
}
