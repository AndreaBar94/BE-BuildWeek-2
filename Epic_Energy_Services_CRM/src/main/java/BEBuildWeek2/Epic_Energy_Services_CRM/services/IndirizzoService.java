package BEBuildWeek2.Epic_Energy_Services_CRM.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Indirizzo;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.IndirizzoPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.repositories.IndirizzoRepository;

@Service
public class IndirizzoService {
	@Autowired
	private final IndirizzoRepository indirizzoRepository;

	public IndirizzoService(IndirizzoRepository indirizzoRepository) {
		this.indirizzoRepository = indirizzoRepository;
	}

	public Page<Indirizzo> getAllIndirizzi(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 20;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return indirizzoRepository.findAll(pageable);
	}

	public Indirizzo getIndirizzoById(UUID idIndirizzo) {
		return indirizzoRepository.findById(idIndirizzo).orElse(null);
	}

	public Indirizzo createIndirizzo(IndirizzoPayload i) {
		Indirizzo indirizzo = new Indirizzo(i.getVia(), i.getCivico(), i.getCap(), i.getLocalita(), i.getComune());
		return indirizzoRepository.save(indirizzo);
	}

	public Indirizzo findIndirizzoByIdAndUpdate(UUID idIndirizzo, IndirizzoPayload indirizzo) {
		Indirizzo foundIndirizzo = this.getIndirizzoById(idIndirizzo);
		foundIndirizzo.setVia(indirizzo.getVia());
		foundIndirizzo.setCivico(indirizzo.getCivico());
		foundIndirizzo.setCap(indirizzo.getCap());
		return indirizzoRepository.save(foundIndirizzo);
	}

	public void deleteIndirizzo(UUID idIndirizzo) {
		indirizzoRepository.deleteById(idIndirizzo);
	}

	public void deleteAllIndirizzi() {
		indirizzoRepository.deleteAll();
	}

}
