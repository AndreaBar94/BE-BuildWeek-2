package BEBuildWeek2.Epic_Energy_Services_CRM;

import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Cliente;
import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Indirizzo;
import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Utente;
import BEBuildWeek2.Epic_Energy_Services_CRM.services.ClienteService;
import BEBuildWeek2.Epic_Energy_Services_CRM.utils.TipoCliente;

@Component
public class ClientiRunner implements CommandLineRunner {

	@Autowired
	private ClienteService clienteService;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		for (int i = 0; i < 20; i++) {
			String partitaIva = faker.business().creditCardNumber();
			String ragioneSociale = faker.company().name();
			TipoCliente tipoCliente = getRandomTipoCliente();
			String emailCliente = faker.internet().emailAddress();
			Date dataInserimento = getDateInPast(365);
			Date dataUltimoContatto = getDateInPast(30);
			Double fatturatoAnnuale = faker.number().randomDouble(2, 0, 10000);
			String pec = faker.internet().emailAddress();
			String telefono = faker.phoneNumber().phoneNumber();
			String nomeUtente = faker.name().firstName();
			String emailUtente = faker.internet().emailAddress();
			String cognomeUtente = faker.name().lastName();
			String via = faker.address().streetName();
			Integer civico = Integer.parseInt(faker.address().buildingNumber());
			Integer cap = Integer.parseInt(faker.address().zipCode());

			// Creazione delle entità correlate
			Utente utente = new Utente();
			utente.setName(nomeUtente);
			utente.setEmailUtente(emailUtente);
			utente.setSurname(cognomeUtente);

			Indirizzo indirizzo = new Indirizzo();
			indirizzo.setVia(via);
			indirizzo.setCivico(civico); // Parse building number to Integer
			indirizzo.setCap(cap);

			// Creazione del cliente
			Cliente cliente = new Cliente(partitaIva, ragioneSociale, emailCliente, dataInserimento, dataUltimoContatto,
					fatturatoAnnuale, pec, telefono, utente, indirizzo);
			cliente.setPartitaIva(partitaIva);
			cliente.setRagioneSociale(ragioneSociale);
			cliente.setTipoCliente(tipoCliente);
			cliente.setEmailCliente(emailCliente);
			cliente.setDataInserimento(dataInserimento);
			cliente.setDataUltimoContatto(dataUltimoContatto);
			cliente.setFatturatoAnnuale(fatturatoAnnuale);
			cliente.setPec(pec);
			cliente.setTelefono(telefono);
			cliente.setIdUtente(utente);
			cliente.setIndirizzo(indirizzo);

			clienteService.createCliente(cliente);
		}
	}

	private TipoCliente getRandomTipoCliente() {
		Random random = new Random();
		int length = TipoCliente.values().length;
		int randomIndex = random.nextInt(length);
		return TipoCliente.values()[randomIndex];
	}

	private Date getDateInPast(int days) {
		Date currentDate = new Date();
		long pastMillis = currentDate.getTime() - TimeUnit.DAYS.toMillis(days);
		return new Date(pastMillis);
	}
}
