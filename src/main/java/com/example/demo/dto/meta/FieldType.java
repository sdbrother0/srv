package com.example.demo.dto.meta;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldType {
    private FieldTypeName name;

    //lookup
    private String metaUrl;
    private String foreignKey;
    private String valFieldName;
    private String keyFieldName;

    //date
    private String format;

}
