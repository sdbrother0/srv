package com.example.demo.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class Table3Dto {
    private UUID id;
    private String val;
    private Table1Dto test1;
}
