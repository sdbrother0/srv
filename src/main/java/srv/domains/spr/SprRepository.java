package srv.domains.spr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SprRepository extends JpaRepository<SprEntity, UUID>, JpaSpecificationExecutor<SprEntity> {

}
