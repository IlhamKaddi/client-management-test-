package ma.test.client_api.repository;

import ma.test.client_api.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {

    // Vérifier si un CIN existe déjà
    boolean existsByCin(String cin);

    // Recherche par CIN, nom, prénom ou agence avec pagination
    @Query("""
        SELECT c FROM Client c
        LEFT JOIN c.agence a
        WHERE
            LOWER(c.cin) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(c.nom) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(c.prenom) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(a.nom) LIKE LOWER(CONCAT('%', :search, '%'))
        """)
    Page<Client> searchClients(
            @Param("search") String search,
            Pageable pageable
    );
}