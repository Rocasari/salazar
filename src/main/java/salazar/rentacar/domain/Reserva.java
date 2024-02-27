package salazar.rentacar.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idReserva")

public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReserva;
	
    @Temporal(TemporalType.DATE)
    private Date fechaIni;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    
    private Double precioTotal;
    private Boolean autoEntregado;
    
    //Relación muchos a uno con Agencia
    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Agencia agencia;
    
  //Relación muchos a uno con Cliente
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_cliente")
    @JsonIdentityReference(alwaysAsId = true)
    private Cliente cliente;

    //Relación de "muchos a muchos" con Auto
    @ManyToMany
    @JoinTable(name = "auto_reserva",
				joinColumns = @JoinColumn(name = "idReserva"),
				inverseJoinColumns = @JoinColumn(name = "idAuto"))
    @JsonIdentityReference(alwaysAsId = true)
    private List<Auto> autos = new ArrayList<Auto>();


}
