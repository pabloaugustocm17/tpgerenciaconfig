package puc.br.tpgerenciaconfig;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import puc.br.tpgerenciaconfig.mapper.PacienteMapper;
import puc.br.tpgerenciaconfig.model.Paciente;
import puc.br.tpgerenciaconfig.model.dto.PacienteEditDTO;
import puc.br.tpgerenciaconfig.model.dto.PacienteInsertDTO;
import puc.br.tpgerenciaconfig.repositories.PacienteRepository;
import puc.br.tpgerenciaconfig.services.PacienteService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UnitTest {

    @Mock
    private PacienteRepository REPOSITORY;

    @InjectMocks
    private PacienteService SERVICE;

    @Test
    public void _retornaTodosPacientesTest(){

        List<Paciente> pacientes = new ArrayList<>();
        pacientes.add(new Paciente());

        when(REPOSITORY.findAll()).thenReturn(pacientes);

        ResponseEntity<?> response_entity = SERVICE._retornaTodosPacientes();

        assertEquals(HttpStatus.OK, response_entity.getStatusCode());
        assertInstanceOf(List.class, response_entity.getBody());
        assertEquals(pacientes, response_entity.getBody());
    }

    @Test
    public void _retornaTodosPacientesTestError(){

        List<Paciente> pacientes = new ArrayList<>();

        when(REPOSITORY.findAll()).thenReturn(pacientes);

        ResponseEntity<?> response_entity = SERVICE._retornaTodosPacientes();

        assertEquals(HttpStatus.NOT_FOUND, response_entity.getStatusCode());
    }

    @Test
    public void _retornaPacientePorIdTest(){

        Optional<Paciente> optional_paciente = Optional.of(new Paciente());

        UUID random_id = UUID.randomUUID();

        when(REPOSITORY.findById(random_id)).thenReturn(optional_paciente);

        ResponseEntity<?> response_entity = SERVICE._retornaPacientePorId(random_id);

        assertEquals(HttpStatus.OK, response_entity.getStatusCode());
        assertEquals(optional_paciente.get(), response_entity.getBody());
        assertInstanceOf(Paciente.class, response_entity.getBody());
    }

    @Test
    public void _retornaPacientePorIdTestError(){

        Optional<Paciente> optional_paciente = Optional.empty();

        UUID random_id = UUID.randomUUID();

        when(REPOSITORY.findById(random_id)).thenReturn(optional_paciente);

        ResponseEntity<?> response_entity = SERVICE._retornaPacientePorId(random_id);

        assertEquals(HttpStatus.NOT_FOUND, response_entity.getStatusCode());
    }

    @Test
    public void _criaPacienteTest(){

        PacienteInsertDTO dto = new PacienteInsertDTO(
                "Pablo",
                "Magalhães",
                'M',
                LocalDate.now(),
                (short) 169,
                65.0,
                "15653205656"
        );

        Paciente paciente_inserir = PacienteMapper.mapper(dto);

        when(REPOSITORY._retornaPacientePorCPF(dto.cpf())).thenReturn(Optional.empty());
        when(REPOSITORY.save(paciente_inserir)).thenReturn(paciente_inserir);

        ResponseEntity<?> response_entity = SERVICE._criaPaciente(dto);

        assertEquals(HttpStatus.OK, response_entity.getStatusCode());
    }

    @Test
    public void _criaPacienteTestError(){

        PacienteInsertDTO dto = new PacienteInsertDTO(
                "Pablo",
                "Magalhães",
                'M',
                LocalDate.now(),
                (short) 169,
                65.0,
                "15653205656"
        );

        Paciente paciente_inserir = PacienteMapper.mapper(dto);

        when(REPOSITORY._retornaPacientePorCPF(dto.cpf())).thenReturn(Optional.of(paciente_inserir));

        ResponseEntity<?> response_entity = SERVICE._criaPaciente(dto);

        assertEquals(HttpStatus.BAD_REQUEST, response_entity.getStatusCode());
    }

    @Test
    public void _excluiPacienteTest(){

        UUID id = UUID.randomUUID();

        Paciente paciente = new Paciente();

        when(REPOSITORY.findById(id)).thenReturn(Optional.of(paciente));

        ResponseEntity<?> response_entity = SERVICE._excluiPaciente(id);

        assertEquals(HttpStatus.OK, response_entity.getStatusCode());
    }

    @Test
    public void _excluiPacienteTestError(){

        UUID id = UUID.randomUUID();

        when(REPOSITORY.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<?> response_entity = SERVICE._excluiPaciente(id);

        assertEquals(HttpStatus.NOT_FOUND, response_entity.getStatusCode());
    }

    @Test
    public void _editaPacienteTest(){

        PacienteEditDTO dto = new PacienteEditDTO(
                UUID.randomUUID(),
                "Pablo",
                "Magalhães",
                'M',
                LocalDate.now(),
                (short) 169,
                65.0,
                "15653205656"
        );

        Paciente paciente = new Paciente();

        PacienteMapper.mapper(dto, paciente);

        when(REPOSITORY.findById(dto.id())).thenReturn(Optional.of(paciente));
        when(REPOSITORY.saveAndFlush(paciente)).thenReturn(paciente);

        ResponseEntity<?> response_entity = SERVICE._editaPaciente(dto);

        assertEquals(HttpStatus.ACCEPTED, response_entity.getStatusCode());
    }

    @Test
    public void _editaPacienteTestError(){
        PacienteEditDTO dto = new PacienteEditDTO(
                UUID.randomUUID(),
                "Pablo",
                "Magalhães",
                'M',
                LocalDate.now(),
                (short) 169,
                65.0,
                "15653205656"
        );

        when(REPOSITORY.findById(dto.id())).thenReturn(Optional.empty());

        ResponseEntity<?> response_entity = SERVICE._editaPaciente(dto);

        assertEquals(HttpStatus.NOT_FOUND, response_entity.getStatusCode());
    }
}
