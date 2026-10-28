package app.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(min = 2, max = 100, message = "O produto deve ter entre 2 e 100 caracteres")
    private String produto;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 5, max = 500, message = "A descrição deve ter entre 5 e 500 caracteres")
    private String descricao;

    @NotNull(message = "O preço original é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço original deve ser maior que zero")
    private BigDecimal precoOriginal;

    @NotNull(message = "O preço promocional é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço promocional deve ser maior que zero")
    private BigDecimal precoPromocional;

    @NotNull(message = "A data de início é obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @NotNull(message = "A data de fim é obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

    @NotBlank(message = "A URL da imagem é obrigatória")
    @URL(message = "A URL da imagem é inválida")
    private String imagemUrl;

    @NotNull(message = "O status da oferta é obrigatório")
    private Boolean ativa;
    
    

	public Oferta() {

	}

	public Oferta(Long id, String produto, String descricao, BigDecimal precoOriginal, BigDecimal precoPromocional,
			LocalDate dataInicio, LocalDate dataFim, String imagemUrl, Boolean ativa) {
		super();
		this.id = id;
		this.produto = produto;
		this.descricao = descricao;
		this.precoOriginal = precoOriginal;
		this.precoPromocional = precoPromocional;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.imagemUrl = imagemUrl;
		this.ativa = ativa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPrecoOriginal() {
		return precoOriginal;
	}

	public void setPrecoOriginal(BigDecimal precoOriginal) {
		this.precoOriginal = precoOriginal;
	}

	public BigDecimal getPrecoPromocional() {
		return precoPromocional;
	}

	public void setPrecoPromocional(BigDecimal precoPromocional) {
		this.precoPromocional = precoPromocional;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}
    
    

}
