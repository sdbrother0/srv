package com.example.demo.dto.search;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldValue {
    private String field;
    private String value;
}
