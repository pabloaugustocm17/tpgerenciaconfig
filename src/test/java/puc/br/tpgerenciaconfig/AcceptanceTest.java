package puc.br.tpgerenciaconfig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import puc.br.tpgerenciaconfig.model.Paciente;
import puc.br.tpgerenciaconfig.model.dto.PacienteInsertDTO;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void _controllerTeste() {


        PacienteInsertDTO paciente = new PacienteInsertDTO(
                "Pablo",
                "Teste",
                'M',
                LocalDate.now(),
                (short)1.66,
                64.0,
                "12345678911"
        );

        String url = "http://localhost:" + port + "/api/v1/Paciente/";
        String url_2 = "http://localhost:" + port + "/api/v1/Paciente/all";

        ResponseEntity<Paciente> response = restTemplate.postForEntity(url, paciente, Paciente.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

        ResponseEntity<List> getResponse = restTemplate.getForEntity(url_2, List.class);

        assertThat(getResponse.getStatusCode().is2xxSuccessful()).isTrue();

        List<Paciente> users = getResponse.getBody();

        assertThat(users).isNotEmpty();
    }

}
