package puc.br.tpgerenciaconfig.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import puc.br.tpgerenciaconfig.mapper.PacienteMapper;
import puc.br.tpgerenciaconfig.model.Paciente;
import puc.br.tpgerenciaconfig.model.dto.PacienteEditDTO;
import puc.br.tpgerenciaconfig.model.dto.PacienteInsertDTO;
import puc.br.tpgerenciaconfig.model.response.ResponseBuilder;
import puc.br.tpgerenciaconfig.model.response.ResponseGeneric;
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
            return ResponseBuilder._criaResposta400(new ResponseGeneric(Constantes.paciente_ja_inserido));

        Paciente paciente_inserir = PacienteMapper.mapper(dto);

        REPOSITORY.save(paciente_inserir);

        return ResponseBuilder._criaResposta201(new ResponseInsert(paciente_inserir.getId(), Constantes.sucesso));
    }

    public ResponseEntity<?> _excluiPaciente(UUID id){

        Optional<Paciente> paciente_procurar = REPOSITORY.findById(id);

        if(paciente_procurar.isEmpty())
            return ResponseBuilder._criaResposta404(null);

        Paciente paciente = paciente_procurar.get();

        REPOSITORY.delete(paciente);

        return ResponseBuilder._criaResposta200(new ResponseGeneric(Constantes.paciente_excluido));
    }

    public ResponseEntity<?> _editaPaciente(PacienteEditDTO dto){

        Optional<Paciente> paciente_procurar = REPOSITORY.findById(dto.id());

        if(paciente_procurar.isEmpty())
            return ResponseBuilder._criaResposta404(null);

        Paciente paciente_utilizar = paciente_procurar.get();

        PacienteMapper.mapper(dto, paciente_utilizar);

        REPOSITORY.saveAndFlush(paciente_utilizar);

        return ResponseBuilder._criaResposta202(null);
    }
}
