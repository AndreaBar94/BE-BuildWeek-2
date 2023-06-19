package BEBuildWeek2.Epic_Energy_Services_CRM.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Comune {
	
	@Id
	private String nome;
	
	@ManyToOne
	private Provincia siglaProvincia;

	public Comune(String nome) {
		super();
		this.nome = nome;
	}
	
	
	
}
