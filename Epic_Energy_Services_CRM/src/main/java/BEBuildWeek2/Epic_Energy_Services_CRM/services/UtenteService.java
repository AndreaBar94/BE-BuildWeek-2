package BEBuildWeek2.Epic_Energy_Services_CRM.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Utente;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.UserRegistrationPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.repositories.UtenteRepository;

@Service
public class UtenteService {
	@Autowired
	private UtenteRepository utenteRepo;

	public Page<Utente> findAllUtenti(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 20;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return utenteRepo.findAll(pageable);
	}

	public Utente createUtente(UserRegistrationPayload u) {
		// userRepo.findByEmail(u.getEmailUtente()).ifPresent(user -> {
		// throw new BadRequestException("Email" + user.getEmailUtente() + "già in
		// uso.");
		// });
		Utente newUtente = new Utente(u.getNickname(), u.getName(), u.getSurname(), u.getEmailUtente(),
				u.getPassword());
		return utenteRepo.save(newUtente);
	}

	public Utente findUtenteById(UUID id) throws NotFoundException { // throws NotFoundException {
		return utenteRepo.findById(id).orElseThrow(() -> new NotFoundException()); // .orElseThrow(() -> new
																					// NotFoundException("User not
																					// found!"));
	}

	public Utente findUtenteByIdAndUpdate(UUID id, UserRegistrationPayload u) throws NotFoundException { // throws
																											// NotFoundException
																											// {
		Utente foundUser = this.findUtenteById(id);
		foundUser.setIdUtente(id);
		foundUser.setUsername(u.getNickname());
		foundUser.setName(u.getName());
		foundUser.setSurname(u.getSurname());
		foundUser.setEmailUtente(u.getEmailUtente());
		return utenteRepo.save(foundUser);
	}

	public void findUtenteByIdAndDelete(UUID id) throws NotFoundException { // throws NotFoundException {
		Utente foundUtente = this.findUtenteById(id);
		utenteRepo.delete(foundUtente);
	}

	public Optional<Utente> findUtenteByEmail(String email) { // throws NotFoundException {
		return utenteRepo.findByEmail(email); // .orElseThrow(() -> new NotFoundException("User not found!"));
	}
}
