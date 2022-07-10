package com.walter.queue.producer.service;

import com.walter.queue.producer.response.Result;

public interface TransferService {
	Result sendMessage(String queue, String message);
}
