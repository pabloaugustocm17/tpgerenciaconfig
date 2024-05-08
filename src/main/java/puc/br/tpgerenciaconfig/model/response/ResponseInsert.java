package puc.br.tpgerenciaconfig.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseInsert {

    private UUID id;
    private String mensagem;
}
