package salazar.rentacar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import salazar.rentacar.domain.Auto;
import salazar.rentacar.domain.Garaje;
import salazar.rentacar.repository.AutoRepository;
import salazar.rentacar.repository.GarajeRepository;

@Service
public class AutoServiceImp implements AutoService {

    @Autowired
    private AutoRepository autoRep;
    
    @Autowired
    private GarajeRepository garajeRep;

    @Override
    public List<Auto> listarAutos() {
        return (List<Auto>) autoRep.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Auto> buscarXIdAuto(Long idAuto) {
        return autoRep.findById(idAuto);
    }

    @Override
    public Auto grabarAuto(Auto auto) {
        return autoRep.save(auto);
    }

    @Override
    public void eliminarAuto(Long idAuto) {
        autoRep.deleteById(idAuto);
    }
    
    @Override
    public Auto asignarAutoAGaraje(Long idGaraje, Long idAuto) {
        Optional<Garaje> garajeOptional = garajeRep.findById(idGaraje);
        Optional<Auto> autoOptional = autoRep.findById(idAuto);

        if (garajeOptional.isPresent() && autoOptional.isPresent()) {
            Garaje garaje = garajeOptional.get();
            Auto auto = autoOptional.get();

            // Asignar el auto al garaje
            auto.setGaraje(garaje);

            // Guardar los cambios en el garaje
            return autoRep.save(auto);
        } else {
            throw new EntityNotFoundException("No se encontró uno o ambos IDs proporcionados.");
        }
    }
}
