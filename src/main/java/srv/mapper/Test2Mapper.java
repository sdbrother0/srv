package srv.mapper;

import srv.dto.Table2Dto;
import srv.entity.Test2Entity;
import org.mapstruct.Mapper;

@Mapper
public interface Test2Mapper {
    Test2Entity map(Table2Dto table2Dto);

}
