package srv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import srv.dto.InvoiceDetailsDto;
import srv.dto.meta.MetaData;
import srv.entity.InvoiceDetailsEntity;
import srv.entity.InvoiceEntity;
import srv.repository.InvoiceDetailsRepository;
import srv.repository.InvoiceRepository;
import srv.specification.SimpleLikeSpecification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static srv.mapper.service.MapperService.invoiceDetailsMapper;
import static srv.mapper.service.MapperService.invoiceMapper;

@RequiredArgsConstructor
@Service
public class InvoiceDetailsService {

    private final ObjectMapper objectMapper;
    private final InvoiceDetailsRepository invoiceDetailsRepository;
    private final InvoiceRepository invoiceRepository;
    private final EntityManager entityManager;

    public Page<InvoiceDetailsDto> findAll(Pageable pageable, @RequestParam(value = "masterId", required = false) Long masterId, @RequestParam(name = "search", required = false) List<String> search) {
        if (Objects.isNull(masterId)) {
            return new PageImpl<>(Collections.emptyList());
        }
        if (Objects.isNull(search)) {
            search = new ArrayList<>();
        }
        Specification<InvoiceDetailsEntity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(), null, Map.of("invoice.id", masterId));
        return invoiceDetailsRepository.findAll(simpleLikeSpecification, pageable).map(invoiceDetailsMapper::map);
    }

    @Transactional
    public InvoiceDetailsDto delete(@RequestParam Long id) {
        InvoiceDetailsEntity invoiceDetailsEntity = invoiceDetailsRepository.findById(id).orElseThrow();
        invoiceDetailsRepository.deleteById(id);
        InvoiceEntity invoiceEntity = invoiceRepository.findById(invoiceDetailsEntity.getInvoice().getId()).orElseThrow();
        entityManager.flush();
        entityManager.refresh(invoiceEntity);
        return invoiceDetailsMapper.map(invoiceDetailsEntity);
    }

    @Transactional
    public InvoiceDetailsDto save(@RequestBody InvoiceDetailsDto invoiceDetailsDto) {
        InvoiceEntity invoiceEntity = invoiceMapper.map(invoiceDetailsDto.getInvoice());
        invoiceEntity = invoiceRepository.save(invoiceEntity);

        InvoiceDetailsEntity invoiceDetailsEntity = invoiceDetailsMapper.map(invoiceDetailsDto);
        invoiceDetailsEntity.setInvoice(invoiceEntity);
        invoiceDetailsEntity.setTax(invoiceDetailsDto.getPrice().multiply(BigDecimal.valueOf(0.20)));
        invoiceDetailsEntity = invoiceDetailsRepository.save(invoiceDetailsEntity);

        entityManager.flush();
        entityManager.refresh(invoiceEntity);
        entityManager.refresh(invoiceDetailsEntity);
        return invoiceDetailsMapper.map(invoiceDetailsEntity);
    }

    public MetaData getMetaData() throws JsonProcessingException {
        String meta = """
                {
                    "url" : "/invoice_details",
                    "name": "invoice_details",
                    "key": "id",
                    "showSelect": true,
                    "showAction": true,
                    "showLoader": true,
                    "fields": [
                        {
                            "name": "id",
                            "label": "Invoice details id",
                            "type": {
                                "name": "string"
                            },
                            "hidden": true
                        },
                        {
                            "name": "product",
                            "label": "Product",
                            "type": {
                                "name": "lookup",
                                "metaUrl": "/meta/product",
                                "foreignKey": "product_id",
                                "keyFieldName": "id",
                                "valFieldName": "name",
                                "masterMapping": {
                                    "price": "price",
                                    "tax": "tax",
                                    "quantity": "1"
                                 }
                            },
                            "validation": {
                                "required": true,
                                "message": "Select product please!!!"
                            },
                            "editable": true
                        },
                        {
                            "name": "price",
                            "label": "Price",
                            "type": {
                                "name": "number"
                            },
                            "validation": {
                                "required": true,
                                "message": "Input price please!!!"
                            },
                            "editable": true
                        },
                        {
                            "name": "tax",
                            "label": "Tax",
                            "type": {
                                "name": "number"
                            },  
                            "validation": {
                                "required": true,
                                "message": "Input tax please!!!"
                            },
                            "editable": true
                        },
                        {
                            "name": "quantity",
                            "label": "Quantity",
                            "type": {
                                "name": "number"
                            },
                            "validation": {
                                "required": true,
                                "message": "Input quantity please!!!"
                            },
                            "editable": true
                        },
                        {
                            "name": "amount",
                            "label": "Amount",
                            "type": {
                                "name": "number"
                            },
                            "editable": false
                        }
                    ]
                }
            """;
        return objectMapper.readValue(meta, MetaData.class);
    }

}
