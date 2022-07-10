package com.walter.queue.producer.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.walter.queue.producer.response.Result;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SqsTransferService implements TransferService {

	@Value("${sqs.queue.url:}")
	private String queueUrl;
	@Value("${sqs.queue.message.group.id:}")
	private String messageGroupId;

	private final AmazonSQS amazonSQS;
	private final QueueMessagingTemplate queueMessagingTemplate;

	public SqsTransferService(AmazonSQS amazonSQS, QueueMessagingTemplate queueMessagingTemplate) {
		this.amazonSQS = amazonSQS;
		this.queueMessagingTemplate = queueMessagingTemplate;
	}

	@Override
	public Result sendMessage(String queue, String message) {
		if (StringUtils.contains(queue.toLowerCase(), ".fifo")) {
			return sendMessageToFifoQueue(queue, message);
		}
		return simpleSendMessage(queue, message);
	}

	private Result simpleSendMessage(String queue, String message) {
		final Message<String> sqsMessage = MessageBuilder.withPayload(message).build();
		queueMessagingTemplate.send(queueUrl + queue, sqsMessage);
		return Result.OK;
	}

	private Result sendMessageToFifoQueue(String queue, String message) {
		final SendMessageRequest request = new SendMessageRequest()
				.withQueueUrl(queueUrl + queue)
				.withMessageGroupId(messageGroupId)
				.withMessageDeduplicationId(UUID.randomUUID().toString())
				.withMessageBody(message);
		final SendMessageResult result = amazonSQS.sendMessage(request);
		if (StringUtils.isEmpty(result.getMessageId())) {
			return Result.FAILURE;
		}
		return Result.OK;
	}
}
