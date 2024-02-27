package salazar.rentacar.services;

import java.util.List;
import java.util.Optional;

import salazar.rentacar.domain.Cliente;

public interface ClienteService {
	List<Cliente> listarClientes();
	Optional<Cliente> buscarXIdCliente(Long idCliente);
	Cliente grabarCliente (Cliente cliente);
	void eliminarCliente(Long idCliente);
	Cliente asignarAvalador(Long idCliente, Long idAvalador);
}
