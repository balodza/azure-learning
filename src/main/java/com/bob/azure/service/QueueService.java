package com.bob.azure.service;


import com.azure.core.http.rest.PagedIterable;
import com.azure.storage.queue.models.QueueMessageItem;

public interface QueueService {
    void send(String message);

    PagedIterable<QueueMessageItem> receiveMessages(int size);
}
