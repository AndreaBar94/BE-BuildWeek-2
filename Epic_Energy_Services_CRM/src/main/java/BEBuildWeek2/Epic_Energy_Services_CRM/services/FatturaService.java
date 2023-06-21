package BEBuildWeek2.Epic_Energy_Services_CRM.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Fattura;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.FatturaPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.repositories.FatturaRepository;
import BEBuildWeek2.Epic_Energy_Services_CRM.utils.StatoFattura;

@Service
public class FatturaService {
	@Autowired
	private final FatturaRepository fatturaRepository;

	public FatturaService(FatturaRepository fatturaRepository) {
		this.fatturaRepository = fatturaRepository;
	}

	@Autowired
	ClienteService clienteService;

	public Fattura createFattura(FatturaPayload f) {
		Fattura fattura = new Fattura(f.getNumeroFattura(), f.getAnno(), f.getData(), f.getImporto(), f.getState(), f.getIdCliente());
		return fatturaRepository.save(fattura);
	}

	public Fattura getFatturaById(UUID id) {
		return fatturaRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Fattura non trovata con ID: " + id));
	}

	public List<Fattura> getAllFatture() {
		return fatturaRepository.findAll();
	}

	public Fattura updateFattura(UUID id, int numeroFattura, int anno, java.util.Date data, BigDecimal importo,
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

	public List<Fattura> findFatturaByCliente(UUID idCliente) {

		return fatturaRepository.findByIdCliente(clienteService.getClienteById(idCliente));
	}

	public List<Fattura> findFatturaByStato(String state) {
		return fatturaRepository.findByState(state);
	}

	public List<Fattura> findFatturaByData(Date data) {
		return fatturaRepository.findByData(data);
	}

	public List<Fattura> findFatturaByAnno(int anno) {
		return fatturaRepository.findByAnno(anno);
	}

	public List<Fattura> findFatturaByRangeImporto(BigDecimal minImporto, BigDecimal maxImporto) {
		return fatturaRepository.findByImportoBetween(minImporto, maxImporto);
	}

}
