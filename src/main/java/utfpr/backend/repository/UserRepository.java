package utfpr.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.backend.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>{
	Optional<User> findByUsername(String username);
}
