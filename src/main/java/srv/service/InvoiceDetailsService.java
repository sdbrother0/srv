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
import srv.dto.InvoiceDetailsDto;
import srv.dto.InvoiceDto;
import srv.dto.meta.MetaData;
import srv.entity.InvoiceDetailsEntity;
import srv.entity.InvoiceEntity;
import srv.mapper.InvoiceDetailsMapper;
import srv.mapper.InvoiceMapper;
import srv.repository.InvoiceDetailsRepository;
import srv.repository.InvoiceRepository;
import srv.specification.SimpleLikeSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static srv.mapper.service.MapperService.invoiceDetailsMapper;

@RequiredArgsConstructor
@Service
public class InvoiceDetailsService {

    private final ObjectMapper objectMapper;
    private final InvoiceDetailsRepository invoiceRepository;

    public Page<InvoiceDetailsDto> findAll(Pageable pageable, @RequestParam(value = "masterId", required = false) UUID masterId, @RequestParam(name = "search", required = false) List<String> search) {
        if (Objects.isNull(search)) {
            search = new ArrayList<>();
        }
        Specification<InvoiceDetailsEntity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(), null);
        return invoiceRepository.findAll(simpleLikeSpecification, pageable).map(invoiceDetailsMapper::map);
    }

    @Transactional
    public InvoiceDetailsDto delete(@RequestParam Long id) {
        InvoiceDetailsEntity invoiceEntity = invoiceRepository.findById(id).orElseThrow();
        invoiceRepository.deleteById(id);
        return invoiceDetailsMapper.map(invoiceEntity);
    }

    @Transactional
    public InvoiceDetailsDto save(@RequestBody InvoiceDetailsDto invoiceDetailsDto) {
        InvoiceDetailsEntity invoiceDetailsEntity = invoiceDetailsMapper.map(invoiceDetailsDto);
        InvoiceDetailsEntity savedInvoiceDetailsEntity = invoiceRepository.save(invoiceDetailsEntity);
        return invoiceDetailsMapper.map(savedInvoiceDetailsEntity);
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
                            "label": "Invoice ID",
                            "type": {
                                "name": "string"
                            },
                            "hidden": false
                        }
                    ]
                }
            """;
        return objectMapper.readValue(meta, MetaData.class);
    }

}
