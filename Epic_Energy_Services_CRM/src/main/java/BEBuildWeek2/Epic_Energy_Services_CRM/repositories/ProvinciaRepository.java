package BEBuildWeek2.Epic_Energy_Services_CRM.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, UUID> {
	
    Optional<Provincia> findBySigla(String sigla);

    void deleteBySigla(String sigla);
}
