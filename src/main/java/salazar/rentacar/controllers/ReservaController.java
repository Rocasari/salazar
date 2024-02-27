package salazar.rentacar.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;
import salazar.rentacar.domain.Reserva;
import salazar.rentacar.services.ReservaService;

@RestController
@RequestMapping("api/salazar/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> getAllReservas() {
        return reservaService.listarReservas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservaById(@PathVariable Long id) {
        Optional<Reserva> reservaO = reservaService.buscarXIdReserva(id);
        if (reservaO.isPresent()) {
            return ResponseEntity.ok(reservaO.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Reserva reserva) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaService.grabarReserva(reserva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Reserva reserva) {
        Optional<Reserva> reservaO = reservaService.buscarXIdReserva(id);
        if (reservaO.isPresent()) {
            Reserva reservaAct = reservaO.get();
            reservaAct.setFechaIni(reserva.getFechaIni());
            reservaAct.setFechaFin(reserva.getFechaFin());
            reservaAct.setPrecioTotal(reserva.getPrecioTotal());
            reservaAct.setAutoEntregado(reserva.getAutoEntregado());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(reservaService.grabarReserva(reservaAct));
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/asignar-auto-reserva/{idReserva}/{idAuto}")
    public ResponseEntity<?> asignarAutosReserva(@PathVariable Long idReserva, @PathVariable Long idAuto) {
        try {
            Reserva reserva = reservaService.asignarAutosReserva(idReserva, idAuto);
            return ResponseEntity.ok(reserva);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró uno o ambos IDs proporcionados.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al asignar autos a la reserva: " + e.getMessage());
        }
    }

    @PutMapping("/asignar-cliente-reserva/{idReserva}/{idCliente}")
    public ResponseEntity<?> asignarClienteReserva(@PathVariable Long idReserva, @PathVariable Long idCliente) {
        try {
            Reserva reserva = reservaService.asignarClienteReserva(idReserva, idCliente);
            return ResponseEntity.ok(reserva);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró uno o ambos IDs proporcionados.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al asignar cliente a la reserva: " + e.getMessage());
        }
    }

    @PutMapping("/asignar-agencia-reserva/{idReserva}/{idAgencia}")
    public ResponseEntity<?> asignarAgenciaReserva(@PathVariable Long idReserva, @PathVariable Long idAgencia) {
        try {
            Reserva reserva = reservaService.asignarAgenciaReserva(idReserva, idAgencia);
            return ResponseEntity.ok(reserva);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró uno o ambos IDs proporcionados.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al asignar agencia a la reserva: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Reserva> reservaO = reservaService.buscarXIdReserva(id);
        if (reservaO.isPresent()) {
            reservaService.eliminarReserva(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

