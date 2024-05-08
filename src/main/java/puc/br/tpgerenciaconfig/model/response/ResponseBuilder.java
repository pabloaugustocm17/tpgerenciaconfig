package puc.br.tpgerenciaconfig.model.response;

import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    public static ResponseEntity<?> _criaResposta200(Object corpo){
        return ResponseEntity.status(200).body(corpo);
    }

    public static ResponseEntity<?> _criaResposta404(Object corpo){
        return ResponseEntity.status(404).body(corpo);
    }

    public static ResponseEntity<?> _criaResposta400(Object corpo){
        return ResponseEntity.status(400).body(corpo);
    }

    public static ResponseEntity<?> _criaResposta201(Object corpo){
        return ResponseEntity.status(200).body(corpo);
    }
}
