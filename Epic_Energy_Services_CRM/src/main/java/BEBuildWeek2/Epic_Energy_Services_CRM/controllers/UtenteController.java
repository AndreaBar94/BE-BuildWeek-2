package BEBuildWeek2.Epic_Energy_Services_CRM.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Utente;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.UserRegistrationPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.services.UtenteService;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
	@Autowired
	private UtenteService utenteService;
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Utente saveUtente(@RequestBody @Validated UserRegistrationPayload body) {
		return utenteService.createUtente(body);
	}
	
	@GetMapping("")
	public Page<Utente> getUtente(@RequestParam(defaultValue = "0") int page,
				@RequestParam(defaultValue = "10") int size, 
				@RequestParam(defaultValue ="id") String sortBy) {
		return utenteService.findAllUtenti(page, size, sortBy);
	}
	
	@GetMapping("/{utenteId}")
	public Utente getUtente(@PathVariable UUID utenteId) throws Exception {
		return utenteService.findUtenteById(utenteId);
	}
	
	@PutMapping("/{utenteId}")
	public Utente updateUtente(@PathVariable UUID utenteId, @RequestBody UserRegistrationPayload body) throws Exception {
		return utenteService.findUtenteByIdAndUpdate(utenteId, body);
	}
	
	@DeleteMapping("/{utenteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUtente(@PathVariable UUID utenteId) throws NotFoundException {
		utenteService.findUtenteByIdAndDelete(utenteId);
	}
}
