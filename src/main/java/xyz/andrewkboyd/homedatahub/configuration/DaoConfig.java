package xyz.andrewkboyd.homedatahub.configuration;

import org.influxdb.InfluxDB;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.andrewkboyd.homedatahub.dao.interfaces.LatestNumbersDAO;
import xyz.andrewkboyd.homedatahub.dao.postgresql.LatestNumbersImpl;

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
    public LatestNumbersDAO influxDAO() { return new xyz.andrewkboyd.homedatahub.dao.influx.LatestNumbersImpl(influxDB); }
}
