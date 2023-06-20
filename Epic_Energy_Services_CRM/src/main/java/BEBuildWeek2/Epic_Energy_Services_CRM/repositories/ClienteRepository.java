package BEBuildWeek2.Epic_Energy_Services_CRM.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Cliente;
import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Utente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
	List<Cliente> findClientiByFatturatoAnnuale(Double fatturatoAnnuale);
}
