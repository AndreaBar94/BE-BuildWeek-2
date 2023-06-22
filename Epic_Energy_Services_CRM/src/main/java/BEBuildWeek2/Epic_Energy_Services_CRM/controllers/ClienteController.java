package BEBuildWeek2.Epic_Energy_Services_CRM.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Cliente;
import BEBuildWeek2.Epic_Energy_Services_CRM.services.ClienteService;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
	@Autowired
	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("")
	public Page<Cliente> getAllClienti(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "idCliente") String sortBy) {
		return clienteService.getAllClienti(page, size, sortBy);
	}

	@GetMapping("/{clienteId}")
	public Cliente getClienteById(@PathVariable UUID clienteId) {
		return clienteService.getClienteById(clienteId);
	}

	@GetMapping(params = "fatturatoAnnuale")
	public ResponseEntity<Page<Cliente>> getClientiByFatturato(
			@RequestParam("fatturatoAnnuale") Double fatturatoAnnuale) {
		Page<Cliente> clienti = clienteService.findClientiByFatturatoAnnuale(fatturatoAnnuale, 0, 20, "idCliente");
		return ResponseEntity.ok(clienti);
	}

	@GetMapping(params = "dataInserimento")
	public ResponseEntity<Page<Cliente>> getClientiByDataInserimento(
			@RequestParam("dataInserimento") String dataInserimento) throws ParseException {
		// salvo data in variabile
		String dateString = dataInserimento;
		// inizializzo la formattazione della data
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		// applico la formattazione della data alla stringa per ottenere oggetto di tipo
		// Date
		Date data = format.parse(dateString);

		Page<Cliente> clientiByData = clienteService.findClientiByDataInserimento(data, 0, 20, "idCliente");
		return ResponseEntity.ok(clientiByData);
	}

	@GetMapping(params = "ultimoContatto")
	public ResponseEntity<Page<Cliente>> getClientiByUltimoContatto(
			@RequestParam("ultimoContatto") String ultimoContatto) throws ParseException {
		// salvo data in variabile
		String dateString = ultimoContatto;
		// inizializzo la formattazione della data
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		// applico la formattazione della data alla stringa per ottenere oggetto di tipo
		// Date
		Date data = format.parse(dateString);

		Page<Cliente> clienti = clienteService.findClientiByDataUltimoContatto(data, 0, 20, "idCliente");
		return ResponseEntity.ok(clienti);
	}

	@GetMapping(params = "ragioneSociale")
	public ResponseEntity<Page<Cliente>> findClientiByRagioneSociale(
			@RequestParam("ragioneSociale") String ragioneSociale) {
		Page<Cliente> clienti = clienteService.findClientiByRagioneSociale(ragioneSociale, 0, 20, "idCliente");
		return ResponseEntity.ok(clienti);
	}

	@PostMapping
	public Cliente createCliente(@RequestBody Cliente cliente) {
		return clienteService.createCliente(cliente);
	}

	@PutMapping("/{clienteId}")
	public Cliente updateCliente(@PathVariable UUID clienteId, @RequestBody Cliente cliente) {
		return clienteService.updateCliente(clienteId, cliente);
	}

	@DeleteMapping("/{clienteId}")
	public void deleteCliente(@PathVariable UUID clienteId) {
		clienteService.deleteCliente(clienteId);
	}
}
