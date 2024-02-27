package salazar.rentacar.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import salazar.rentacar.domain.Garaje;
import salazar.rentacar.services.GarajeService;

@RestController
@RequestMapping("api/salazar/garajes")
public class GarajeController {

    @Autowired
    private GarajeService garajeService;

    @GetMapping
    public List<Garaje> getAllGarajes() {
        return garajeService.listarGarajes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGarajeById(@PathVariable Long id) {
        Optional<Garaje> garajeO = garajeService.buscarXIdGaraje(id);
        if (garajeO.isPresent()) {
            return ResponseEntity.ok(garajeO.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Garaje garaje) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(garajeService.grabarGaraje(garaje));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Garaje garaje) {
        Optional<Garaje> garajeO = garajeService.buscarXIdGaraje(id);
        if (garajeO.isPresent()) {
            Garaje garajeAct = garajeO.get();
            garajeAct.setNombre(garaje.getNombre());
            garajeAct.setUbicacion(garaje.getUbicacion());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(garajeService.grabarGaraje(garajeAct));
        }
        return ResponseEntity.notFound().build();
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Garaje> garajeO = garajeService.buscarXIdGaraje(id);
        if (garajeO.isPresent()) {
            garajeService.eliminarGaraje(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

