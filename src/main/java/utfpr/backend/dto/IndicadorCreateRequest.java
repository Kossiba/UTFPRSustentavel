// src/main/java/utfpr/backend/dto/IndicadorCreateRequest.java
package utfpr.backend.dto;

import java.time.LocalDate;
import java.util.UUID;

public class IndicadorCreateRequest {
    private String tipo;
    private String descricao;
    private Float quantidade;
    private String medida;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private UUID idCampus;
    
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
	public UUID getIdCampus() {
		return idCampus;
	}
	public void setIdCampus(UUID idCampus) {
		this.idCampus = idCampus;
	}  
    
    
}
