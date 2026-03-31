package srv.domains.customer;

import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CustomerMapper {
    CustomerEntity map(CustomerDto customerDto);

    CustomerDto map(CustomerEntity customerEntity);
}
