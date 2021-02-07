package xyz.andrewkboyd.etltemplate.configuration;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxDBConfig {

    @Value("${influxdb.username}")
    private String username;

    @Value("${influxdb.pwd}")
    private String pwd;

    @Value("${influxdb.url}")
    private String url;

    @Bean
    public InfluxDB getInfluxDB(){
        return InfluxDBFactory.connect(getHostname(), getUsername(), getPassword());
    }

    private String getUsername() {
        return username;
    }

    private String getPassword() {
        return pwd;
    }

    private String getHostname() {
        return url;
    }
}
