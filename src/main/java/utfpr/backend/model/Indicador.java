package utfpr.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.util.UUID;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidade JPA para a tabela "indicador", com "quantidade" como Float.
 */
@Entity
@Table(name = "indicador")
public class Indicador{

	@Id
    @GeneratedValue
    @Column(name = "idIndicador", updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID idIndicador;

    /**
     * Relacionamento Many-to-One com Campus.
     * Caso prefira só armazenar o UUID, troque para:
     *   @Column(name = "idCampus", nullable = false, columnDefinition = "UUID")
     *   private UUID idCampus;
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCampus", nullable = false)
    private Campus campus;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;


    @Column(name = "quantidade")
    private Float quantidade;

    @Column(name = "medida", length = 20)
    private String medida;

    @Column(name = "dataInicial")
    private LocalDate dataInicial;

    @Column(name = "dataFinal")
    private LocalDate dataFinal;

    @Column(name = "dataAtualizacao")
    private LocalDateTime dataAtualizacao;

    public Indicador() { }

    // Getters e Setters

    public UUID getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(UUID idIndicador) {
        this.idIndicador = idIndicador;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @PrePersist
    protected void onCreate() {
        dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }
}
