package srv.mapper;

import srv.dto.Table4Dto;
import srv.entity.Test4Entity;
import org.mapstruct.Mapper;

@Mapper
public interface Test4Mapper {
    Test4Entity map(Table4Dto table4Dto);
    Table4Dto map(Test4Entity test4Entity);

}
