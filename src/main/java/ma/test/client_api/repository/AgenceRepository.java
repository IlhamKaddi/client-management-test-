
package ma.test.client_api.repository;

import ma.test.client_api.entity.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenceRepository extends JpaRepository<Agence, Long> {
}
