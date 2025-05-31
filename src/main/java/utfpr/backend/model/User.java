package utfpr.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
	
	@NotBlank
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;
	
	@NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    public User() { }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
