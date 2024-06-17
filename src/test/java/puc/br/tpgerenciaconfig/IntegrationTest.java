package puc.br.tpgerenciaconfig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import puc.br.tpgerenciaconfig.model.Paciente;
import puc.br.tpgerenciaconfig.model.dto.PacienteInsertDTO;
import puc.br.tpgerenciaconfig.repositories.PacienteRepository;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Test
    void testCreateUser() {

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

        ResponseEntity<Paciente> response = restTemplate.postForEntity(url, paciente, Paciente.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
    }

}
