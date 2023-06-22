package BEBuildWeek2.Epic_Energy_Services_CRM;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Cliente;
import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Comune;
import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Fattura;
import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Indirizzo;
import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Utente;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.FatturaPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.IndirizzoPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.UserRegistrationPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.repositories.ClienteRepository;
import BEBuildWeek2.Epic_Energy_Services_CRM.repositories.FatturaRepository;
import BEBuildWeek2.Epic_Energy_Services_CRM.repositories.IndirizzoRepository;
import BEBuildWeek2.Epic_Energy_Services_CRM.repositories.UtenteRepository;
import BEBuildWeek2.Epic_Energy_Services_CRM.services.ClienteService;
import BEBuildWeek2.Epic_Energy_Services_CRM.services.FatturaService;
import BEBuildWeek2.Epic_Energy_Services_CRM.services.IndirizzoService;
import BEBuildWeek2.Epic_Energy_Services_CRM.services.UtenteService;
import BEBuildWeek2.Epic_Energy_Services_CRM.utils.StatoFattura;

@SpringBootTest
class EpicEnergyServicesCrmApplicationTests {
	@Mock
	private UtenteRepository utenteRepository;

	@InjectMocks
	private UtenteService utenteService;

	@Mock
	private IndirizzoRepository indirizzoRepository;

	@Mock
	private FatturaRepository fatturaRepository;

	@Mock
	private ClienteRepository clienteRepository;

	@InjectMocks
	private ClienteService clienteService;

	@InjectMocks
	private IndirizzoService indirizzoService;

	@InjectMocks
	private FatturaService fatturaService;

	List<Indirizzo> indirizzi = new ArrayList<>();
	UUID idIndirizzo = UUID.randomUUID();
	Indirizzo indirizzoProva = new Indirizzo("Via Prova", 10, 00166, "MI", new Comune());

	// variabili che si possono ripetere molte volte
	UUID idUtente = UUID.randomUUID();
	Date dataProva = new Date();
	Utente utenteProva = new Utente("usernameProva", "nomeProva", "cognomeProva", "email@prova.it", "suPercalifragi12");
	Cliente clienteProva = new Cliente("partitaIva", "ragioneSociale", "emailCliente", dataProva, dataProva, 100.00,
			"pec", "1234", utenteProva, indirizzoProva);
	// Parametri di paginazione
	int page = 0;
	int size = 10;
	String sortBy = "idCliente";

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
	public void testGetAllIndirizzi() throws NotFoundException {
		// Mocking the repository
		Pageable pageable = PageRequest.of(0, 10, Sort.by("sortBy"));
		indirizzi.add(new Indirizzo());

		// Calling the service method
		Page<Indirizzo> page = new PageImpl<>(indirizzi, pageable, 1);
		when(indirizzoRepository.findAll(pageable)).thenReturn(page);

		Page<Indirizzo> result = indirizzoService.getAllIndirizzi(0, 10, "sortBy");

		// Assertions
		assertNotNull(result);
		assertEquals(1, result.getTotalElements());
		// Additional assertions based on your expected data

	}

	@Test
	public void testGetIndirizzoById() throws NotFoundException {
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
		Indirizzo indirizzoProva = new Indirizzo("Via Prova 2", 10, 00166, "MI", new Comune());
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

	@Test
	public void testDeleteIndirizzo() {
		// Chiamare il metodo deleteIndirizzo
		UUID idIndirizzo = UUID.randomUUID();
		Indirizzo indirizzoProvaDaEliminare = new Indirizzo("Via Prova 2", 10, 00166, "MI", new Comune());
		indirizzoProvaDaEliminare.setIdIndirizzo(idIndirizzo);

		// Verificare che il metodo deleteById del repository sia stato chiamato con
		// l'id corretto
		indirizzoService.deleteIndirizzo(idIndirizzo);
		verify(indirizzoRepository, times(1)).deleteById(idIndirizzo);
	}

	@Test
	public void testGetAllClienti() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("sortBy"));
		List<Cliente> clienti = new ArrayList<>();
		clienti.add(new Cliente());
		Page<Cliente> page = new PageImpl<>(clienti, pageable, 1);

		when(clienteRepository.findAll(pageable)).thenReturn(page);

		Page<Cliente> result = clienteService.getAllClienti(0, 10, "sortBy");

		assertNotNull(result);
		assertEquals(1, result.getTotalElements());
	}

	@Test
	public void testGetClienteById() throws NotFoundException {
		UUID idCliente = UUID.randomUUID();
		clienteProva.setIdCliente(idCliente);

		// mock della repo che restituisce l'utente di prova
		when(clienteRepository.findById(idCliente)).thenReturn(Optional.of(clienteProva));

		Cliente result = clienteService.getClienteById(idCliente);

		assertNotNull(result);
		assertEquals(idCliente, result.getIdCliente());
	}

	@Test
	public void testGetClientiByFatturatoAnnuale() throws NotFoundException {
		List<Cliente> clienti = new ArrayList<>();
		Double fatturatoProva = 5000.00;
		clienteProva.setFatturatoAnnuale(fatturatoProva);
		clienti.add(clienteProva);
		int page = 0;
		int size = 10;
		String sortBy = "idCliente";
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		when(clienteRepository.findClientiByFatturatoAnnuale(fatturatoProva, pageable))
				.thenReturn(new PageImpl<>(clienti));

		Page<Cliente> result = clienteService.findClientiByFatturatoAnnuale(fatturatoProva, 0, 10, "idCliente");

		assertNotNull(result);
		assertEquals(1, result.getSize());
	}

	@Test
	public void testFindClientiByDataInserimento() {
		ClienteService clienteService = new ClienteService(clienteRepository);

		// Creazione di una lista di clienti per il test
		List<Cliente> clienti = new ArrayList<>();
		Cliente clienteProva = new Cliente();
		clienteProva.setDataInserimento(new Date());
		clienti.add(clienteProva);

		// Configurazione del mock del repository
		when(clienteRepository.findClientiByDataInserimento(any(), any(Pageable.class)))
				.thenReturn(new PageImpl<>(clienti));

		// Chiamata al metodo di servizio da testare
		Date dataInserimento = new Date();
		Page<Cliente> result = clienteService.findClientiByDataInserimento(dataInserimento, page, size, sortBy);

		// Verifica dei risultati
		assertNotNull(result);
		assertEquals(1, result.getTotalElements());
		assertEquals(clienti, result.getContent());
	}

	@Test
	public void testFindClientiByDataUltimoContatto() {
		ClienteService clienteService = new ClienteService(clienteRepository);

		// Creazione di una lista di clienti per il test
		List<Cliente> clienti = new ArrayList<>();
		Cliente clienteProva = new Cliente();
		clienteProva.setDataUltimoContatto(new Date());
		clienti.add(clienteProva);

		// Configurazione del mock del repository
		when(clienteRepository.findClientiByDataUltimoContatto(any(), any(Pageable.class)))
				.thenReturn(new PageImpl<>(clienti));

		// Chiamata al metodo di servizio da testare
		Date dataUltimoContatto = new Date();
		Page<Cliente> result = clienteService.findClientiByDataUltimoContatto(dataUltimoContatto, page, size, sortBy);

		// Verifica dei risultati
		assertNotNull(result);
		assertEquals(1, result.getTotalElements());
		assertEquals(clienti, result.getContent());
	}

	@Test
	public void testFindClientiByRagioneSociale() {
		ClienteService clienteService = new ClienteService(clienteRepository);

		// Creazione di una lista di clienti per il test
		List<Cliente> clienti = new ArrayList<>();
		Cliente clienteProva = new Cliente();
		clienteProva.setRagioneSociale("ragione prova");
		clienti.add(clienteProva);

		// Configurazione del mock del repository
		when(clienteRepository.findClientiByRagioneSociale(any(), any(Pageable.class)))
				.thenReturn(new PageImpl<>(clienti));

		// Chiamata al metodo di servizio da testare
		Page<Cliente> result = clienteService.findClientiByRagioneSociale("ragione prova", page, size, sortBy);

		// Verifica dei risultati
		assertNotNull(result);
		assertEquals(1, result.getTotalElements());
		assertEquals(clienti, result.getContent());
	}

	@Test
	public void testCreateCliente() {
		ClienteService clienteService = new ClienteService(clienteRepository);
		Cliente clienteProva = new Cliente();
		when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(new Cliente());

		Cliente result = clienteService.createCliente(clienteProva);
		assertNotNull(result);
	}

	@Test
	public void testUpdateCliente() {
		UUID idCliente = UUID.randomUUID();
		
		clienteProva.setIdCliente(idCliente);

		Cliente updatedPayload = new Cliente();

		// Mock del repository per restituire l'utente di prova
		when(clienteRepository.findById(idCliente)).thenReturn(Optional.of(clienteProva));
		when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(clienteProva);

		// Chiamata al metodo del servizio
		Cliente result = clienteService.updateCliente(idCliente, updatedPayload);

		// Asserzioni
		assertNotNull(result);
		assertEquals(updatedPayload.getPec(), result.getPec());
	}

	@Test
	public void testDeleteCliente() {
		UUID idCliente = UUID.randomUUID();
		Cliente clienteProva = new Cliente();
		clienteProva.setIdCliente(idCliente);
		clienteService.deleteCliente(idCliente);
		verify(clienteRepository, times(1)).deleteById(idCliente);

	}

	@Test
	public void testCreateFattura() {
		FatturaService fatturaService = new FatturaService(fatturaRepository);
		FatturaPayload fatturaProva = new FatturaPayload();
		when(fatturaRepository.save(Mockito.any(Fattura.class))).thenReturn(new Fattura());

		Fattura result = fatturaService.createFattura(fatturaProva);
		assertNotNull(result);

	}

	@Test
	public void testUpdateFattura() throws NotFoundException {
		// Crea una fattura di prova con ID
		UUID idFattura = UUID.randomUUID();
		Fattura fatturaProva = new Fattura();
		fatturaProva.setIdFattura(idFattura);

		// Definisci i dati di aggiornamento
		FatturaPayload updatedPayload = new FatturaPayload();
		updatedPayload.setNumeroFattura(123456);
		updatedPayload.setImporto(BigDecimal.valueOf(100.0));
		updatedPayload.setAnno(2023);
		updatedPayload.setData(new Date());
		updatedPayload.setState(StatoFattura.DA_INVIARE);

		// Configura il mock del repository per restituire la fattura di prova
		when(fatturaRepository.findById(idFattura)).thenReturn(Optional.of(fatturaProva));
		when(fatturaRepository.save(Mockito.any(Fattura.class))).thenReturn(fatturaProva);

		// Chiamata al metodo da testare
		Fattura result = fatturaService.updateFattura(idFattura, updatedPayload.getNumeroFattura(),
				updatedPayload.getAnno(), updatedPayload.getData(), updatedPayload.getImporto(),
				updatedPayload.getState());

		// Verifica che la fattura sia stata restituita correttamente
		assertNotNull(result);
		assertEquals(updatedPayload.getNumeroFattura(), result.getNumeroFattura());
		assertEquals(updatedPayload.getAnno(), result.getAnno());
		assertEquals(updatedPayload.getData(), result.getData());
		assertEquals(updatedPayload.getImporto(), result.getImporto());
		assertEquals(updatedPayload.getState(), result.getState());

		// Verifica che il metodo findById sia stato chiamato correttamente
		verify(fatturaRepository, times(1)).findById(idFattura);

		// Verifica che il metodo save sia stato chiamato per salvare le modifiche
		verify(fatturaRepository, times(1)).save(Mockito.any(Fattura.class));
	}

	@Test
	public void testDeleteFattura() {
		UUID idFattura = UUID.randomUUID();
		FatturaPayload fatturaProva = new FatturaPayload();
		when(fatturaRepository.save(Mockito.any(Fattura.class))).thenReturn(new Fattura());
		Fattura result = fatturaService.createFattura(fatturaProva);
		result.setIdFattura(idFattura);
		fatturaService.deleteFattura(idFattura);
		verify(fatturaRepository, times(1)).deleteById(idFattura);
	}

	@Test
	public void testFindFatturaByCliente() {

	}

	@Test
	public void testFindFatturaByStato() {

	}

	@Test
	public void testFindFatturaByData() {	    
	    Date testData = new Date();
	    List<Fattura> fatture = new ArrayList<>();
	    fatture.add(new Fattura());
	    fatture.add(new Fattura());
	    Pageable pageable = PageRequest.of(0, 2, Sort.by("idFattura"));
	    when(fatturaRepository.findByData(pageable, testData)).thenReturn(new PageImpl<>(fatture));
	    Page<Fattura> result = fatturaService.findFatturaByData(0, 2, "idFattura", testData);

	    // Assert
	    assertNotNull(result);
	    assertEquals(2, result.getContent().size());
	    verify(fatturaRepository, times(1)).findByData(pageable, testData);
	}


	@Test
	public void testFatturaByAnno() {
		int testAnno = 2023;
		
		List<Fattura> fatture = new ArrayList<>();
		fatture.add(new Fattura());
		fatture.add(new Fattura());
		
		Pageable pageable = PageRequest.of(0, 2, Sort.by("idFattura"));
	    when(fatturaRepository.findByAnno(pageable, testAnno)).thenReturn(new PageImpl<>(fatture));
	    
	    Page<Fattura> result = fatturaService.findFatturaByAnno(0, 2, "idFattura", testAnno);
		
	    assertNotNull(result);
	    assertEquals(2, result.getContent().size());
	    verify(fatturaRepository, times(1)).findByAnno(pageable, testAnno);
	
	}

	@Test
	public void testFatturaByRangeImporto() {

	}

	@Test
	public void testDeleteAllFatture() {

	}
}
