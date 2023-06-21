package BEBuildWeek2.Epic_Energy_Services_CRM.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Cliente;
import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Fattura;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {
	List<Fattura> findByIdCliente(Cliente idCliente);

	List<Fattura> findByState(String state);

	List<Fattura> findByData(Date data);

	List<Fattura> findByAnno(int anno);

	List<Fattura> findByImportoBetween(BigDecimal minImporto, BigDecimal maxImporto);
}
