package BEBuildWeek2.Epic_Energy_Services_CRM;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
	public void testGetAllIndirizzi() {
		// Mocking the repository
		List<Indirizzo> indirizzi = new ArrayList<>();
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
		UUID id = UUID.randomUUID();
		when(indirizzoRepository.findById(id)).thenReturn(Optional.of(new Indirizzo()));

		// Calling the service method
		Indirizzo result = indirizzoService.getIndirizzoById(id);

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
}
