package srv.mapper.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import srv.mapper.ProductMapper;
import srv.mapper.Test1Mapper;
import srv.mapper.Test2Mapper;
import srv.mapper.Test3Mapper;
import srv.mapper.Test4Mapper;

@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapperService {
    public static final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    public static final Test1Mapper test1Mapper = Mappers.getMapper(Test1Mapper.class);
    public static final Test2Mapper test2Mapper = Mappers.getMapper(Test2Mapper.class);
    public static final Test3Mapper test3Mapper = Mappers.getMapper(Test3Mapper.class);
    public static final Test4Mapper test4Mapper = Mappers.getMapper(Test4Mapper.class);
}
