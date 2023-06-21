package BEBuildWeek2.Epic_Energy_Services_CRM.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Comune;
import BEBuildWeek2.Epic_Energy_Services_CRM.repositories.ComuneRepository;

@Service
public class ComuneService {
	@Autowired
	private final ComuneRepository comuneRepository;

	public ComuneService(ComuneRepository comuneRepository) {
		this.comuneRepository = comuneRepository;
	}

	public List<Comune> getAllComuni() {
		return comuneRepository.findAll();
	}

	public Optional<Comune> getComuneByNome(String nome) {
		return comuneRepository.findByNome(nome);
	}

	public Comune createComune(Comune comune) {
		return comuneRepository.save(comune);
	}

	public Comune updateComune(Comune comune) {
		return comuneRepository.save(comune);
	}

	public void deleteComune(String nome) {
		comuneRepository.deleteByNome(nome);
	}

}
