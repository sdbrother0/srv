package srv.domains.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import srv.specification.SimpleLikeSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static srv.service.MapperService.productMapper;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductDto> findAll(Pageable pageable,
                                    @RequestParam(name = "search", required = false) List<String> search,
                                    @RequestParam(name = "keyValue", required = false) String keyValue) {
        if (Objects.isNull(search)) {
            search = new ArrayList<>();
        }
        Specification<ProductEntity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(), Objects.isNull(keyValue) ? null : Pair.of("id", keyValue));
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

}
