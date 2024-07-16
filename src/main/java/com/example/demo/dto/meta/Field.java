package com.example.demo.dto.meta;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Field {
    private String name;
    private String label;
    private FieldType type;
    private Boolean editable;
    private Boolean hidden;
}
