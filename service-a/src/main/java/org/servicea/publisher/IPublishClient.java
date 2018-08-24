package org.servicea.publisher;

import org.servicea.constent.InputOutputConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface IPublishClient {
	@Output
	MessageChannel output();
	
	
	@Output(InputOutputConst.OUTPUT_TEST1)
	MessageChannel output1();
}
