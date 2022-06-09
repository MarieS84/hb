package shb.poc.systemmap.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RuntimeRepository extends JpaRepository<Runtime, Long> {

}
