package utfpr.backend.controller;

import utfpr.backend.model.Indicador;
import utfpr.backend.dto.IndicadorCreateRequest;
import utfpr.backend.services.IndicadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/indicadores")
public class IndicadorResource {

    private final IndicadorService indicadorService;

    public IndicadorResource(IndicadorService indicadorService) {
        this.indicadorService = indicadorService;
    }

    /** GET /api/indicadores — lista todos os indicadores */
    @GetMapping
    public ResponseEntity<List<Indicador>> getAll() {
        return ResponseEntity.ok(indicadorService.findAll());
    }

    /** GET /api/indicadores/{id} — retorna indicador por ID */
    @GetMapping("/{id}")
    public ResponseEntity<Indicador> getById(@PathVariable UUID id) {
        return indicadorService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * GET /api/indicadores/campus/{campusId}
     * Retorna todos os indicadores de um campus.
     */
    @GetMapping("/campus/{campusId}")
    public ResponseEntity<List<Indicador>> getByCampus(
            @PathVariable UUID campusId
    ) {
        try {
            List<Indicador> lista = indicadorService.findByCampusId(campusId);
            return ResponseEntity.ok(lista);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * POST /api/indicadores
     */
    @PostMapping
    public ResponseEntity<Indicador> create(
            @RequestBody IndicadorCreateRequest request
    ) {
        try {
            Indicador criado = indicadorService.createFromRequest(request);
            return ResponseEntity.status(201).body(criado);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * PUT /api/indicadores/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Indicador> update(
            @PathVariable UUID id,
            @RequestBody Indicador dadosNovos
    ) {
        try {
            return indicadorService.update(id, dadosNovos)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * DELETE /api/indicadores/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        indicadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
