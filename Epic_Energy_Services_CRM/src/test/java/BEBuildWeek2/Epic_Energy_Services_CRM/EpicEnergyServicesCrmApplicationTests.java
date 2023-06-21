package BEBuildWeek2.Epic_Energy_Services_CRM;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Indirizzo;
import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Utente;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.IndirizzoPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.UserRegistrationPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.repositories.IndirizzoRepository;
import BEBuildWeek2.Epic_Energy_Services_CRM.repositories.UtenteRepository;
import BEBuildWeek2.Epic_Energy_Services_CRM.services.IndirizzoService;
import BEBuildWeek2.Epic_Energy_Services_CRM.services.UtenteService;

@SpringBootTest
class EpicEnergyServicesCrmApplicationTests {
	@Mock
	private UtenteRepository utenteRepository;

	@InjectMocks
	private UtenteService utenteService;

	@Mock
	private IndirizzoRepository indirizzoRepository;

	@InjectMocks
	private IndirizzoService indirizzoService;
	
	List<Indirizzo> indirizzi = new ArrayList<>();
	UUID idIndirizzo = UUID.randomUUID();
	Indirizzo indirizzoProva = new Indirizzo("Via Prova", 10, 00166);

	// variabili che si possono ripetere molte volte
	UUID idUtente = UUID.randomUUID();
	Utente utenteProva = new Utente("usernameProva", "nomeProva", "cognomeProva", "email@prova.it", "suPercalifragi12");

	@Test
	public void testFindAllUtenti() {

		// Mocking the repository
		Pageable pageable = PageRequest.of(0, 10, Sort.by("sortBy"));
		List<Utente> utenti = new ArrayList<>();
		utenti.add(new Utente());
		Page<Utente> page = new PageImpl<>(utenti, pageable, 1);

		when(utenteRepository.findAll(pageable)).thenReturn(page);

		// Calling the service method
		Page<Utente> result = utenteService.findAllUtenti(0, 10, "sortBy");

		// Assertions
		assertNotNull(result);
		assertEquals(1, result.getTotalElements());
		// Additional assertions based on your expected data

	}

	@Test
	public void testCreateUtente() {
		// Mocking the repository
		UserRegistrationPayload payload = new UserRegistrationPayload();
		payload.setUsername("testuser");
		payload.setName("Test");
		payload.setSurname("User");
		payload.setEmailUtente("testuser@example.com");
		payload.setPassword("password");
		when(utenteRepository.save(Mockito.any(Utente.class))).thenReturn(new Utente());

		// Calling the service method
		Utente result = utenteService.createUtente(payload);

		// Assertions
		assertNotNull(result);
		// Additional assertions based on your expected data

	}

	@Test
	public void testFindUtenteById() throws NotFoundException {
		idUtente = UUID.randomUUID();
		utenteProva.setIdUtente(idUtente);

		// mock della repo che restituisce l'utente di prova
		when(utenteRepository.findById(idUtente)).thenReturn(Optional.of(utenteProva));

		Utente result = utenteService.findUtenteById(idUtente);

		assertNotNull(result);
		assertEquals(idUtente, result.getIdUtente());
	}

	@Test
	public void testFindUtenteByIdAndUpdate() throws NotFoundException {
		idUtente = UUID.randomUUID();
		utenteProva = new Utente("usernameProva2", "nomeProva", "cognomeProva", "email@prova.it", "suPercalifragi12");
		utenteProva.setIdUtente(idUtente);

		UserRegistrationPayload updatedPayload = new UserRegistrationPayload();

		// Mock del repository per restituire l'utente di prova
		when(utenteRepository.findById(idUtente)).thenReturn(Optional.of(utenteProva));
		when(utenteRepository.save(Mockito.any(Utente.class))).thenReturn(utenteProva);

		// Chiamata al metodo del servizio
		Utente result = utenteService.findUtenteByIdAndUpdate(idUtente, updatedPayload);

		// Asserzioni
		assertNotNull(result);
		assertEquals(updatedPayload.getUsername(), result.getUsername());

	}

	@Test
	public void testFindUtenteByIdAndDelete() throws NotFoundException {
		idUtente = UUID.randomUUID();
		utenteProva.setIdUtente(idUtente);

		// Mock del repository per restituire l'utente di prova
		when(utenteRepository.findById(idUtente)).thenReturn(Optional.of(utenteProva));

		// Chiamata al metodo del servizio
		utenteService.findUtenteByIdAndDelete(idUtente);

		verify(utenteRepository, times(1)).delete(utenteProva);
	}

	@Test
	public void testFindUtenteByEmail() throws NotFoundException {
		String email = "test@example.com";
		utenteProva = new Utente("usernameProvaEmail", "nomeProvaEmail", "cognomeProva", email, "suPercalifragi12");

		// Mock del repository per restituire l'utente di prova
		when(utenteRepository.findByEmailUtente(email)).thenReturn(Optional.of(utenteProva));

		// Chiamata al metodo del servizio
		Utente result = utenteService.findUtenteByEmail(email);

		// Asserzioni
		assertNotNull(result);
		assertEquals(email, result.getEmailUtente());
	}

	@Test
	public void testGetAllIndirizzi() {
		// Mocking the repository
		
		indirizzi.add(new Indirizzo());
		when(indirizzoRepository.findAll()).thenReturn(indirizzi);

		// Calling the service method
		List<Indirizzo> result = indirizzoService.getAllIndirizzi();

		// Assertions
		assertNotNull(result);
		assertEquals(1, result.size());
		// Additional assertions based on your expected data

	}

	@Test
	public void testGetIndirizzoById() {
		// Mocking the repository
		idIndirizzo = UUID.randomUUID();
		when(indirizzoRepository.findById(idIndirizzo)).thenReturn(Optional.of(new Indirizzo()));

		// Calling the service method
		Indirizzo result = indirizzoService.getIndirizzoById(idIndirizzo);

		// Assertions
		assertNotNull(result);
		// Additional assertions based on your expected data

	}

	@Test
	public void testCreateIndirizzo() {
		// Mocking the repository
		IndirizzoPayload payload = new IndirizzoPayload();
		payload.setVia("Test Street");
		payload.setCivico(123);
		payload.setCap(12345);
		when(indirizzoRepository.save(Mockito.any(Indirizzo.class))).thenReturn(new Indirizzo());

		// Calling the service method
		Indirizzo result = indirizzoService.createIndirizzo(payload);

		// Assertions
		assertNotNull(result);
		// Additional assertions based on your expected data

	}
	
	@Test
	public void testFindIndirizzoIdAndUpdate() {
	    // Dati di prova
	    UUID idIndirizzo = UUID.randomUUID();
	    Indirizzo indirizzoProva = new Indirizzo("Via Prova 2", 10, 00166);
	    indirizzoProva.setIdIndirizzo(idIndirizzo);

	    // Dati di aggiornamento
	    IndirizzoPayload updatedPayload = new IndirizzoPayload();
	    updatedPayload.setVia("Nuova Via");

	    // Mocking del repository
	    when(indirizzoRepository.findById(idIndirizzo)).thenReturn(Optional.of(indirizzoProva));
	    when(indirizzoRepository.save(Mockito.any(Indirizzo.class))).thenReturn(indirizzoProva);

	    // Chiamata al metodo da testare
	    Indirizzo result = indirizzoService.findIndirizzoByIdAndUpdate(idIndirizzo, updatedPayload);

	    // Verifica dell'output
	    assertNotNull(result);
	    assertEquals(updatedPayload.getVia(), result.getVia());

	    // Verifica delle chiamate al repository
	    verify(indirizzoRepository, times(1)).findById(idIndirizzo);
	    verify(indirizzoRepository, times(1)).save(Mockito.any(Indirizzo.class));
	}

	
//	@Test
//	public void testFindIndirizzoIdAndUpdate() {
//		idIndirizzo = UUID.randomUUID();
//		indirizzoProva = new Indirizzo("Via Prova 2", 10, 00166);
//		indirizzoProva.setIdIndirizzo(idIndirizzo);
//		
//		IndirizzoPayload updatedPayload = new IndirizzoPayload();
//		
//		when(indirizzoRepository.findById(idIndirizzo)).thenReturn(Optional.of(new Indirizzo()));
//		when(indirizzoRepository.save(Mockito.any(Indirizzo.class))).thenReturn(indirizzoProva);
//		
//		Indirizzo result = indirizzoService.findIndirizzoByIdAndUpdate(idIndirizzo, updatedPayload);
//		
//		assertNotNull(result);
//		assertEquals(updatedPayload.getVia(), result.getVia());
//	}
	
	@Test
	public void testDeleteIndirizzo() {
		
	}
}
