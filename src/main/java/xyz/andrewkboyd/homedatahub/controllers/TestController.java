package xyz.andrewkboyd.etltemplate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.andrewkboyd.etltemplate.dao.interfaces.LatestNumbersDAO;
import xyz.andrewkboyd.etltemplate.dto.EchoResult;
import xyz.andrewkboyd.etltemplate.dto.LatestNumbers;

/**
 * Simple test controller for test stubs
 */
@RestController
@RequestMapping("api/test")
public class TestController {
    
    private final LatestNumbersDAO postgresNumberDao;
    private final LatestNumbersDAO influxNumbersDao;

    public TestController(
            @Autowired LatestNumbersDAO postgresqDAO,
            @Autowired LatestNumbersDAO influxDAO
    ) {
        postgresNumberDao = postgresqDAO;
        influxNumbersDao = influxDAO;
    }

    /**
     * Echo a received number back to a client in JSON
     * @param num {int} number input
     * @return response wrapping received number
     */
    @GetMapping(value = "/echo/{num}")
    public EchoResult echo(@PathVariable("num") int num) {
        return new EchoResult(num);
    }



    /**
     * Gets the latest numbers from the three different stores (postgresql, influx, cassandra
     * @return {LatestNumbers} object container the latest numbers from each store as properties
     */
    @GetMapping(value = "/latest-numbers", produces = "application/json")
    public LatestNumbers getLatestNumbers() {
        var latestNumbers = new LatestNumbers();
        latestNumbers.setPostgresqlNumber(postgresNumberDao.getLatestNumber());
        latestNumbers.setInfluxNumber(influxNumbersDao.getLatestNumber());
        return latestNumbers;
    }

}
