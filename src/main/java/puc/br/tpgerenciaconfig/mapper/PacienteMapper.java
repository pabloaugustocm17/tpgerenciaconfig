package puc.br.tpgerenciaconfig.mapper;

import puc.br.tpgerenciaconfig.model.Paciente;
import puc.br.tpgerenciaconfig.model.dto.PacienteInsertDTO;

public class PacienteMapper {

    public static Paciente mapper(PacienteInsertDTO dto){

        return new Paciente(
                dto.nome(),
                dto.sobrenome(),
                dto.sexo(),
                dto.nascimento(),
                dto.altura(),
                dto.peso(),
                dto.cpf()
        );
    }

}
