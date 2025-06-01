package utfpr.backend.controller;

import utfpr.backend.dto.IndicadorCreateRequest;
import utfpr.backend.model.Indicador;
import utfpr.backend.services.IndicadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
		List<Indicador> lista = indicadorService.findAll();
		return ResponseEntity.ok(lista);
	}

	/** GET /api/indicadores/{id} — retorna indicador por ID */
	@GetMapping("/{id}")
	public ResponseEntity<Indicador> getById(@PathVariable UUID id) {
		Optional<Indicador> opt = indicadorService.findById(id);
		return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * POST /api/indicadores Recebe o DTO IndicadorCreateRequest no corpo da
	 * requisição. Atenção: aqui já usamos createFromRequest, não create().
	 */
	@PostMapping
	public ResponseEntity<Indicador> create(@RequestBody IndicadorCreateRequest request) {
		try {
			// Chama o método correto do service:
			Indicador criado = indicadorService.createFromRequest(request);
			return ResponseEntity.status(HttpStatus.CREATED).body(criado);
		} catch (IllegalArgumentException e) {
			// Se o DTO tiver valores inválidos (por exemplo, campus não existe),
			// retornamos 400 Bad Request
			return ResponseEntity.badRequest().build();
		}
	}

	/** PUT /api/indicadores/{id} — atualiza um indicador */
	@PutMapping("/{id}")
	public ResponseEntity<Indicador> update(@PathVariable UUID id, @RequestBody Indicador dadosNovos) {
		try {
			Optional<Indicador> atualizadoOpt = indicadorService.update(id, dadosNovos);
			return atualizadoOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/** DELETE /api/indicadores/{id} — remove um indicador */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		Optional<Indicador> opt = indicadorService.findById(id);
		if (opt.isPresent()) {
			indicadorService.delete(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
