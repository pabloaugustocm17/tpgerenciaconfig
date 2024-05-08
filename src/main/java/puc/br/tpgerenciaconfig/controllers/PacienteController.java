package puc.br.tpgerenciaconfig.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import puc.br.tpgerenciaconfig.model.dto.PacienteInsertDTO;
import puc.br.tpgerenciaconfig.model.response.ResponseBuilder;
import puc.br.tpgerenciaconfig.services.PacienteService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/Paciente/")
public class PacienteController {

    /* Injeção de dependência */

    private final PacienteService SERVICE;

    /* Construtor */

    public PacienteController(PacienteService service) {
        this.SERVICE = service;
    }

    /* Endpoints */

    @PostMapping
    public ResponseEntity<?> _criaPaciente(
            @RequestBody PacienteInsertDTO dto
    ){

        if(dto == null)
            return ResponseBuilder._criaResposta400(null);

        return SERVICE._criaPaciente(dto);
    }

    @PutMapping
    public ResponseEntity<?> _editaPaciente(){
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> _excluiPaciente(){
        return null;
    }

    @GetMapping("all")
    public ResponseEntity<?> _retornaTodosPacientes(){
        return SERVICE._retornaTodosPacientes();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> _retornaPacientePorId(
            @PathVariable UUID id
    ){

        if(id == null)
            return ResponseBuilder._criaResposta400(null);

        return SERVICE._retornaPacientePorId(id);
    }
}
