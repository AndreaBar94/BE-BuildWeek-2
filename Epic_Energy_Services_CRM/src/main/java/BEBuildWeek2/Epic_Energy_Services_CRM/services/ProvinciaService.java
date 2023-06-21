package BEBuildWeek2.Epic_Energy_Services_CRM.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Provincia;
import BEBuildWeek2.Epic_Energy_Services_CRM.repositories.ProvinciaRepository;

@Service
public class ProvinciaService {

	private final ProvinciaRepository provinciaRepository;

	@Autowired
	public ProvinciaService(ProvinciaRepository provinciaRepository) {
		this.provinciaRepository = provinciaRepository;
	}

	public List<Provincia> getAllProvince() {
		return provinciaRepository.findAll();
	}

	public Optional<Provincia> getProvinciaBySigla(String sigla) {
		return provinciaRepository.findBySigla(sigla);
	}

	public Provincia createProvincia(Provincia provincia) {
		return provinciaRepository.save(provincia);
	}

	public Provincia updateProvincia(Provincia provincia) {
		return provinciaRepository.save(provincia);
	}

	public void deleteProvincia(String sigla) {
		provinciaRepository.deleteBySigla(sigla);
	}

	public void deleteAllProvince() {
		provinciaRepository.deleteAll();
		;
	}
}
