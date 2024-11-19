package srv.domains.customer;

import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerEntity map(CustomerDto customerDto);

    CustomerDto map(CustomerEntity customerEntity);
}
