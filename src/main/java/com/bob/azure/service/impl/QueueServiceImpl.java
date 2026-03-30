package com.bob.azure.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azure.core.http.rest.PagedIterable;
import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.models.QueueMessageItem;
import com.azure.storage.queue.models.SendMessageResult;
import com.bob.azure.service.QueueService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class QueueServiceImpl implements QueueService {
    
    private final QueueClient queueClient;

    @Override
    public void send(String message) {
        SendMessageResult sendMessageResult = queueClient.sendMessage(message);
        log.info("Send message id: '{}', payload: '{}'", sendMessageResult.getMessageId(), message);
    }

    @Override
    public PagedIterable<QueueMessageItem> receiveMessages(int size) {
        return queueClient.receiveMessages(size);
    }
}
