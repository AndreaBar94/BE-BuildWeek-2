package BEBuildWeek2.Epic_Energy_Services_CRM;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Indirizzo;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.IndirizzoPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.services.IndirizzoService;

@Component
public class IndirizzoRunner implements CommandLineRunner {

	@Autowired
	IndirizzoService indirizzoService;
	private Indirizzo indirizzo;

	@Override
	public void run(String... args) throws Exception {

		Faker faker = new Faker(new Locale("it"));

		for (int i = 0; i < 20; i++) {
			String via = faker.address().streetName();
			Integer civico = Integer.parseInt(faker.address().buildingNumber());
			Integer cap = Integer.parseInt(faker.address().zipCode());
			IndirizzoPayload indirizzo = new IndirizzoPayload();
			indirizzo.setVia(via);
			indirizzo.setCivico(civico);
			indirizzo.setCap(cap);
			indirizzoService.createIndirizzo(indirizzo);
		}
	}

	public Indirizzo getIndirizzo() {
		return this.indirizzo;
	}
}
