package xyz.andrewkboyd.etltemplate.entities.postgresql;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "test_numbers")
@Data
public class TestNumbers  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "value")
    private Integer value;

    @Column(name = "created")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ZonedDateTime created;
}
