package salazar.rentacar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import salazar.rentacar.domain.Agencia;
import salazar.rentacar.domain.Auto;
import salazar.rentacar.domain.Cliente;
import salazar.rentacar.domain.Reserva;
import salazar.rentacar.repository.AgenciaRepository;
import salazar.rentacar.repository.AutoRepository;
import salazar.rentacar.repository.ClienteRepository;
import salazar.rentacar.repository.ReservaRepository;

@Service
public class ReservaServiceImp implements ReservaService {

	@Autowired
	private ReservaRepository reservaRep;
	
	@Autowired
	private AutoRepository autoRep;
	
	@Autowired
	private ClienteRepository clienteRep;
	
	@Autowired
	private AgenciaRepository agenciaRep;

    @Override
    public List<Reserva> listarReservas() {
        return (List<Reserva>) reservaRep.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reserva> buscarXIdReserva(Long idReserva) {
        return reservaRep.findById(idReserva);
    }

    @Override
    public Reserva grabarReserva(Reserva reserva) {
        return reservaRep.save(reserva);
    }

    @Override
    public void eliminarReserva(Long idReserva) {
        reservaRep.deleteById(idReserva);
    }
    
    @Override

    public Reserva asignarAutosReserva(Long idReserva, Long idAuto) {
        Optional<Reserva> reservaOptional = reservaRep.findById(idReserva);
        Optional<Auto> autoOptional = autoRep.findById(idAuto);

        if (reservaOptional.isPresent() && autoOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            Auto auto = autoOptional.get();

            // Asignar el auto a la reserva
            reserva.getAutos().add(auto);

            // Guardar los cambios en la reserva
            return reservaRep.save(reserva);
        } else {
            throw new EntityNotFoundException("No se encontró uno o ambos IDs proporcionados.");
        }
    }

    @Override

    public Reserva asignarClienteReserva(Long idReserva, Long idCliente) {
        Optional<Reserva> reservaOptional = reservaRep.findById(idReserva);
        Optional<Cliente> clienteOptional = clienteRep.findById(idCliente);

        if (reservaOptional.isPresent() && clienteOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            Cliente cliente = clienteOptional.get();

            // Asignar el cliente a la reserva
            reserva.setCliente(cliente);

            // Guardar los cambios en la reserva
            return reservaRep.save(reserva);
        } else {
            throw new EntityNotFoundException("No se encontró uno o ambos IDs proporcionados.");
        }
    }

    @Override

    public Reserva asignarAgenciaReserva(Long idReserva, Long idAgencia) {
        Optional<Reserva> reservaOptional = reservaRep.findById(idReserva);
        Optional<Agencia> agenciaOptional = agenciaRep.findById(idAgencia);

        if (reservaOptional.isPresent() && agenciaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            Agencia agencia = agenciaOptional.get();

            // Asignar la agencia a la reserva
            reserva.setAgencia(agencia);

            // Guardar los cambios en la reserva
            return reservaRep.save(reserva);
        } else {
            throw new EntityNotFoundException("No se encontró uno o ambos IDs proporcionados.");
        }
    }

}
