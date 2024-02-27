package salazar.rentacar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import salazar.rentacar.domain.Cliente;
import salazar.rentacar.repository.ClienteRepository;

@Service
public class ClienteServiceImp implements ClienteService {

	@Autowired
	private ClienteRepository clienteRep;

    @Override
    public List<Cliente> listarClientes() {
        return (List<Cliente>) clienteRep.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarXIdCliente(Long idCliente) {
        return clienteRep.findById(idCliente);
    }

    @Override
    public Cliente grabarCliente(Cliente cliente) {
        return clienteRep.save(cliente);
    }

    @Override
    public void eliminarCliente(Long idCliente) {
        clienteRep.deleteById(idCliente);
    }
    
    @Override
    public Cliente asignarAvalador(Long idCliente, Long idAvalador) {
        // Buscamos el cliente y el avalador en la base de datos
        Optional<Cliente> clienteOptional = clienteRep.findById(idCliente);
        Optional<Cliente> avaladorOptional = clienteRep.findById(idAvalador);

        // Verificamos si ambos clientes existen
        if (clienteOptional.isPresent() && avaladorOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            Cliente avalador = avaladorOptional.get();

            // Validamos que el cliente no se esté asignando a sí mismo
            if (cliente.equals(avalador)) {
                throw new IllegalArgumentException("Un cliente no puede ser su propio avalador.");
            }

            cliente.setAvalador(avalador);
            return clienteRep.save(cliente);
        } else {
            throw new EntityNotFoundException("No se encontró uno o ambos clientes con los IDs proporcionados.");
        }
    }



}
