package BEBuildWeek2.Epic_Energy_Services_CRM.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Cliente;
import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Fattura;
import BEBuildWeek2.Epic_Energy_Services_CRM.utils.StatoFattura;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {
	Page<Fattura> findByIdCliente(Pageable pageable, Cliente idCliente);

	Page<Fattura> findByState(Pageable pageable, StatoFattura state);

	Page<Fattura> findByData(Pageable pageable, Date data);

	Page<Fattura> findByAnno(Pageable pageable, int anno);

	Page<Fattura> findByImportoBetween(Pageable pageable, BigDecimal minImporto, BigDecimal maxImporto);
}
