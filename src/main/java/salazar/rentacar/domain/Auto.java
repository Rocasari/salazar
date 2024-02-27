package salazar.rentacar.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idAuto")

public class Auto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAuto;
	
	@Column(unique = true)
	private String matricula;
	
    private String modelo;
    private String color;
    private String marca;
    private Double cantGasolina;
    private Double precioAlquiler;
    
    
    //Relación de "muchos a uno" con Garaje
    @ManyToOne
    @JoinColumn(name = "id_garaje")
    @JsonBackReference
    private Garaje garaje;

    //Relación "muchos a muchos" con Reserva
    @ManyToMany(mappedBy = "autos")
    private List<Reserva> reservas = new ArrayList<>();

}
