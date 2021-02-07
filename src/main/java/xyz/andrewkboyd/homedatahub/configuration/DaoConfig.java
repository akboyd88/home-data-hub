package xyz.andrewkboyd.etltemplate.configuration;

import org.influxdb.InfluxDB;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.andrewkboyd.etltemplate.dao.interfaces.LatestNumbersDAO;
import xyz.andrewkboyd.etltemplate.dao.postgresql.LatestNumbersImpl;

@Configuration
public class DaoConfig {

    private final InfluxDB influxDB;

    public DaoConfig(@Qualifier("getInfluxDB")InfluxDB influxDB) {
        this.influxDB = influxDB;
    }

    @Bean
    public LatestNumbersDAO postgresqDAO(){
        return new LatestNumbersImpl();
    }

    @Bean
    public LatestNumbersDAO influxDAO() { return new xyz.andrewkboyd.etltemplate.dao.influx.LatestNumbersImpl(influxDB); }
}
