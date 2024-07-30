package srv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import srv.dto.CustomerDto;
import srv.dto.meta.MetaData;
import srv.entity.CustomerEntity;
import srv.repository.CustomerRepository;
import srv.specification.SimpleLikeSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static srv.mapper.service.MapperService.customerMapper;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository productRepository;
    private final ObjectMapper objectMapper;
    private final EntityManager entityManager;

    public Page<CustomerDto> findAll(Pageable pageable, @RequestParam(value = "masterId", required = false) UUID masterId, @RequestParam(name = "search", required = false) List<String> search) {
        if (Objects.isNull(search)) {
            search = new ArrayList<>();
        }
        Specification<CustomerEntity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(), null);
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
                    "url" : "http://localhost:8090/customer",
                    "name": "customer",
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
                            "name": "street",
                            "label": "Street",
                            "type": {
                                "name": "string"
                            },
                            "editable": true
                        },
                        {
                            "name": "city",
                            "label": "City",
                            "type": {
                                "name": "string"
                            },
                            "editable": true
                        },
                        {
                            "name": "state",
                            "label": "State",
                            "type": {
                                "name": "string"
                            },
                            "editable": true
                        },
                        {
                            "name": "zipCode",
                            "label": "Zip code",
                            "type": {
                                "name": "string"
                            },
                            "editable": true
                        },
                        {
                            "name": "phoneNumber",
                            "label": "Phone number",
                            "type": {
                                "name": "string"
                            },
                            "editable": true
                        }
                    ]
                }
            """;
        return objectMapper.readValue(meta, MetaData.class);
    }

}
