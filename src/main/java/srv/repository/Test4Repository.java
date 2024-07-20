package srv.repository;

import srv.entity.Test4Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface Test4Repository extends JpaRepository<Test4Entity, UUID>, JpaSpecificationExecutor<Test4Entity> {
    @Override
    Page<Test4Entity> findAll(Specification<Test4Entity> spec, Pageable pageable);
}
