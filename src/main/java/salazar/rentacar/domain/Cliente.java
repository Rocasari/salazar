package salazar.rentacar.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	@Column (unique = true)
	private String dniCliente;
    private String nombre;
    private String direccion;
    private String telefono;
    
    
    // Relación de muchos a uno con Cliente
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_avalador")
    @JsonIdentityReference(alwaysAsId = true)
    private Cliente avalador;

    //Relación de uno a muchos con Reserva
    @OneToMany(mappedBy = "cliente")
    @JsonManagedReference
    private List<Reserva> reservas = new ArrayList<>();
}
