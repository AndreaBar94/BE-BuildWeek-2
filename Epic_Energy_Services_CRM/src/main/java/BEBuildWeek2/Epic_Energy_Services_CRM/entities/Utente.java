package BEBuildWeek2.Epic_Energy_Services_CRM.entities;

import java.util.UUID;

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
public class Utente {
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
}
