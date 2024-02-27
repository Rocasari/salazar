package salazar.rentacar.services;

import java.util.List;
import java.util.Optional;

import salazar.rentacar.domain.Garaje;

public interface GarajeService {
	List<Garaje> listarGarajes();
	Optional<Garaje> buscarXIdGaraje(Long idGaraje);
	Garaje grabarGaraje (Garaje garaje);
	void eliminarGaraje(Long idGaraje);
}
