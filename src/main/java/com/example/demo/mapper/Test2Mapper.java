package com.example.demo.mapper;

import com.example.demo.dto.Table2Dto;
import com.example.demo.entity.Test2Entity;
import org.mapstruct.Mapper;

@Mapper
public interface Test2Mapper {
    Test2Entity map(Table2Dto table2Dto);

}
