package xyz.andrewkboyd.etltemplate.entities.influx;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Data
@Measurement(name = "test_number")
public class TestNumbers {
    @Column(name = "value")
    private Integer value;
}
