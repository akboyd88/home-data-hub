package xyz.andrewkboyd.etltemplate.actors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CassandraNumberActor {
    private static final Logger LOG = LoggerFactory.getLogger(CassandraNumberActor.class);

    @KafkaListener(topics = "test.number", groupId = "test.number.cassandra")
    public void handleNumberUpdate(String modifiedCveUpdate)  {
        LOG.debug("Received number update for cassandra");
    }
}
