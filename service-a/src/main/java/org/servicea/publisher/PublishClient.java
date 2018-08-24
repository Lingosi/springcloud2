package org.servicea.publisher;

import org.servicea.constent.InputOutputConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@EnableBinding(IPublishClient.class)
@Service
public class PublishClient {
	@Autowired
	private MessageChannel output;
	
	
	@Autowired
	@Output(InputOutputConst.OUTPUT_TEST1)
	private MessageChannel output1;
	
	public void send(Object obj){
		output.send(MessageBuilder.withPayload(obj).build());
	}
	
	public void send1(Object obj){
		output1.send(MessageBuilder.withPayload(obj).build());
	}
}
