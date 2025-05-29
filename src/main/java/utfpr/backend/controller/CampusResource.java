// src/main/java/utfpr/backend/controller/CampusResource.java
package utfpr.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import utfpr.backend.model.Campus;
import utfpr.backend.services.CampusService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/campuses")
public class CampusResource {             

    private final CampusService service; 

    public CampusResource(CampusService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Campus>> findAll() {
        List<Campus> lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Campus> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Campus> create(@RequestBody @Valid Campus c) {
        Campus saved = service.create(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campus> update(@PathVariable UUID id, @RequestBody @Valid Campus c) {
        return ResponseEntity.ok(service.update(id, c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
