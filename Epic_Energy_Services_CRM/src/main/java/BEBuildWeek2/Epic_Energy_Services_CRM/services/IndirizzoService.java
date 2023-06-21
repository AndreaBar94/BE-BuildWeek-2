package BEBuildWeek2.Epic_Energy_Services_CRM.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<Indirizzo> getAllIndirizzi() {
		return indirizzoRepository.findAll();
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
