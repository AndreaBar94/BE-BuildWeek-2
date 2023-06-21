package BEBuildWeek2.Epic_Energy_Services_CRM;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Utente;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.UserRegistrationPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.services.UtenteService;

@Component
public class UtentiRunner implements CommandLineRunner {

	@Autowired
	UtenteService utenteService;
	@Autowired
	private PasswordEncoder bcrypt;
	private Utente utente;

	@Override
	public void run(String... args) throws Exception {

		Faker faker = new Faker(new Locale("it"));

		for (int i = 0; i < 20; i++) {
			String username = faker.name().username();
			String nome = faker.funnyName().name();
			String cognome = faker.name().lastName();
			String emailUtente = faker.internet().emailAddress();
			String password = bcrypt.encode(faker.internet().password());
			UserRegistrationPayload utente = new UserRegistrationPayload();
			utente.setUsername(username);
			utente.setName(nome);
			utente.setSurname(cognome);
			utente.setEmailUtente(emailUtente);
			utente.setPassword(password);
			utenteService.createUtente(utente);
		}
	}

	public Utente getUtente() {
		return this.utente;
	}
}
