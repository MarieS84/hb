package shb.poc.systemmap.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repository provides capabilities to store, update, retrieve and delete objects
@Repository
public interface BusinessSystemRepository extends JpaRepository<BusinessSystem, Long> {
}
