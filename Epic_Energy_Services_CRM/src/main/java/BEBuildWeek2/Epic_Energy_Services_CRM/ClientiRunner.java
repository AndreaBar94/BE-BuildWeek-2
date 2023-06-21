package BEBuildWeek2.Epic_Energy_Services_CRM;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import BEBuildWeek2.Epic_Energy_Services_CRM.services.UtenteService;

@Component
public class ClientiRunner implements CommandLineRunner{
	
	@Autowired
	UtenteService utenteService;
	@Autowired
	private PasswordEncoder bcrypt;
	
	@Override
	public void run(String... args) throws Exception {
		
		Faker faker = new Faker(new Locale("it"));
		for(int i = 0; i < 20; i++) {
			String partitaIva = faker.howIMetYourMother().quote();
		}
	}

}
