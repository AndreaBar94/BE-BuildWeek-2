package BEBuildWeek2.Epic_Energy_Services_CRM.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "provincia")
@Data
@NoArgsConstructor
public class Provincia {
	
	@Id
	private String sigla;

	private String provincia;

	private String regione;
	
	@OneToMany(mappedBy = "siglaProvincia")
	private List<Comune> comune;

	
}
