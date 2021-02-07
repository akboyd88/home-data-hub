package xyz.andrewkboyd.etltemplate.initializers;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InfluxDBInitializer implements ApplicationRunner {

    private final InfluxDB influxDB;

    public InfluxDBInitializer(@Qualifier("getInfluxDB") InfluxDB db) {
        influxDB = db;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        String dbName = "etl";
        influxDB.query(new Query("CREATE DATABASE " + dbName));
        String rpName = "etlDefault";
        influxDB.query(new Query("CREATE RETENTION POLICY " + rpName + " ON " + dbName + " DURATION 90d REPLICATION 2 DEFAULT"));
    }
}
