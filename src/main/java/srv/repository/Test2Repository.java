package srv.repository;

import srv.entity.Test2Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface Test2Repository extends JpaRepository<Test2Entity, UUID>, JpaSpecificationExecutor<Test2Entity> {
}
