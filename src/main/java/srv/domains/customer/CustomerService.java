package srv.domains.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import srv.dto.meta.MetaData;
import srv.specification.SimpleLikeSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static srv.service.MapperService.customerMapper;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository productRepository;
    private final ObjectMapper objectMapper;
    private final EntityManager entityManager;
    private MetaData metaData;

    public Page<CustomerDto> findAll(Pageable pageable, @RequestParam(name = "search", required = false) List<String> search, String keyValue) {
        if (Objects.isNull(search)) {
            search = new ArrayList<>();
        }
        Specification<CustomerEntity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(), Objects.isNull(keyValue) ? null : Pair.of(metaData.getKey(), keyValue));
        return productRepository.findAll(simpleLikeSpecification, pageable).map(customerMapper::map);
    }

    @Transactional
    public CustomerDto delete(@RequestParam Long id) {
        CustomerEntity productEntity = productRepository.findById(id).orElseThrow();
        productRepository.deleteById(id);
        return customerMapper.map(productEntity);
    }

    @Transactional
    public CustomerDto save(@RequestBody CustomerDto productDto) {
        CustomerEntity productEntity = customerMapper.map(productDto);
        CustomerEntity savedProductEntity = productRepository.save(productEntity);
        entityManager.flush();
        entityManager.refresh(savedProductEntity);
        return customerMapper.map(savedProductEntity);
    }

    public MetaData getMetaData() throws JsonProcessingException {
        String meta = """
                {
                    "url" : "/customer",
                    "name": "customer",
                    "key": "id",
                    "fields": [
                        {
                            "name": "id",
                            "label": "Id",
                            "type": {
                                "name": "string"
                            },
                            "hidden": false
                        },
                        {
                            "name": "firstName",
                            "label": "First name",
                            "type": {
                                "name": "string"
                            },
                            "editable": true
                        },
                        {
                            "name": "lastName",
                            "label": "Last name",
                            "type": {
                                "name": "string"
                            },
                            "editable": true
                        },
                        {
                            "name": "email",
                            "label": "Email",
                            "type": {
                                "name": "string"
                            },
                            "editable": true
                        }
                    ]
                }
            """;
        metaData = objectMapper.readValue(meta, MetaData.class);
        return metaData;
    }

}
