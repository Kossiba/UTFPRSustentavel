package utfpr.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.backend.model.Indicador;
import java.util.UUID;

public interface IndicadorRepository extends JpaRepository<Indicador, UUID> {
    // herda métodos como findAll(), findById(), save(), delete()...
}
