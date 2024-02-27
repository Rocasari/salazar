package salazar.rentacar.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import salazar.rentacar.domain.Agencia;
import salazar.rentacar.services.AgenciaService;

@RestController
@RequestMapping("api/salazar/agencias")
public class AgenciaController {

	@Autowired
    private AgenciaService agenciaService;
	
	@GetMapping
    public List<Agencia> getAllAgencias() {
        return agenciaService.listarAgencias();
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getAgenciaById(@PathVariable Long id) {
        Optional<Agencia> agenciaO = agenciaService.buscarXIdAgencia(id);
        if (agenciaO.isPresent()) {
            return ResponseEntity.ok(agenciaO.get());
        }
        return ResponseEntity.notFound().build();
    }
	
	@PostMapping
    public ResponseEntity<?> create(@RequestBody Agencia agencia) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(agenciaService.grabarAgencia(agencia));
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Agencia agencia) {
        Optional<Agencia> agenciaO = agenciaService.buscarXIdAgencia(id);
        if (agenciaO.isPresent()) {
            Agencia agenciaAct = agenciaO.get();
            agenciaAct.setNombre(agencia.getNombre());
            agenciaAct.setUbicacion(agencia.getUbicacion());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(agenciaService.grabarAgencia(agenciaAct));
        }
        return ResponseEntity.notFound().build();
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Agencia> agenciaO = agenciaService.buscarXIdAgencia(id);
        if (agenciaO.isPresent()){
            agenciaService.eliminarAgencia(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
