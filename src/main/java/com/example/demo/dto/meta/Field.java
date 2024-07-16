package com.example.demo.dto.meta;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Field {
    private String name;
    private String label;
    private FieldType type;
    private Boolean editable;
    private Boolean hidden;
    private Validation validation;
}
