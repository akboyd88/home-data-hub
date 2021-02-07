package xyz.andrewkboyd.etltemplate.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

public @Data
class SearchCriteria {
    private BigInteger offset;
    private long count;
    private List<String> terms;
}
