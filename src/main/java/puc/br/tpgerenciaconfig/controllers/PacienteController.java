package puc.br.tpgerenciaconfig.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/Paciente/")
public class PacienteController {

    @PostMapping
    public ResponseEntity<?> _criaPaciente(){

        /*Paciente paciente = new Paciente(
                "Pablo",
                "Magalhães",
                'm',
                LocalDate.now(),
                (short) 1.7,
                67.0,
                "15653205656"
        );*/

        return null;
    }

    @PutMapping
    public ResponseEntity<?> _editaPaciente(){
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> _excluiPaciente(){
        return null;
    }

}
