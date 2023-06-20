package BEBuildWeek2.Epic_Energy_Services_CRM.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
	List<Cliente> findClientiByFatturatoAnnuale(Double fatturatoAnnuale);
	List<Cliente> findClientiByDataInserimento(Date dataInserimento);
	List<Cliente> findClientiByDataUltimoContatto(Date dataUltimoContatto);

    @Query("SELECT c FROM Cliente c WHERE c.ragioneSociale LIKE %:ragioneSociale%")
    List<Cliente> findClientiByRagioneSociale(@Param("ragioneSociale") String ragioneSociale);
}
