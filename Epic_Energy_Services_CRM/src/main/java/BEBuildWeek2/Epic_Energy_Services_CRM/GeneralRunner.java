//package BEBuildWeek2.Epic_Energy_Services_CRM;
//
//import java.math.BigDecimal;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.github.javafaker.Faker;
//
//import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Cliente;
//import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Comune;
//import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Indirizzo;
//import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Provincia;
//import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Utente;
//import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.FatturaPayload;
//import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.IndirizzoPayload;
//import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.UserRegistrationPayload;
//import BEBuildWeek2.Epic_Energy_Services_CRM.repositories.ComuneRepository;
//import BEBuildWeek2.Epic_Energy_Services_CRM.services.ClienteService;
//import BEBuildWeek2.Epic_Energy_Services_CRM.services.ComuneService;
//import BEBuildWeek2.Epic_Energy_Services_CRM.services.FatturaService;
//import BEBuildWeek2.Epic_Energy_Services_CRM.services.IndirizzoService;
//import BEBuildWeek2.Epic_Energy_Services_CRM.services.ProvinciaService;
//import BEBuildWeek2.Epic_Energy_Services_CRM.services.UtenteService;
//import BEBuildWeek2.Epic_Energy_Services_CRM.utils.StatoFattura;
//import BEBuildWeek2.Epic_Energy_Services_CRM.utils.TipoCliente;
//
//@Component
//public class GeneralRunner implements CommandLineRunner {
//
//	@Autowired
//	IndirizzoService indirizzoService;
//	@Autowired
//	UtenteService utenteService;
//	@Autowired
//	ClienteService clienteService;
//	@Autowired
//	FatturaService fatturaService;
//	@Autowired
//	ComuneRepository comuneRepository;
//	@Autowired
//	ComuneService comuneService;
//	@Autowired
//	ProvinciaService provinceService;
//	@Autowired
//	private PasswordEncoder bcrypt;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		clienteService.deleteAllClienti();
//		fatturaService.deleteAllFatture();
//		indirizzoService.deleteAllIndirizzi();
//		utenteService.deleteAllUtenti();
////		comuneRepository.deleteAll();
////		provinceService.deleteAllProvince();
//
//		Faker faker = new Faker(new Locale("it"));
//
//		for (int i = 0; i < 20; i++) {
//
//			// SEZIONE CREAZIONE UTENTI
//			String username = faker.name().username();
//			String nome = faker.funnyName().name();
//			String cognome = faker.name().lastName();
//			String emailUtente = faker.internet().emailAddress();
//			String password = bcrypt.encode(faker.internet().password());
//			UserRegistrationPayload utente = new UserRegistrationPayload();
//			utente.setUsername(username);
//			utente.setName(nome);
//			utente.setSurname(cognome);
//			utente.setEmailUtente(emailUtente);
//			utente.setPassword(password);
//			utenteService.createUtente(utente);
//
//			// SEZIONE CREAZIONE INDIRIZZI
//			String via = faker.address().streetName();
//			Integer civico = Integer.parseInt(faker.address().buildingNumber());
//			Integer cap = Integer.parseInt(faker.address().zipCode());
//			Comune comune = getRandomComuneFromDatabase();
//			String provincia = faker.address().cityPrefix();
//			IndirizzoPayload indirizzo = new IndirizzoPayload();
//			indirizzo.setVia(via);
//			indirizzo.setCivico(civico);
//			indirizzo.setCap(cap);
//			indirizzo.setLocalita(provincia);
//			indirizzo.setComune(comune);
//			indirizzoService.createIndirizzo(indirizzo);
//
//			// SEZIONE CREAZIONE CLIENTI
//			String partitaIva = faker.business().creditCardNumber();
//			String ragioneSociale = faker.company().name();
//			TipoCliente tipoCliente = getRandomTipoCliente();
//			String emailCliente = faker.internet().emailAddress();
//			Date dataInserimento = getDateInPast(365);
//			Date dataUltimoContatto = getDateInPast(30);
//			Double fatturatoAnnuale = faker.number().randomDouble(2, 0, 10000);
//			String pec = faker.internet().emailAddress();
//			String telefono = faker.phoneNumber().phoneNumber();
//			Utente superUser = new Utente(utente.getUsername(), utente.getName(), utente.getSurname(),
//					utente.getEmailUtente(), utente.getPassword());
//			Indirizzo superAddress = new Indirizzo(indirizzo.getVia(), indirizzo.getCivico(), indirizzo.getCap(),
//					indirizzo.getLocalita(), indirizzo.getComune());
//			Cliente cliente = new Cliente(partitaIva, ragioneSociale, emailCliente, dataInserimento, dataUltimoContatto,
//					fatturatoAnnuale, pec, telefono, superUser, superAddress);
//			cliente.setPartitaIva(partitaIva);
//			cliente.setRagioneSociale(ragioneSociale);
//			cliente.setTipoCliente(tipoCliente);
//			cliente.setEmailCliente(emailCliente);
//			cliente.setDataInserimento(dataInserimento);
//			cliente.setDataUltimoContatto(dataUltimoContatto);
//			cliente.setFatturatoAnnuale(fatturatoAnnuale);
//			cliente.setPec(pec);
//			cliente.setTelefono(telefono);
//			cliente.setIdUtente(superUser);
//			cliente.setNome(utente.getName());
//			cliente.setEmailUtente(utente.getEmailUtente());
//			cliente.setCognome(utente.getSurname());
//			cliente.setIndirizzo(superAddress);
//			clienteService.createCliente(cliente);
//
//			// SEZIONE CREAZIONE FATTURE
//			int numeroFattura = faker.number().randomDigit();
//			Date data = getDateInPast(100);
//			int anno = getYearFromDate(data);
//			BigDecimal importo = new BigDecimal(faker.number().randomDouble(2, 0, 1000));
//			StatoFattura state = StatoFattura.values()[faker.number().numberBetween(0, StatoFattura.values().length)];
//
//			FatturaPayload fattura = new FatturaPayload();
//			fattura.setNumeroFattura(numeroFattura);
//			fattura.setAnno(anno);
//			fattura.setData(data);
//			fattura.setImporto(importo);
//			fattura.setState(state);
//			fattura.setIdCliente(cliente);
//			fatturaService.createFattura(fattura);
//		}
//
//	}
//
//	private TipoCliente getRandomTipoCliente() {
//		Random random = new Random();
//		int length = TipoCliente.values().length;
//		int randomIndex = random.nextInt(length);
//		return TipoCliente.values()[randomIndex];
//	}
//
//	private Date getDateInPast(int days) {
//		Date currentDate = new Date();
//		long pastMillis = currentDate.getTime() - TimeUnit.DAYS.toMillis(days);
//		return new Date(pastMillis);
//	}
//
//	private int getYearFromDate(Date date) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		return calendar.get(Calendar.YEAR);
//	}
//
//	private Comune getRandomComuneFromDatabase() {
//		// Recupera una lista di comuni dal database (es. tramite il repository dei
//		// comuni)
//		List<Comune> comuni = comuneService.getAllComuni();
//
//		// Verifica se la lista dei comuni è vuota
//		if (comuni.isEmpty()) {
//			throw new IllegalStateException(
//					"La lista dei comuni è vuota. Assicurati che ci siano comuni nel database.");
//		}
//
//		// Ottieni un comune casuale dalla lista
//		Random random = new Random();
//		int randomIndex = random.nextInt(comuni.size());
//		return comuni.get(randomIndex);
//	}
//
//}
