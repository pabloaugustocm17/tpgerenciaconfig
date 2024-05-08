package puc.br.tpgerenciaconfig.model.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PacienteEditDTO (
        UUID id,
        String nome,
        String sobrenome,
        char sexo,
        LocalDate nascimento,
        Short altura,
        Double peso,
        String cpf
){ }
