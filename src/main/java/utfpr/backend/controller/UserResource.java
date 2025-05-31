package utfpr.backend.controller;

import utfpr.backend.model.User;
import utfpr.backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retorna todos os usuários.
     * GET /users
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> lista = userService.findAll();
        return ResponseEntity.ok(lista);
    }

    /**
     * Retorna um usuário por ID.
     * GET /users/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        Optional<User> usuarioOpt = userService.findById(id);
        return usuarioOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retorna um usuário por username.
     * GET /users/search?username={username}
     */
    @GetMapping("/search")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        Optional<User> usuarioOpt = userService.findByUsername(username);
        return usuarioOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Cria um novo usuário.
     * POST /users
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Aqui você pode incluir lógica de validação antes de criar
        User criado = userService.create(user);
        return ResponseEntity
                .status(201)
                .body(criado);
    }

    /**
     * Atualiza um usuário existente.
     * PUT /users/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable UUID id,
            @RequestBody User userData
    ) {
        Optional<User> atualizadoOpt = userService.update(id, userData);
        return atualizadoOpt
                .map(atualizado -> ResponseEntity.ok(atualizado))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Remove um usuário por ID.
     * DELETE /users/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        Optional<User> usuarioOpt = userService.findById(id);
        if (usuarioOpt.isPresent()) {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
