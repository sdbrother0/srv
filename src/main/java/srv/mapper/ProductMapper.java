package srv.mapper;

import org.mapstruct.Mapper;
import srv.dto.ProductDto;
import srv.entity.ProductEntity;

@Mapper
public interface ProductMapper {
    ProductEntity map(ProductDto productDto);
    ProductDto map(ProductEntity productEntity);
}
