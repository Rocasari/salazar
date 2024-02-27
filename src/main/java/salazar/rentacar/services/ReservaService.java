package salazar.rentacar.services;

import java.util.List;
import java.util.Optional;

import salazar.rentacar.domain.Reserva;

public interface ReservaService {
	List<Reserva> listarReservas();
	Optional<Reserva> buscarXIdReserva(Long idReserva);
	Reserva grabarReserva (Reserva reserva);
	void eliminarReserva(Long idReserva);
	Reserva asignarAutosReserva(Long idReserva, Long idAuto);
	Reserva asignarClienteReserva(Long idReserva, Long idCliente);
	Reserva asignarAgenciaReserva(Long idReserva, Long idAgencia);
}
