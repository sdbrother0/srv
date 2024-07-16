package com.example.demo.dto.meta;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FieldTypeName {
    @JsonProperty("string") STRING,
    @JsonProperty("number") NUMBER,
    @JsonProperty("text") TEXT,
    @JsonProperty("date") DATE,
    @JsonProperty("lookup") LOOKUP
}
