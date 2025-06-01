package utfpr.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import utfpr.backend.model.Indicador;

import java.util.List;
import java.util.UUID;

public interface IndicadorRepository extends JpaRepository<Indicador, UUID> {
    /**
     * Retorna todos os Indicador cujo campus.idCampus seja igual a campusId.
     * Internamente, gera algo como:
     * SELECT * FROM tb_indicador WHERE id_campus = :campusId
     */
    List<Indicador> findByCampus_IdCampus(UUID campusId);
}
