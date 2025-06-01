package utfpr.backend.services;

import utfpr.backend.model.Indicador;
import utfpr.backend.model.Campus;
import utfpr.backend.dto.IndicadorCreateRequest;
import utfpr.backend.repository.IndicadorRepository;
import utfpr.backend.repository.CampusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IndicadorService {

    private final IndicadorRepository indicadorRepository;
    private final CampusRepository campusRepository;

    public IndicadorService(IndicadorRepository indicadorRepository,
                            CampusRepository campusRepository) {
        this.indicadorRepository = indicadorRepository;
        this.campusRepository = campusRepository;
    }

    /** Busca todos os indicadores. */
    public List<Indicador> findAll() {
        return indicadorRepository.findAll();
    }

    /** Busca um indicador por ID. */
    public Optional<Indicador> findById(UUID id) {
        return indicadorRepository.findById(id);
    }

    /**
     * Retorna todos os Indicador de um campus específico.
     * @param campusId UUID do campus para filtrar
     * @return Lista de Indicador cujo campus.idCampus = campusId
     * @throws IllegalArgumentException se o campusId não existir no BD
     */
    public List<Indicador> findByCampusId(UUID campusId) {
        // (opcional) Primeiro checa se esse campus existe:
        campusRepository.findById(campusId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Campus não encontrado com ID: " + campusId
            ));

        // Depois retorna todos os indicadores daquele campus:
        return indicadorRepository.findByCampus_IdCampus(campusId);
    }

    /**
     * Cria um novo Indicador a partir do DTO {@link IndicadorCreateRequest}.
     */
    public Indicador createFromRequest(IndicadorCreateRequest req) {
        Campus campusExistente = campusRepository
                .findById(req.getIdCampus())
                .orElseThrow(() -> new IllegalArgumentException(
                    "Campus não encontrado com ID: " + req.getIdCampus()));

        Indicador ind = new Indicador();
        ind.setCampus(campusExistente);
        ind.setTipo(req.getTipo());
        ind.setDescricao(req.getDescricao());
        ind.setQuantidade(req.getQuantidade());
        ind.setMedida(req.getMedida());
        ind.setDataInicial(req.getDataInicial());
        ind.setDataFinal(req.getDataFinal());
        // dataAtualizacao será setada no @PrePersist da entidade

        return indicadorRepository.save(ind);
    }

    /**
     * Atualiza um indicador existente.
     */
    public Optional<Indicador> update(UUID id, Indicador dadosNovos) {
        return indicadorRepository.findById(id)
            .map(indicadorExistente -> {
                Campus novoCampus = dadosNovos.getCampus();
                if (novoCampus != null) {
                    UUID novoCampusId = novoCampus.getIdCampus();
                    Campus campusExistente = campusRepository.findById(novoCampusId)
                        .orElseThrow(() -> new IllegalArgumentException(
                            "Campus não encontrado com ID: " + novoCampusId));
                    indicadorExistente.setCampus(campusExistente);
                }

                indicadorExistente.setTipo(dadosNovos.getTipo());
                indicadorExistente.setDescricao(dadosNovos.getDescricao());
                indicadorExistente.setQuantidade(dadosNovos.getQuantidade());
                indicadorExistente.setMedida(dadosNovos.getMedida());
                indicadorExistente.setDataInicial(dadosNovos.getDataInicial());
                indicadorExistente.setDataFinal(dadosNovos.getDataFinal());

                return indicadorRepository.save(indicadorExistente);
            });
    }

    /** Remove um indicador por ID. */
    public void delete(UUID id) {
        indicadorRepository.deleteById(id);
    }
}
