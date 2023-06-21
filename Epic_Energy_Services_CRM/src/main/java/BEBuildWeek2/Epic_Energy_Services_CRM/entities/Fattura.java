package BEBuildWeek2.Epic_Energy_Services_CRM.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import BEBuildWeek2.Epic_Energy_Services_CRM.utils.StatoFattura;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Fattura {

	@Id
	@GeneratedValue
	private UUID idFattura;
	private int numeroFattura;
	private int anno;
	private Date data;
	private BigDecimal importo;
	@Enumerated(EnumType.STRING)
	private StatoFattura state;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente idCliente;

	public Fattura(int numeroFattura, int anno, Date data, BigDecimal importo, StatoFattura state, Cliente idCliente) {
		super();
		this.numeroFattura = numeroFattura;
		this.anno = anno;
		this.data = data;
		this.importo = importo;
		this.state = state;
		this.idCliente = idCliente;
	}

}
