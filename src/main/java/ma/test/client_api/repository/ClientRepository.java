
package ma.test.client_api.repository;

import ma.test.client_api.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByCin(String cin);

}
