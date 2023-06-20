package BEBuildWeek2.Epic_Energy_Services_CRM.entities;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import BEBuildWeek2.Epic_Energy_Services_CRM.utils.TipoUtente;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Utenti")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({ "password" })
public class Utente implements UserDetails{
	@Id
	@GeneratedValue
	private UUID idUtente;
	private String username, name, surname, emailUtente, password;

	@Enumerated(EnumType.STRING)
	private TipoUtente ruolo;

	public Utente(String username, String name, String surname, String mail, String password) {
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.emailUtente = mail;
		this.password = password;
		this.ruolo = TipoUtente.USER;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(ruolo.name()));
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.emailUtente;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
