package puc.br.tpgerenciaconfig.services;

import org.springframework.stereotype.Service;
import puc.br.tpgerenciaconfig.repositories.PacienteRepository;

@Service
public class PacienteService {

    private final PacienteRepository REPOSITORY;

    public PacienteService(PacienteRepository repository) {
        this.REPOSITORY = repository;
    }

}
