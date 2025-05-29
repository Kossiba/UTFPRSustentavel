package utfpr.backend.services;

import java.util.List;

import utfpr.backend.model.Campus;
import utfpr.backend.repository.CampusRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class CampusService {
    private final CampusRepository repository;

    public CampusService(CampusRepository repository) {
        this.repository = repository;
    }

    /**
     * Retorna todos os campus.
     */
    public List<Campus> findAll() {
        return repository.findAll();
    }

    /**
     * Retorna um campus pelo ID ou lança EntityNotFoundException.
     */
    public Campus findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Campus not found with id " + id));
    }

    /**
     * Cria um novo campus.
     */
    @Transactional
    public Campus create(Campus campus) {
        return repository.save(campus);
    }

    /**
     * Atualiza um campus existente.
     */
    @Transactional
    public Campus update(UUID id, Campus campus) {
        Campus existing = findById(id);
        existing.setNome(campus.getNome());
        existing.setCidade(campus.getCidade());
        existing.setUf(campus.getUf());
        return repository.save(existing);
    }

    /**
     * Remove um campus pelo ID.
     */
    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Campus not found with id " + id);
        }
        repository.deleteById(id);
    }
}
