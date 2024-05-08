package puc.br.tpgerenciaconfig.mapper;

import puc.br.tpgerenciaconfig.model.Paciente;
import puc.br.tpgerenciaconfig.model.dto.PacienteEditDTO;
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

    public static void mapper(PacienteEditDTO dto, Paciente paciente){

        paciente.setNome(dto.nome());
        paciente.setSobrenome(dto.sobrenome());
        paciente.setSexo(dto.sexo());
        paciente.setNascimento(dto.nascimento());
        paciente.setAltura(dto.altura());
        paciente.setPeso(dto.peso());
        paciente.setCpf(dto.cpf());
        paciente._calcularIMC();
        paciente._calcularIdade();
    }

}
