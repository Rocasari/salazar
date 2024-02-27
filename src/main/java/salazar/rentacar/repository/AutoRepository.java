package salazar.rentacar.repository;

import org.springframework.data.repository.CrudRepository;

import salazar.rentacar.domain.Auto;

public interface AutoRepository extends CrudRepository<Auto, Long> {

}
