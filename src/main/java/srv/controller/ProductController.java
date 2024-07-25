package srv.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import srv.dto.ProductDto;
import srv.dto.meta.MetaData;
import srv.entity.ProductEntity;
import srv.repository.ProductRepository;
import srv.specification.SimpleLikeSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static srv.mapper.MapperService.productMapper;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;
    private final EntityManager entityManager;

    private static final String DATA_URL = "/product";

    @GetMapping("/meta" + DATA_URL)
    public Object getMetaData3() throws JsonProcessingException {
        String meta = """
                {
                    "url" : "http://localhost:8090/product",
                    "name": "test3",
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
                                "name": "text"
                            },
                            "editable": true
                        }
                    ]
                }
            """;
        return objectMapper.readValue(meta, MetaData.class);
    }

    @GetMapping(DATA_URL)
    public Page<ProductDto> findAll(Pageable pageable, @RequestParam(value = "masterId", required = false) UUID masterId, @RequestParam(name = "search", required = false) List<String> search) {
        if (Objects.isNull(search)) {
            search = new ArrayList<>();
        }
        Specification<ProductEntity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(), null);
        return productRepository.findAll(simpleLikeSpecification, pageable).map(productMapper::map);
    }

    @Transactional
    @DeleteMapping(DATA_URL)
    public ProductDto delete(@RequestParam Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow();
        productRepository.deleteById(id);
        return productMapper.map(productEntity);
    }

    @PostMapping(DATA_URL)
    @Transactional
    public ProductDto save(@RequestBody ProductDto productDto) {
        ProductEntity productEntity = productMapper.map(productDto);
        ProductEntity savedProductEntity = productRepository.save(productEntity);
        return productMapper.map(savedProductEntity);
    }

}
