package BEBuildWeek2.Epic_Energy_Services_CRM.repositories;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
	Page<Cliente> findClientiByFatturatoAnnuale(Double fatturatoAnnuale, Pageable pageable);

	Page<Cliente> findClientiByDataInserimento(Date dataInserimento, Pageable pageable);

	Page<Cliente> findClientiByDataUltimoContatto(Date dataUltimoContatto, Pageable pageable);

	@Query("SELECT c FROM Cliente c WHERE c.ragioneSociale LIKE %:ragioneSociale%")
	Page<Cliente> findClientiByRagioneSociale(@Param("ragioneSociale") String ragioneSociale, Pageable pageable);
}
