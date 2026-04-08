package srv.domains.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import srv.specification.SimpleLikeSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public Page<ProductDto> findAll(Pageable pageable,
                                    List<String> search,
                                    String keyValue) {
        if (Objects.isNull(search)) {
            search = new ArrayList<>();
        }
        Specification<ProductEntity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(), Objects.isNull(keyValue) ? null : Pair.of("id", keyValue));
        return productRepository.findAll(simpleLikeSpecification, pageable).map(productMapper::map);
    }

    @Transactional
    public ProductDto delete(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow();
        productRepository.deleteById(id);
        return productMapper.map(productEntity);
    }

    @Transactional
    public ProductDto save(ProductDto productDto) {
        ProductEntity productEntity = productMapper.map(productDto);
        ProductEntity savedProductEntity = productRepository.save(productEntity);
        return productMapper.map(savedProductEntity);
    }

}
