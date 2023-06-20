package BEBuildWeek2.Epic_Energy_Services_CRM.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Cliente;
import BEBuildWeek2.Epic_Energy_Services_CRM.services.ClienteService;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
	private final ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping
	public List<Cliente> getAllClienti() {
		return clienteService.getAllClienti();
	}

	@GetMapping("/{clienteId}")
	public Cliente getClienteById(@PathVariable UUID clienteId) {
		return clienteService.getClienteById(clienteId);
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
