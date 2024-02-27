package salazar.rentacar.repository;

import org.springframework.data.repository.CrudRepository;

import salazar.rentacar.domain.Reserva;

public interface ReservaRepository extends CrudRepository<Reserva, Long> {

}
