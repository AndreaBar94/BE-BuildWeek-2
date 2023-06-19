package BEBuildWeek2.Epic_Energy_Services_CRM.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Indirizzo {
	@Id
	@GeneratedValue
	private UUID idIndirizzo;
	private String via;
	private Integer civico;
	private Integer cap;

	@OneToOne
	@JoinColumn(name = "Province")
	private Provincia località;

	@OneToOne
	@JoinColumn(name = "Comuni")
	private Comune comune;

	public Indirizzo(UUID idIndirizzo, String via, Integer civico, Integer cap, Provincia località, Comune comune) {
		super();
		this.idIndirizzo = idIndirizzo;
		this.via = via;
		this.civico = civico;
		this.cap = cap;
		this.località = località;
		this.comune = comune;
	}

}
