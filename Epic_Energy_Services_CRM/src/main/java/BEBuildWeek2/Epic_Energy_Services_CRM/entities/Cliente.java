package BEBuildWeek2.Epic_Energy_Services_CRM.entities;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import BEBuildWeek2.Epic_Energy_Services_CRM.utils.TipoCliente;
import jakarta.persistence.CascadeType;
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
	private String telefono;

	@OneToMany(mappedBy = "idCliente")
	private List<Fattura> fatture;

	@JoinColumn(name = "id_utente")
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Utente idUtente;

	@JoinColumn(name = "nome_utente")
	private String nome;
	@JoinColumn(name = "email_utente")
	private String emailUtente;
	@JoinColumn(name = "cognome_utente")
	private String cognome;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "indirizzo_id")
	private Indirizzo indirizzo;

	public Cliente(String partitaIva, String ragioneSociale, String emailCliente, Date dataInserimento,
			Date dataUltimoContatto, Double fatturatoAnnuale, String pec, String telefono, Utente idUtente,
			Indirizzo indirizzo) {
		this.partitaIva = partitaIva;
		this.ragioneSociale = ragioneSociale;
		this.emailCliente = emailCliente;
		this.dataInserimento = dataInserimento;
		this.dataUltimoContatto = dataUltimoContatto;
		this.fatturatoAnnuale = fatturatoAnnuale;
		this.pec = pec;
		this.telefono = telefono;
		this.idUtente = idUtente;
		this.indirizzo = indirizzo;
	}

}
