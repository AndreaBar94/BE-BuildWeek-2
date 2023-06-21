package BEBuildWeek2.Epic_Energy_Services_CRM.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Comune;
@Repository
public interface ComuneRepository extends JpaRepository<Comune, UUID>{
	
    Optional<Comune> findByNome(String nome);

    void deleteByNome(String nome);
}
