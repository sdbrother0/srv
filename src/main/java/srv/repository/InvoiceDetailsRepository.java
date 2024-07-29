package srv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import srv.entity.InvoiceDetailsEntity;
import srv.entity.InvoiceEntity;

public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetailsEntity, Long>, JpaSpecificationExecutor<InvoiceDetailsEntity> {

}
