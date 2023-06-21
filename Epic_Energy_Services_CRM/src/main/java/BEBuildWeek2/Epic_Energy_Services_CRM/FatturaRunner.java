package BEBuildWeek2.Epic_Energy_Services_CRM;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Fattura;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.FatturaPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.services.FatturaService;
import BEBuildWeek2.Epic_Energy_Services_CRM.utils.StatoFattura;

@Component
public class FatturaRunner implements CommandLineRunner{
	
	@Autowired
	FatturaService fatturaService;
	
	@Override
	public void run(String... args) throws Exception {
		
		Faker faker = new Faker(new Locale("it"));
		for(int i = 0; i < 20; i++) {
			int numeroFattura = faker.number().randomDigit();
			Date data = getDateInPast(100);
			int anno = getYearFromDate(data);
			BigDecimal importo = new BigDecimal(faker.number().randomDouble(2, 0, 1000));
			StatoFattura state = StatoFattura.values()[faker.number().numberBetween(0, StatoFattura.values().length)];
			
			FatturaPayload fattura = new FatturaPayload();
			fattura.setNumeroFattura(numeroFattura);
			fattura.setAnno(anno);
			fattura.setData(data);
			fattura.setImporto(importo);
			fattura.setState(state);
			
			//fatturaService.createFattura(fattura);
			
		}
	}
	
	private Date getDateInPast(int days) {
        Date currentDate = new Date();
        long pastMillis = currentDate.getTime() - TimeUnit.DAYS.toMillis(days);
        return new Date(pastMillis);
    }
	
	private int getYearFromDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
}