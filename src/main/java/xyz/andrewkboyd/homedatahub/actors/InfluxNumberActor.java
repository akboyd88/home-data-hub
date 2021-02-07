package xyz.andrewkboyd.etltemplate.actors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import xyz.andrewkboyd.etltemplate.dao.interfaces.LatestNumbersDAO;

@Component
public class InfluxNumberActor {
    private static final Logger LOG = LoggerFactory.getLogger(InfluxNumberActor.class);

    private final LatestNumbersDAO numbersDAO;

    public InfluxNumberActor(@Autowired LatestNumbersDAO influxDAO) {
        this.numbersDAO = influxDAO;
    }

    @KafkaListener(topics = "test.number", groupId = "test.number.influx")
    public void handleNumberUpdate(String strIntValue)  {
        LOG.debug("Received number update for influxdb");
        int val = Integer.parseInt(strIntValue);
        numbersDAO.insertNewNumber(val);
        LOG.info("Added number to influx data store {}", val);
    }
}
