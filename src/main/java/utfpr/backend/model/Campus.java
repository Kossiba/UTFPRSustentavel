package utfpr.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_campus")
public class Campus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(columnDefinition="uuid", updatable=false, nullable=false)
    private UUID idCampus;

    private String nome;
    private String cidade;
    private String uf;
    
    public Campus() {
    	
    }

	public Campus(UUID idCampus, String nome, String cidade, String uf) {
		super();
		this.idCampus = idCampus;
		this.nome = nome;
		this.cidade = cidade;
		this.uf = uf;
	}

	public UUID getIdCampus() {
		return idCampus;
	}

	public void setIdCampus(UUID idCampus) {
		this.idCampus = idCampus;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idCampus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campus other = (Campus) obj;
		return Objects.equals(idCampus, other.idCampus);
	}
    
}
