
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

    // Recherche avec filtres séparés : CIN, nom/prénom et agence
    @Query("""
        SELECT c FROM Client c
        LEFT JOIN c.agence a
        WHERE
            (:cin = '' OR LOWER(c.cin) LIKE LOWER(CONCAT('%', :cin, '%')))
            AND (
                :nomPrenom = ''
                OR LOWER(c.nom) LIKE LOWER(CONCAT('%', :nomPrenom, '%'))
                OR LOWER(c.prenom) LIKE LOWER(CONCAT('%', :nomPrenom, '%'))
            )
            AND (
                :agenceId IS NULL
                OR a.id = :agenceId
            )
        """)
    Page<Client> searchWithFilters(
            @Param("cin") String cin,
            @Param("nomPrenom") String nomPrenom,
            @Param("agenceId") Long agenceId,
            Pageable pageable
    );
}
