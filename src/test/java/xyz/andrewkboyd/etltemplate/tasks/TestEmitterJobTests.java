package xyz.andrewkboyd.etltemplate.tasks;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.CompletableToListenableFutureAdapter;

import java.util.concurrent.CompletableFuture;

@RunWith(SpringRunner.class)
class TestEmitterJobTests {

    @Test
    void testEmitterEmitsRandomData() throws JobExecutionException {
        @SuppressWarnings("unchecked") KafkaTemplate<String,String> mockTemplate = Mockito.mock(KafkaTemplate.class);
        var context = Mockito.mock(JobExecutionContext.class);
        var job = new TestEmitterJob(mockTemplate);
        CompletableFuture<SendResult<String,String>> future = new CompletableFuture<>();
        CompletableToListenableFutureAdapter<SendResult<String,String>> adapter = new CompletableToListenableFutureAdapter<>(future);
        Mockito.when(mockTemplate.send(Mockito.any(), Mockito.any())).thenReturn(adapter);
        future.complete(new SendResult<>(new ProducerRecord<String,String>("test", "test"), new RecordMetadata(
                new TopicPartition("test", 0),
                0L,
                0L,
                0L,
                0L,
                0,
                0
        )));
        job.executeInternal(context);
        Mockito.verify(mockTemplate, Mockito.times(1)).send(Mockito.any(), Mockito.any());
    }
}
