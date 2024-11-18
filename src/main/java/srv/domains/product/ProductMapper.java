package srv.domains.product;

import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    ProductEntity map(ProductDto productDto);

    ProductDto map(ProductEntity productEntity);
}
