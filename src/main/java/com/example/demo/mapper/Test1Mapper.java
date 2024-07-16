package com.example.demo.mapper;

import com.example.demo.dto.Table1Dto;
import com.example.demo.entity.Test1Entity;
import org.mapstruct.Mapper;

@Mapper
public interface Test1Mapper {
    Test1Entity map(Table1Dto table1Dto);
    Table1Dto map(Test1Entity test1Entity);

}
