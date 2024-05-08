package puc.br.tpgerenciaconfig.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import puc.br.tpgerenciaconfig.mapper.PacienteMapper;
import puc.br.tpgerenciaconfig.model.Paciente;
import puc.br.tpgerenciaconfig.model.dto.PacienteInsertDTO;
import puc.br.tpgerenciaconfig.model.response.ResponseBuilder;
import puc.br.tpgerenciaconfig.model.response.ResponseError;
import puc.br.tpgerenciaconfig.model.response.ResponseInsert;
import puc.br.tpgerenciaconfig.repositories.PacienteRepository;
import puc.br.tpgerenciaconfig.utils.Constantes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PacienteService {

    /* Injeção de dependência */

    private final PacienteRepository REPOSITORY;

    /* Construtor */

    public PacienteService(PacienteRepository repository) {
        this.REPOSITORY = repository;
    }

    /* Métodos Públicos */

    public ResponseEntity<?> _retornaTodosPacientes(){

        List<Paciente> pacientes = REPOSITORY.findAll();

        if(pacientes.isEmpty())
            return ResponseBuilder._criaResposta404(null);

        return ResponseBuilder._criaResposta200(pacientes);
    }

    public ResponseEntity<?> _retornaPacientePorId(UUID id){

        Optional<Paciente> paciente = REPOSITORY.findById(id);

        if(paciente.isEmpty())
            return ResponseBuilder._criaResposta404(null);

        return ResponseBuilder._criaResposta200(paciente.get());
    }

    public ResponseEntity<?> _criaPaciente(PacienteInsertDTO dto){

        Optional<Paciente> paciente_comparar = REPOSITORY._retornaPacientePorCPF(dto.cpf());

        if(paciente_comparar.isPresent())
            return ResponseBuilder._criaResposta400(new ResponseError(Constantes.paciente_ja_inserido));

        Paciente paciente_inserir = PacienteMapper.mapper(dto);

        REPOSITORY.save(paciente_inserir);

        return ResponseBuilder._criaResposta201(new ResponseInsert(paciente_inserir.getId(), Constantes.sucesso));
    }
}
