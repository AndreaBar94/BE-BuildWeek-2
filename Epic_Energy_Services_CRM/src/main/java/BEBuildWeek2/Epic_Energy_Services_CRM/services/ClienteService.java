package BEBuildWeek2.Epic_Energy_Services_CRM.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Cliente;
import BEBuildWeek2.Epic_Energy_Services_CRM.repositories.ClienteRepository;

@Service
public class ClienteService {
	private final ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public List<Cliente> getAllClienti() {
		return clienteRepository.findAll();
	}

	public Cliente getClienteById(UUID idCliente) {
		return clienteRepository.findById(idCliente).orElse(null);
	}

	public Cliente createCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente updateCliente(UUID idCliente, Cliente cliente) {
		if (clienteRepository.existsById(idCliente)) {
			cliente.setIdCliente(idCliente);
			return clienteRepository.save(cliente);
		}
		return null;
	}

	public void deleteCliente(UUID idCliente) {
		clienteRepository.deleteById(idCliente);
	}
}
