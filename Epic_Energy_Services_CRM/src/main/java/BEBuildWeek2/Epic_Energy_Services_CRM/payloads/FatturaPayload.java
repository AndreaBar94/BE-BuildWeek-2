package BEBuildWeek2.Epic_Energy_Services_CRM.payloads;

import java.math.BigDecimal;
import java.util.Date;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Cliente;
import BEBuildWeek2.Epic_Energy_Services_CRM.utils.StatoFattura;
import lombok.Data;

@Data
public class FatturaPayload {
	
	 int numeroFattura;
	 int anno;
	 Date data;
	 BigDecimal importo;
	 StatoFattura state;
	 Cliente idCliente;
}
