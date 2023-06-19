package BEBuildWeek2.Epic_Energy_Services_CRM.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Utente;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository <Utente, UUID> {
	Optional<Utente> findByEmail(String email);
}
