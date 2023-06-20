package BEBuildWeek2.Epic_Energy_Services_CRM.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BEBuildWeek2.Epic_Energy_Services_CRM.entities.Utente;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.AuthenticationSuccessfullPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.UserLoginPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.payloads.UserRegistrationPayload;
import BEBuildWeek2.Epic_Energy_Services_CRM.services.UtenteService;



@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	UtenteService usersService;
	
	@Autowired
	private PasswordEncoder bcrypt;
	
	@PostMapping("/register")
	public ResponseEntity<Utente> register(@RequestBody @Validated UserRegistrationPayload body) {
		body.setPassword(bcrypt.encode(body.getPassword()));

		Utente createdUser = usersService.createUtente(body);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UserLoginPayload body) throws NotFoundException{
		
		//cerco la mail inserita nel login tra quelle degli utenti
		Utente user = usersService.findUtenteByEmail(body.getEmailUtente());
		
		//se la trovo faccio il check sulla password, se non corrisponde lancio errore 401
		//if(!body.getPassword().matches(user.getPassword())) throw new UnauthorizedException("Credenziali non valide");
		String plainPW = body.getPassword();
		String hashedPW = user.getPassword(); 
		
		if (!bcrypt.matches(plainPW, hashedPW))
			throw new UnauthorizedException("Credenziali non valide");
		//se corrisponde creo token
		String token = JWTTools.createToken(user);
		
		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}
	


}