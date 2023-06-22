package BEBuildWeek2.Epic_Energy_Services_CRM.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		Fattura fattura = new Fattura(f.getNumeroFattura(), f.getAnno(), f.getData(), f.getImporto(), f.getState(),
				f.getIdCliente());
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

	public Page<Fattura> findFatturaByCliente(int page, int size, String sortBy, UUID idCliente) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 20;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return fatturaRepository.findByIdCliente(pageable, clienteService.getClienteById(idCliente));
	}

	public Page<Fattura> findFatturaByStato(int page, int size, String sortBy, StatoFattura state) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 20;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return fatturaRepository.findByState(pageable, state);
	}

	public Page<Fattura> findFatturaByData(int page, int size, String sortBy, Date data) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 20;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return fatturaRepository.findByData(pageable, data);
	}

	public Page<Fattura> findFatturaByAnno(int page, int size, String sortBy, int anno) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 20;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return fatturaRepository.findByAnno(pageable, anno);
	}

	public Page<Fattura> findFatturaByRangeImporto(int page, int size, String sortBy, BigDecimal minImporto, BigDecimal maxImporto) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 20;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return fatturaRepository.findByImportoBetween(pageable, minImporto, maxImporto);
	}

	public void deleteAllFatture() {
		fatturaRepository.deleteAll();
	}

}
