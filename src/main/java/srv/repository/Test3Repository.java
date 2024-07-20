package srv.repository;

import srv.entity.Test3Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface Test3Repository extends JpaRepository<Test3Entity, UUID>, JpaSpecificationExecutor<Test3Entity> {
    @Override
    Page<Test3Entity> findAll(Specification<Test3Entity> spec, Pageable pageable);
}