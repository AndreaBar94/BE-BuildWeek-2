package BEBuildWeek2.Epic_Energy_Services_CRM.entities;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import BEBuildWeek2.Epic_Energy_Services_CRM.utils.TipoCliente;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Clienti")
@NoArgsConstructor
public class Cliente {
	@Id
	@GeneratedValue
	private UUID idCliente;
	private String partitaIva;
	
	private String ragioneSociale;
	
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;

	private String emailCliente;
	private Date dataInserimento;
	private Date dataUltimoContatto;
	private Double fatturatoAnnuale;
	private String pec;
	private Integer telefono;

	@OneToMany(mappedBy = "idCliente")
	private List<Fattura> fatture;

	@ManyToOne
	@JoinColumn(name = "utente_email")
	private Utente emailUtente;

	@OneToOne
	@JoinColumn(name = "utente_nome")
	private Utente nome;

	@OneToOne
	@JoinColumn(name = "utente_cognome")
	private Utente cognome;

	@OneToOne
	@JoinColumn(name = "indirizzo_id")
	private Indirizzo indirizzo;

	public Cliente(String ragioneSociale, String partitaIva, TipoCliente tipoCliente, String emailCliente,
			Date dataInserimento, Date dataUltimoContatto, Double fatturatoAnnuale, String pec, Integer telefono, List<Fattura> fatture,
			Utente emailUtente, Utente nome, Utente cognome, Indirizzo indirizzo) {
		super();
		this.ragioneSociale = ragioneSociale;
		this.partitaIva = partitaIva;
		this.tipoCliente = tipoCliente;
		this.emailCliente = emailCliente;
		this.dataInserimento = dataInserimento;
		this.dataUltimoContatto = dataUltimoContatto;
		this.fatturatoAnnuale = fatturatoAnnuale;
		this.pec = pec;
		this.telefono = telefono;
		this.fatture = fatture;
		this.emailUtente = emailUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
	}

}
