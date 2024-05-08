package puc.br.tpgerenciaconfig.model.dto;

import java.time.LocalDate;

public record PacienteInsertDTO (
        String nome,
        String sobrenome,
        char sexo,
        LocalDate nascimento,
        Short altura,
        Double peso,
        String cpf
){ }
