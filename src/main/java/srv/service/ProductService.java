package srv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import srv.dto.ProductDto;
import srv.dto.meta.MetaData;
import srv.entity.ProductEntity;
import srv.repository.ProductRepository;
import srv.specification.SimpleLikeSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static srv.mapper.service.MapperService.productMapper;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    public Page<ProductDto> findAll(Pageable pageable, @RequestParam(value = "masterId", required = false) UUID masterId, @RequestParam(name = "search", required = false) List<String> search) {
        if (Objects.isNull(search)) {
            search = new ArrayList<>();
        }
        Specification<ProductEntity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(), null);
        return productRepository.findAll(simpleLikeSpecification, pageable).map(productMapper::map);
    }

    @Transactional
    public ProductDto delete(@RequestParam Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow();
        productRepository.deleteById(id);
        return productMapper.map(productEntity);
    }

    @Transactional
    public ProductDto save(@RequestBody ProductDto productDto) {
        ProductEntity productEntity = productMapper.map(productDto);
        ProductEntity savedProductEntity = productRepository.save(productEntity);
        return productMapper.map(savedProductEntity);
    }

    public MetaData getMetaData() throws JsonProcessingException {
        String meta = """
                {
                    "url" : "http://localhost:8090/product",
                    "name": "product",
                    "key": "id",
                    "showSelect": true,
                    "showAction": true,
                    "showLoader": true,
                    "fields": [
                        {
                            "name": "id",
                            "label": "Id product",
                            "type": {
                                "name": "string"
                            },
                            "hidden": false
                        },
                        {
                            "name": "name",
                            "label": "Product name",
                            "type": {
                                "name": "string"
                            },
                            "editable": true
                        },
                        {
                            "name": "price",
                            "label": "Product price",
                            "type": {
                                "name": "number"
                            },
                            "editable": true
                        }
                    ]
                }
            """;
        return objectMapper.readValue(meta, MetaData.class);
    }

}
