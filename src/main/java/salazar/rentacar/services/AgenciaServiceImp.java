package salazar.rentacar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import salazar.rentacar.domain.Agencia;
import salazar.rentacar.repository.AgenciaRepository;

@Service
public class AgenciaServiceImp implements AgenciaService {

	@Autowired
	private AgenciaRepository agenciaRep;

	@Override
	public List<Agencia> listarAgencias() {
		return (List<Agencia>) agenciaRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Agencia> buscarXIdAgencia(Long idAgencia) {
		return agenciaRep.findById(idAgencia);
	}

	@Override
	public Agencia grabarAgencia(Agencia agencia) {
		return agenciaRep.save(agencia);
	}

	@Override
	public void eliminarAgencia(Long idAgencia) {
		agenciaRep.deleteById(idAgencia);
	}

}
