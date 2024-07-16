package com.example.demo.dto.meta;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class MetaData implements Serializable {
    private String url;
    private String name;
    private String key;
    private Boolean showSelect;
    private Boolean showAction;
    private Boolean showLoader;
    private List<Field> fields;
    private List<Detail> details;
}
