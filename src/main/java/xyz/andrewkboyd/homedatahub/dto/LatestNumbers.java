package xyz.andrewkboyd.etltemplate.dto;

import lombok.Data;

@Data
public class  LatestNumbers {
    private int postgresqlNumber;
    private int cassandraNumber;
    private int influxNumber;
}
