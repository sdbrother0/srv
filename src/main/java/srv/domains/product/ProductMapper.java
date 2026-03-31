package srv.domains.product;

import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ProductMapper {
    ProductEntity map(ProductDto productDto);

    ProductDto map(ProductEntity productEntity);
}
