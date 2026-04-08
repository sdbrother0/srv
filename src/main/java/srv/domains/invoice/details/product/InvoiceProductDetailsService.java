package srv.domains.invoice.details.product;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import srv.domains.invoice.master.InvoiceEntity;
import srv.domains.invoice.master.InvoiceMapper;
import srv.domains.invoice.master.InvoiceRepository;
import srv.specification.SimpleLikeSpecification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class InvoiceProductDetailsService {

    private final InvoiceProductDetailsRepository invoiceProductDetailsRepository;
    private final InvoiceRepository invoiceRepository;
    private final EntityManager entityManager;
    private final InvoiceProductDetailsMapper invoiceProductDetailsMapper;
    private final InvoiceMapper invoiceMapper;

    public Page<InvoiceProductDetailsDto> findAll(Pageable pageable, Long masterId, List<String> search) {
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
    public InvoiceProductDetailsDto delete(Long id) {
        InvoiceProductDetailsEntity invoiceProductDetailsEntity = invoiceProductDetailsRepository.findById(id).orElseThrow();
        invoiceProductDetailsRepository.deleteById(id);
        InvoiceEntity invoiceEntity = invoiceRepository.findById(invoiceProductDetailsEntity.getInvoice().getId()).orElseThrow();
        entityManager.flush();
        entityManager.refresh(invoiceEntity);
        return invoiceProductDetailsMapper.map(invoiceProductDetailsEntity);
    }

    @Transactional
    public InvoiceProductDetailsDto save(InvoiceProductDetailsDto invoiceDetailsDto) {
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

}
