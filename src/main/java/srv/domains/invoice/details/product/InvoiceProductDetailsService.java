package srv.domains.invoice.details.product;

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
import srv.dto.meta.MetaData;
import srv.domains.invoice.master.InvoiceEntity;
import srv.domains.invoice.master.InvoiceRepository;
import srv.specification.SimpleLikeSpecification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static srv.service.MapperService.invoiceProductDetailsMapper;
import static srv.service.MapperService.invoiceMapper;

@RequiredArgsConstructor
@Service
public class InvoiceProductDetailsService {

    private final ObjectMapper objectMapper;
    private final InvoiceProductDetailsRepository invoiceProductDetailsRepository;
    private final InvoiceRepository invoiceRepository;
    private final EntityManager entityManager;

    public Page<InvoiceProductDetailsDto> findAll(Pageable pageable, @RequestParam(value = "masterId", required = false) Long masterId, @RequestParam(name = "search", required = false) List<String> search) {
        if (Objects.isNull(masterId)) {
            return new PageImpl<>(Collections.emptyList());
        }
        if (Objects.isNull(search)) {
            search = new ArrayList<>();
        }
        Specification<InvoiceProductDetailsEntity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(), null, Map.of("invoice.id", masterId));
        return invoiceProductDetailsRepository.findAll(simpleLikeSpecification, pageable).map(invoiceProductDetailsMapper::map);
    }

    @Transactional
    public InvoiceProductDetailsDto delete(@RequestParam Long id) {
        InvoiceProductDetailsEntity invoiceProductDetailsEntity = invoiceProductDetailsRepository.findById(id).orElseThrow();
        invoiceProductDetailsRepository.deleteById(id);
        InvoiceEntity invoiceEntity = invoiceRepository.findById(invoiceProductDetailsEntity.getInvoice().getId()).orElseThrow();
        entityManager.flush();
        entityManager.refresh(invoiceEntity);
        return invoiceProductDetailsMapper.map(invoiceProductDetailsEntity);
    }

    @Transactional
    public InvoiceProductDetailsDto save(@RequestBody InvoiceProductDetailsDto invoiceDetailsDto) {
        InvoiceEntity invoiceEntity = invoiceMapper.map(invoiceDetailsDto.getInvoice());
        invoiceEntity = invoiceRepository.save(invoiceEntity);

        InvoiceProductDetailsEntity invoiceProductDetailsEntity = invoiceProductDetailsMapper.map(invoiceDetailsDto);
        invoiceProductDetailsEntity.setInvoice(invoiceEntity);
        invoiceProductDetailsEntity.setTax(invoiceDetailsDto.getPrice().multiply(BigDecimal.valueOf(0.20)));
        invoiceProductDetailsEntity = invoiceProductDetailsRepository.save(invoiceProductDetailsEntity);

        entityManager.flush();
        entityManager.refresh(invoiceEntity);
        entityManager.refresh(invoiceProductDetailsEntity);
        return invoiceProductDetailsMapper.map(invoiceProductDetailsEntity);
    }

    public MetaData getMetaData() throws JsonProcessingException {
        String meta = """
                {
                    "url" : "/invoice_product_details",
                    "name": "invoice_product_details",
                    "key": "id",
                    "fields": [
                        {
                            "name": "id",
                            "label": "Invoice product details id",
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
