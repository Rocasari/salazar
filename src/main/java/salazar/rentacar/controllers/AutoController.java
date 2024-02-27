package salazar.rentacar.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import salazar.rentacar.domain.Auto;
import salazar.rentacar.services.AutoService;

@RestController
@RequestMapping("api/salazar/autos")
public class AutoController {

    @Autowired
    private AutoService autoService;

    @GetMapping
    public List<Auto> getAllAutos() {
        return autoService.listarAutos();
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<?> getAutoById(@PathVariable Long id) {
        Optional<Auto> autoO = autoService.buscarXIdAuto(id);
        if (autoO.isPresent()) {
            return ResponseEntity.ok(autoO.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Auto auto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(autoService.grabarAuto(auto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Auto auto) {
        Optional<Auto> autoO = autoService.buscarXIdAuto(id);
        if (autoO.isPresent()) {
            Auto autoAct = autoO.get();
            autoAct.setMatricula(auto.getMatricula());
            autoAct.setModelo(auto.getModelo());
            autoAct.setColor(auto.getColor());
            autoAct.setMarca(auto.getMarca());
            autoAct.setCantGasolina(auto.getCantGasolina());
            autoAct.setPrecioAlquiler(auto.getPrecioAlquiler());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(autoService.grabarAuto(autoAct));
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/asignar-auto-garaje/{idAuto}/{idGaraje}")
    public ResponseEntity<?> asignarAutoAGaraje(@PathVariable Long idGaraje, @PathVariable Long idAuto) {
        try {
        	autoService.asignarAutoAGaraje(idGaraje, idAuto);
            return ResponseEntity.ok("Auto asignado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al asignar auto: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Auto> autoO = autoService.buscarXIdAuto(id);
        if (autoO.isPresent()) {
            autoService.eliminarAuto(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
