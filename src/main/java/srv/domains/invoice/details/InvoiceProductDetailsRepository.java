package srv.domains.invoice.details;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InvoiceProductDetailsRepository extends JpaRepository<InvoiceProductDetailsEntity, Long>, JpaSpecificationExecutor<InvoiceProductDetailsEntity> {

    @Override
    @EntityGraph(attributePaths = {"product.id", "invoice.customer"})
    Page<InvoiceProductDetailsEntity> findAll(Specification<InvoiceProductDetailsEntity> spec, Pageable pageable);
}
