package com.bob.azure.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.azure.core.http.rest.PagedIterable;
import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.models.QueueMessageItem;
import com.azure.storage.queue.models.SendMessageResult;

@ExtendWith(MockitoExtension.class)
class QueueServiceImplTest {

    @Mock
    QueueClient queueClient;
    @Mock
    SendMessageResult sendMessageResult;
    @Mock
    PagedIterable<QueueMessageItem> pagedIterable;
    @InjectMocks
    QueueServiceImpl queueService;

    @Test
    void send_delegatesToQueueClientWithMessage() {
        when(queueClient.sendMessage("hello")).thenReturn(sendMessageResult);
        when(sendMessageResult.getMessageId()).thenReturn("msg-123");

        queueService.send("hello");

        verify(queueClient).sendMessage("hello");
    }

    @Test
    void receiveMessages_returnsResultFromQueueClient() {
        when(queueClient.receiveMessages(5)).thenReturn(pagedIterable);

        var result = queueService.receiveMessages(5);

        assertThat(result).isSameAs(pagedIterable);
        verify(queueClient).receiveMessages(5);
    }

    @Test
    void receiveMessages_passesRequestedSizeToQueueClient() {
        when(queueClient.receiveMessages(32)).thenReturn(pagedIterable);

        queueService.receiveMessages(32);

        verify(queueClient).receiveMessages(32);
    }
}
