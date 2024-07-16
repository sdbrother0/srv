package com.example.demo.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapperService {
    public static final Test1Mapper test1Mapper = Mappers.getMapper(Test1Mapper.class);
    public static final Test2Mapper test2Mapper = Mappers.getMapper(Test2Mapper.class);
    public static final Test3Mapper test3Mapper = Mappers.getMapper(Test3Mapper.class);
    public static final Test4Mapper test4Mapper = Mappers.getMapper(Test4Mapper.class);
}
