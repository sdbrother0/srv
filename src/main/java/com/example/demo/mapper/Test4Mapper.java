package com.example.demo.mapper;

import com.example.demo.dto.Table3Dto;
import com.example.demo.dto.Table4Dto;
import com.example.demo.entity.Test3Entity;
import com.example.demo.entity.Test4Entity;
import org.mapstruct.Mapper;

@Mapper
public interface Test4Mapper {
    Test4Entity map(Table4Dto table4Dto);
    Table4Dto map(Test4Entity test4Entity);

}
