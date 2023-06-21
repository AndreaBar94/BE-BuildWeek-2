package BEBuildWeek2.Epic_Energy_Services_CRM.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Fattura;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.FatturaPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.UserRegistrationPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.services.FatturaService;
import BEBuildWeek2.Epic_Energy_Services_CRM.utils.StatoFattura;

@RestController
@RequestMapping("/fatture")
public class FatturaController {
	private final FatturaService fatturaService;

	@Autowired
	public FatturaController(FatturaService fatturaService) {
		this.fatturaService = fatturaService;
	}

	@GetMapping
	public List<Fattura> getAllFatture() {
		return fatturaService.getAllFatture();
	}

	@GetMapping("/{fatturaId}")
	public Fattura getFatturaById(@PathVariable UUID fatturaId) {
		return fatturaService.getFatturaById(fatturaId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Fattura createFattura(@RequestBody @Validated FatturaPayload body) {
		return fatturaService.createFattura(body);
	}

	@PutMapping("/{fatturaId}")
	public Fattura updateFattura(@PathVariable UUID fatturaId, @RequestBody Fattura fattura) {
		int numeroFattura = fattura.getNumeroFattura();
		int anno = fattura.getAnno();
		Date data = fattura.getData();
		BigDecimal importo = fattura.getImporto();
		StatoFattura stato = fattura.getState();

		return fatturaService.updateFattura(fatturaId, numeroFattura, anno, data, importo, stato);
	}

	@DeleteMapping("/{fatturaId}")
	public void deleteFattura(@PathVariable UUID fatturaId) {
		fatturaService.deleteFattura(fatturaId);
	}

	@GetMapping("/clienti/{clienteId}")
	public List<Fattura> getFattureByCliente(@PathVariable UUID clienteId) {
		return fatturaService.findFatturaByCliente(clienteId);
	}

	@GetMapping("/state/{stato}")
	public List<Fattura> getFattureByStato(@PathVariable String stato) {
		return fatturaService.findFatturaByStato(stato);
	}

	@GetMapping("/data/{data}")
	public List<Fattura> getFattureByData(@PathVariable Date data) {
		return fatturaService.findFatturaByData(data);
	}

	@GetMapping("/anno/{anno}")
	public List<Fattura> getFattureByAnno(@PathVariable int anno) {
		return fatturaService.findFatturaByAnno(anno);
	}

	@GetMapping("/importo/{minImporto}/{maxImporto}")
	public List<Fattura> getFattureByRangeImporto(@PathVariable BigDecimal minImporto,
			@PathVariable BigDecimal maxImporto) {
		return fatturaService.findFatturaByRangeImporto(minImporto, maxImporto);
	}

}
