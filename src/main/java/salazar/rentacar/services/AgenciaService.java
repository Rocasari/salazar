package salazar.rentacar.services;

import java.util.List;
import java.util.Optional;

import salazar.rentacar.domain.Agencia;

public interface AgenciaService {
	List<Agencia> listarAgencias();
	Optional<Agencia> buscarXIdAgencia(Long idAgencia);
	Agencia grabarAgencia (Agencia agencia);
	void eliminarAgencia(Long idAgencia);
}
