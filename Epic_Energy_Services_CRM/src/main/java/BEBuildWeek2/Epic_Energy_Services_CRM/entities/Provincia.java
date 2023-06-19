package BEBuildWeek2.Epic_Energy_Services_CRM.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Data
public class Provincia {
	
	@Id
	private String sigla;

	private String provincia;

	private String regione;
	
	@OneToMany
	private List<Comune> comune;

	public Provincia(String sigla, String provincia, String regione) {
		super();
		this.sigla = sigla;
		this.provincia = provincia;
		this.regione = regione;
	}
	
	
	
}
