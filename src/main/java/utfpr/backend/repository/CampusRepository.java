// src/main/java/utfpr/backend/repository/CampusRepository.java
package utfpr.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utfpr.backend.model.Campus;
import java.util.UUID;

public interface CampusRepository extends JpaRepository<Campus, UUID> {
    // herda métodos como findAll(), findById(), save(), delete()...
}
