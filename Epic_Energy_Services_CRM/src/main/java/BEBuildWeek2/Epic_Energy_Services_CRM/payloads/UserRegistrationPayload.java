package BEBuildWeek2.Epic_Energy_Services_CRM.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationPayload {
	@Size(max = 20, message = "Username must be at most 20 characters long")
	private String username;

	@NotEmpty(message = "Name is required")
	private String name;

	@NotEmpty(message = "Surname is required")
	private String surname;

	@Email(message = "Invalid email format")
	private String emailUtente;

	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must be at least 8 characters long and contain at least one digit, one letter, and one special character")
	private String password;
}
