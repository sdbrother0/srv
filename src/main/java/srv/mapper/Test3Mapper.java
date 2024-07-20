package srv.mapper;

import srv.dto.Table3Dto;
import srv.entity.Test3Entity;
import org.mapstruct.Mapper;

@Mapper
public interface Test3Mapper {
    Test3Entity map(Table3Dto table3Dto);
    Table3Dto map(Test3Entity test3Entity);

}
