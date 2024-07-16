package com.example.demo.dto.meta;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Validation {
    private boolean required;
    private String message;
}
