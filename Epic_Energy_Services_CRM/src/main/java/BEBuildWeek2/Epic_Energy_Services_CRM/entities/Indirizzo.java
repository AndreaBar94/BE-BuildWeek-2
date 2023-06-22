package BEBuildWeek2.Epic_Energy_Services_CRM.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Indirizzo {
	@Id
	@GeneratedValue
	private UUID idIndirizzo;
	private String via;
	private Integer civico;
	private Integer cap;

	private String localita;

	@ManyToOne
	@JoinColumn(name = "Comune_ID")
	private Comune comune;

	public Indirizzo(String via, Integer civico, Integer cap, String localita, Comune comune) {
		super();
		this.via = via;
		this.civico = civico;
		this.cap = cap;
		this.localita = localita;
		this.comune = comune;
	}

}
