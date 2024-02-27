package salazar.rentacar.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Garaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGarage;
	
	@Column(unique = true)
	private String nombre;
	
    private String ubicacion;

    //Relación "uno a muchos" con Auto
    @OneToMany(mappedBy = "garaje")
    @JsonManagedReference
    private List<Auto> autos = new ArrayList<>();
}
