package com.example.demo.mapper;

import com.example.demo.dto.Table2Dto;
import com.example.demo.dto.Table3Dto;
import com.example.demo.entity.Test3Entity;
import org.mapstruct.Mapper;

@Mapper
public interface Test3Mapper {
    Test3Entity map(Table3Dto table2Dto);
    Table3Dto map(Test3Entity test3Entity);

}
