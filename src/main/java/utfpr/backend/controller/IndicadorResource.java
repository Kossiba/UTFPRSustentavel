package utfpr.backend.controller;

import utfpr.backend.model.Indicador;
import utfpr.backend.services.IndicadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Controlador REST para a entidade Indicador.
 * Exponibiliza endpoints para listar, buscar por ID, criar, atualizar e deletar indicadores.
 */
@RestController
@RequestMapping("/api/indicadores")
public class IndicadorResource {

    private final IndicadorService indicadorService;

    public IndicadorResource(IndicadorService indicadorService) {
        this.indicadorService = indicadorService;
    }

    /**
     * GET /api/indicadores
     * Retorna a lista de todos os indicadores.
     */
    @GetMapping
    public ResponseEntity<List<Indicador>> getAll() {
        List<Indicador> lista = indicadorService.findAll();
        return ResponseEntity.ok(lista);
    }

    /**
     * GET /api/indicadores/{id}
     * Retorna um indicador pelo seu ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Indicador> getById(@PathVariable UUID id) {
        Optional<Indicador> indicadorOpt = indicadorService.findById(id);
        return indicadorOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST /api/indicadores
     * Cria um novo indicador.
     *
     * O JSON de requisição deve conter, no mínimo:
     * {
     *   "campus": { "idCampus": "uuid-do-campus" },
     *   "tipo": "energia",
     *   "descricao": "Consumo médio mensal",
     *   "quantidade": 123.45,
     *   "medida": "kWh",
     *   "dataInicial": "2025-05-01",
     *   "dataFinal": "2025-05-31"
     * }
     */
    @PostMapping
    public ResponseEntity<Indicador> create(@RequestBody Indicador novoIndicador) {
        try {
            Indicador criado = indicadorService.create(novoIndicador);
            return ResponseEntity.status(201).body(criado);
        } catch (IllegalArgumentException ex) {
            // Por exemplo, se o campus não existir
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * PUT /api/indicadores/{id}
     * Atualiza um indicador existente.
     *
     * O JSON de requisição pode conter qualquer campo a ser atualizado:
     * {
     *   "campus": { "idCampus": "novo-uuid-campus" },
     *   "tipo": "carbono",
     *   "descricao": "Emissão de CO2",
     *   "quantidade": 78.9,
     *   "medida": "kg",
     *   "dataInicial": "2025-05-01",
     *   "dataFinal": "2025-05-31"
     * }
     */
    @PutMapping("/{id}")
    public ResponseEntity<Indicador> update(
            @PathVariable UUID id,
            @RequestBody Indicador dadosNovos
    ) {
        try {
            Optional<Indicador> atualizadoOpt = indicadorService.update(id, dadosNovos);
            return atualizadoOpt
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException ex) {
            // Por exemplo, campus não encontrado
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * DELETE /api/indicadores/{id}
     * Remove um indicador pelo seu ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        Optional<Indicador> indicadorOpt = indicadorService.findById(id);
        if (indicadorOpt.isPresent()) {
            indicadorService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
