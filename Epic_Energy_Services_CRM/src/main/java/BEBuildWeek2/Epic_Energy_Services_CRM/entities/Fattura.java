package BEBuildWeek2.Epic_Energy_Services_CRM.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import BEBuildWeek2.Epic_Energy_Services_CRM.utils.StatoFattura;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Fattura {

	@Id
	@GeneratedValue
	private UUID idFattura;
	private int numeroFattura;
	private int anno;
	private Date data;
	private BigDecimal importo;
	private StatoFattura state;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente idCliente;

}
