package BEBuildWeek2.Epic_Energy_Services_CRM.payloads;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Comune;
import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Provincia;
import lombok.Data;

@Data
public class IndirizzoPayload {
	String via;
	Integer civico, cap;
	Comune comune;
	Provincia localita;
}
