package BEBuildWeek2.Epic_Energy_Services_CRM.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "comune")
@Data
public class Comune {
	
	@Column(name ="codice_provincia")
	private int codiceProvincia;
	@Column(name ="progressivo_comune")
	private int progressivoComune;
	@Id
	@Column(name ="nome")
	private String nome;
	
	
	@ManyToOne
	private Provincia siglaProvincia;
	
}
