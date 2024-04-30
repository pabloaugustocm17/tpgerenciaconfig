package puc.br.tpgerenciaconfig.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import puc.br.tpgerenciaconfig.model.Paciente;

import java.util.UUID;

@Repository
public interface PacienteRepository  extends JpaRepository<Paciente, UUID> {
}
