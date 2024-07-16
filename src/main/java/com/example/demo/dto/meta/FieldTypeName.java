package com.example.demo.dto.meta;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FieldTypeName {
    @JsonProperty("text") TEXT,
    @JsonProperty("number") NUMBER,
    @JsonProperty("date") DATE,
    @JsonProperty("lookup") LOOKUP
}
