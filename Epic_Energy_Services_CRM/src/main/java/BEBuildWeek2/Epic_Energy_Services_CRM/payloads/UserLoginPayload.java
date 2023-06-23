package BEBuildWeek2.Epic_Energy_Services_CRM.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserLoginPayload {
	@Email(message = "Invalid email format")
	private String emailUtente;
	
	@Pattern(regexp = "^(?=.[0-9])(?=.[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$" , 
			message = "Password must be at least 8 characters long and contain at least one digit, one letter, and one special character")
	private String password;
}
