package salazar.rentacar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import salazar.rentacar.domain.Auto;
import salazar.rentacar.domain.Cliente;
import salazar.rentacar.domain.Garaje;
import salazar.rentacar.repository.AutoRepository;
import salazar.rentacar.repository.GarajeRepository;

@Service
public class GarajeServiceImp implements GarajeService {

	@Autowired
	private GarajeRepository garajeRep;
	
	@Autowired
	private AutoRepository autoRep;

    @Override
    public List<Garaje> listarGarajes() {
        return (List<Garaje>) garajeRep.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Garaje> buscarXIdGaraje(Long idGaraje) {
        return garajeRep.findById(idGaraje);
    }

    @Override
    public Garaje grabarGaraje(Garaje garaje) {
        return garajeRep.save(garaje);
    }

    @Override
    public void eliminarGaraje(Long idGaraje) {
        garajeRep.deleteById(idGaraje);
    }

}
