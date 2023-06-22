package BEBuildWeek2.Epic_Energy_Services_CRM.services;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Cliente;
import BEBuildWeek2.Epic_Energy_Services_CRM.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public Page<Cliente> getAllClienti(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 20;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return clienteRepository.findAll(pageable);
	}

	public Cliente getClienteById(UUID idCliente) {
		return clienteRepository.findById(idCliente).orElse(null);
	}

	public Page<Cliente> findClientiByFatturatoAnnuale(Double fatturatoAnnuale, int page, int size, String sortBy) {
		if (fatturatoAnnuale != 0) {
			Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
			return clienteRepository.findClientiByFatturatoAnnuale(fatturatoAnnuale, pageable);
		} else {
			return Page.empty();
		}
	}

	public Page<Cliente> findClientiByDataInserimento(Date dataInserimento, int page, int size, String sortBy) {
		if (dataInserimento != null) {
			Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
			return clienteRepository.findClientiByDataInserimento(dataInserimento, pageable);
		} else {
			return Page.empty();
		}
	}

	public Page<Cliente> findClientiByDataUltimoContatto(Date dataUltimoContatto, int page, int size, String sortBy) {
		if (dataUltimoContatto != null) {
			Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
			return clienteRepository.findClientiByDataUltimoContatto(dataUltimoContatto, pageable);
		} else {
			return Page.empty();
		}
	}

	public Page<Cliente> findClientiByRagioneSociale(String ragioneSociale, int page, int size, String sortBy) {
		if (ragioneSociale != null) {
			Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
			return clienteRepository.findClientiByRagioneSociale(ragioneSociale, pageable);
		} else {
			return Page.empty();
		}
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

	public void deleteAllClienti() {
		clienteRepository.deleteAll();
	}

}
