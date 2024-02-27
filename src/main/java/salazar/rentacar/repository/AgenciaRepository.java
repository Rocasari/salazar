package salazar.rentacar.repository;

import org.springframework.data.repository.CrudRepository;

import salazar.rentacar.domain.Agencia;

public interface AgenciaRepository extends CrudRepository<Agencia, Long> {

}
