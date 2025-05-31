package utfpr.backend.services;

import utfpr.backend.model.User;
import utfpr.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Busca todos os usuários.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Busca um usuário por ID.
     */
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    /**
     * Busca um usuário pelo username.
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Cria um novo usuário.
     */
    public User create(User user) {
        // Aqui você pode incluir lógica adicional (por ex., verificar duplicidade de username)
        return userRepository.save(user);
    }

    /**
     * Atualiza um usuário existente.
     */
    public Optional<User> update(UUID id, User userData) {
        return userRepository.findById(id)
            .map(userExistente -> {
                userExistente.setUsername(userData.getUsername());
                userExistente.setPassword(userData.getPassword());
                return userRepository.save(userExistente);
            });
    }

    /**
     * Remove um usuário por ID.
     */
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
