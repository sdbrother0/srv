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
import srv.dto.InvoiceDto;
import srv.dto.meta.MetaData;
import srv.entity.InvoiceEntity;
import srv.repository.InvoiceRepository;
import srv.specification.SimpleLikeSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static srv.mapper.service.MapperService.invoiceMapper;

@RequiredArgsConstructor
@Service
public class InvoiceService {

    private final ObjectMapper objectMapper;
    private final InvoiceRepository invoiceRepository;

    public Page<InvoiceDto> findAll(Pageable pageable, @RequestParam(value = "masterId", required = false) Long masterId, @RequestParam(name = "search", required = false) List<String> search) {
        if (Objects.isNull(search)) {
            search = new ArrayList<>();
        }
        Specification<InvoiceEntity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(), null);
        return invoiceRepository.findAll(simpleLikeSpecification, pageable).map(invoiceMapper::map);
    }

    @Transactional
    public InvoiceDto delete(@RequestParam Long id) {
        InvoiceEntity invoiceEntity = invoiceRepository.findById(id).orElseThrow();
        invoiceRepository.deleteById(id);
        return invoiceMapper.map(invoiceEntity);
    }

    @Transactional
    public InvoiceDto save(@RequestBody InvoiceDto invoiceDto) {
        InvoiceEntity invoiceEntity = invoiceMapper.map(invoiceDto);
        InvoiceEntity savedInvoiceEntity = invoiceRepository.save(invoiceEntity);
        return invoiceMapper.map(savedInvoiceEntity);
    }

    public MetaData getMetaData() throws JsonProcessingException {
        String meta = """
                {
                    "url" : "http://localhost:8090/invoice",
                    "name": "invoice",
                    "key": "id",
                    "showSelect": true,
                    "showAction": true,
                    "showLoader": true,
                    "fields": [
                        {
                            "name": "id",
                            "label": "Invoice id",
                            "type": {
                                "name": "string"
                            },
                            "hidden": false
                        },
                        {
                            "name": "created",
                            "label": "Date time",
                            "type": {
                                "name": "date",
                                "format": "yyyy-MM-dd HH:mm:ss"
                            },
                            "editable": true,
                            "validation": {
                                "required": true,
                                "message": "Input date please!!!"
                            }
                        },
                        {
                            "name": "customer",
                            "label": "Customer",
                            "type": {
                                "name": "lookup",
                                "metaUrl": "http://localhost:8090/meta/customer",
                                "foreignKey": "customer_id",
                                "keyFieldName": "id",
                                "valFieldName": "name"
                            },
                            "validation": {
                                "required": true,
                                "message": "Select customer please!!!"
                            },
                            "editable": true
                        },
                        {
                            "name": "total",
                            "label": "Total",
                            "type": {
                                "name": "number"
                            },
                            "editable": false
                        }
                    ],
                    "details": [
                          {
                            "label": "Invoice details",
                            "metaUrl" : "http://localhost:8090/meta/invoice_details"
                          }
                    ],
                    "reports": [
                        {
                            "label": "Invoice (report 001)",
                            "url": "http://localhost:8090/report01"
                        },
                        {
                            "label": "Report 2",
                            "url": "http://localhost:8090/report02"
                        }
                    ]
                }
            """;
        return objectMapper.readValue(meta, MetaData.class);
    }

}
