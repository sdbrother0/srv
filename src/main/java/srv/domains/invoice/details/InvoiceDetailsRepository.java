package srv.domains.invoice.details;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetailsEntity, Long>, JpaSpecificationExecutor<InvoiceDetailsEntity> {

    @Override
    @EntityGraph(attributePaths = {"product.id", "invoice.customer"})
    Page<InvoiceDetailsEntity> findAll(Specification<InvoiceDetailsEntity> spec, Pageable pageable);
}
