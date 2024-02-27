package salazar.rentacar.repository;

import org.springframework.data.repository.CrudRepository;

import salazar.rentacar.domain.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
