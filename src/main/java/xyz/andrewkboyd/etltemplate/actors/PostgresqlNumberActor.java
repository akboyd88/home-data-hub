package xyz.andrewkboyd.etltemplate.actors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.andrewkboyd.etltemplate.dao.interfaces.LatestNumbersDAO;

@Component
public class PostgresqlNumberActor {
    private static final Logger LOG = LoggerFactory.getLogger(PostgresqlNumberActor.class);
    private final LatestNumbersDAO latestNumbersDAO;

    public PostgresqlNumberActor(@Autowired LatestNumbersDAO postgresqDAO){
        latestNumbersDAO = postgresqDAO;
    }

    @KafkaListener(topics = "test.number", groupId = "test.number.pg")
    public void handleNumberUpdate(String value)  {
        LOG.debug("Received number update for postgresql");
        var intVal = Integer.parseInt(value);
        latestNumbersDAO.insertNewNumber(intVal);
        LOG.info("Added number to pg data store {}", intVal);
    }
}
