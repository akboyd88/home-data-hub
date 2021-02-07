package xyz.andrewkboyd.etltemplate.dao.influx;

import org.influxdb.InfluxDB;
import org.influxdb.dto.BoundParameterQuery;
import org.influxdb.dto.Point;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import xyz.andrewkboyd.etltemplate.dao.interfaces.LatestNumbersDAO;
import xyz.andrewkboyd.etltemplate.entities.influx.TestNumbers;

import java.util.concurrent.TimeUnit;

public class LatestNumbersImpl extends BaseDaoImpl<TestNumbers> implements LatestNumbersDAO {
    private final InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

    public LatestNumbersImpl(@Qualifier("getInfluxDB") InfluxDB influxDB) {
        super(influxDB);
    }

    @Override
    public Point getPointFromValue(TestNumbers val) {
        return Point.measurement("test_number")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("value", val.getValue())
                .build();
    }

    /**
     * Get the latest number from the store
     *
     * @return {int}
     */
    @Override
    public int getLatestNumber() {
       var result = resultMapper.toPOJO(getDb().query(BoundParameterQuery.QueryBuilder.newQuery("\"SELECT * FROM test_number ORDER BY desc LIMIT 1")
                .forDatabase("etl")
                .create()), TestNumbers.class);
       if(result.isEmpty()) {
           return -1;
       } else {
           return result.get(0).getValue();
       }
    }

    /**
     * Add a new number to the store
     *
     * @param number new test number
     */
    @Override
    public void insertNewNumber(int number) {
        var testNumber = new TestNumbers();
        testNumber.setValue(number);
        save(testNumber);
    }
}
