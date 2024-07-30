package srv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import srv.entity.InvoiceDetailsEntity;

public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetailsEntity, Long>, JpaSpecificationExecutor<InvoiceDetailsEntity> {

    @Override
    @EntityGraph(attributePaths = {"product.id", "invoice.customer"})
    Page<InvoiceDetailsEntity> findAll(Specification<InvoiceDetailsEntity> spec, Pageable pageable);
}
