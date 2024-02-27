package salazar.rentacar.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import salazar.rentacar.domain.Cliente;
import salazar.rentacar.services.ClienteService;

@RestController
@RequestMapping("api/salazar/clientes")
public class ClienteController {

	@Autowired
    private ClienteService clienteService;
	
	@GetMapping
    public List<Cliente> getAllAgencias() {
        return clienteService.listarClientes();
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Long id) {
        Optional<Cliente> clienteO = clienteService.buscarXIdCliente(id);
        if (clienteO.isPresent()) {
            Cliente cliente = clienteO.get();
            if (cliente.getAvalador() != null) {
                cliente.getAvalador().setAvalador(null);
                cliente.getAvalador().setReservas(null);
            }
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.notFound().build();
    }
	
	@PostMapping
    public ResponseEntity<?> create(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.grabarCliente(cliente));
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteO = clienteService.buscarXIdCliente(id);
        if (clienteO.isPresent()) {
            Cliente clienteAct = clienteO.get();
            clienteAct.setDniCliente(cliente.getDniCliente());
            clienteAct.setNombre(cliente.getNombre());
            clienteAct.setDireccion(cliente.getDireccion());
            clienteAct.setTelefono(cliente.getTelefono());
            
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(clienteService.grabarCliente(clienteAct));
        }
        return ResponseEntity.notFound().build();
    }
	
	@PutMapping("/asignar-avalador/{idCliente}/{idAvalador}")
	public ResponseEntity<?> asignarAvalador(@PathVariable Long idCliente, @PathVariable Long idAvalador) {
	    try {
	        if (idCliente.equals(idAvalador)) {
	            return ResponseEntity.badRequest().body("Un cliente no puede ser su propio avalador.");
	        }

	        clienteService.asignarAvalador(idCliente, idAvalador);
	        return ResponseEntity.ok("Avalador asignado con éxito.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error al asignar avalador: " + e.getMessage());
	    }
	}

	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Cliente> clienteO = clienteService.buscarXIdCliente(id);
        if (clienteO.isPresent()) {
            clienteService.eliminarCliente(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
