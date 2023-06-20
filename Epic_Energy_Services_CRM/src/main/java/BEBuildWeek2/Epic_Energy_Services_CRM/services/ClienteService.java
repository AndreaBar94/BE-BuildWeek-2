package BEBuildWeek2.Epic_Energy_Services_CRM.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
	
	public List<Cliente> findClientiByFatturatoAnnuale(Double fatturatoAnnuale) {
	    if (fatturatoAnnuale != 0) {
	        return clienteRepository.findClientiByFatturatoAnnuale(fatturatoAnnuale);
	    } else {
	        return new ArrayList<>();
	    }
	}

    public List<Cliente> findClientiByDataInserimento(Date dataInserimento) {
        return clienteRepository.findClientiByDataInserimento(dataInserimento);
    }

    public List<Cliente> findClientiByDataUltimoContatto(Date dataUltimoContatto) {
        return clienteRepository.findClientiByDataUltimoContatto(dataUltimoContatto);
    }

    public List<Cliente> findClientiByRagioneSociale(String ragioneSociale) {
        return clienteRepository.findClientiByRagioneSociale(ragioneSociale);
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
