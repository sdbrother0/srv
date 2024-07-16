package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Table4Dto {
    private UUID id;
    private String val;
    private Table1Dto test1;
    private BigDecimal amount;
}
