package BEBuildWeek2.Epic_Energy_Services_CRM.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Fattura;
import BEBuildWeek2.Epic_Energy_Services_CRM.repositories.FatturaRepository;
import BEBuildWeek2.Epic_Energy_Services_CRM.utils.StatoFattura;

@Service
public class FatturaService {
	private final FatturaRepository fatturaRepository;

	public FatturaService(FatturaRepository fatturaRepository) {
		this.fatturaRepository = fatturaRepository;
	}

	public Fattura createFattura(int numeroFattura, int anno, Date data, BigDecimal importo, StatoFattura stato) {
		Fattura fattura = new Fattura();
		fattura.setNumeroFattura(numeroFattura);
		fattura.setAnno(anno);
		fattura.setData(data);
		fattura.setImporto(importo);
		fattura.setState(stato);
		return fatturaRepository.save(fattura);
	}

	public Fattura getFatturaById(UUID id) {
		return fatturaRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Fattura non trovata con ID: " + id));
	}

	public List<Fattura> getAllFatture() {
		return fatturaRepository.findAll();
	}

	public Fattura updateFattura(UUID id, int numeroFattura, int anno, Date data, BigDecimal importo,
			StatoFattura stato) {
		Fattura fattura = getFatturaById(id);
		fattura.setNumeroFattura(numeroFattura);
		fattura.setAnno(anno);
		fattura.setData(data);
		fattura.setImporto(importo);
		fattura.setState(stato);
		return fatturaRepository.save(fattura);
	}

	public void deleteFattura(UUID id) {
		fatturaRepository.deleteById(id);
	}
}