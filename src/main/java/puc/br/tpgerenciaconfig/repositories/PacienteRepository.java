package puc.br.tpgerenciaconfig.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import puc.br.tpgerenciaconfig.model.Paciente;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PacienteRepository  extends JpaRepository<Paciente, UUID> {

    @Query("SELECT P FROM Paciente AS P WHERE P.cpf =:cpf")
    Optional<Paciente> _retornaPacientePorCPF(
            @Param("cpf") String cpf
    );

}
