package BEBuildWeek2.Epic_Energy_Services_CRM.payloads;

import lombok.Data;

@Data
public class UserRegistrationPayload {
	String nickname, name, surname, emailUtente, password;
}
