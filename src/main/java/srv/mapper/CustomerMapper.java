package srv.mapper;

import org.mapstruct.Mapper;
import srv.dto.CustomerDto;
import srv.entity.CustomerEntity;

@Mapper
public interface CustomerMapper {
    CustomerEntity map(CustomerDto customerDto);

    CustomerDto map(CustomerEntity customerEntity);
}
