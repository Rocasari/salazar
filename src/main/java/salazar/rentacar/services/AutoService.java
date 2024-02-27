package salazar.rentacar.services;

import java.util.List;
import java.util.Optional;

import salazar.rentacar.domain.Auto;

public interface AutoService {
	List<Auto> listarAutos();
	Optional<Auto> buscarXIdAuto(Long idAuto);
	Auto grabarAuto (Auto auto);
	void eliminarAuto(Long idAuto);
	Auto asignarAutoAGaraje(Long idGaraje, Long idAuto);
}
