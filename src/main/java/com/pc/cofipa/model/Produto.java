package com.pc.cofipa.model;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name ="produto")
public class Produto implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank(message = "A descrição é obrigatória")
	@Size(min = 1, max = 150, message = "O tamanho da descrição deve estar entre 1 e 150" )
	private String descricao;
	
	
	@NotNull(message = "Valor unitário é obrigatório")
	@DecimalMin(value = "0.01", message = "O valor da cerveja deve ser maior que R$0,50")
	@DecimalMax(value = "9999999.99", message = "O valor da cerveja deve ser menor que R$9.999.999,99")
	@Column(name = "valor_unitario")
	private BigDecimal valorUnitario;
	
	@NotNull(message = "Valor total  é obrigatório")
	@DecimalMin(value = "0.01", message = "O valor da cerveja deve ser maior que R$0,50")
	@DecimalMax(value = "9999999.99", message = "O valor da cerveja deve ser menor que R$9.999.999,99")
	@Column(name = "valor_total")
	private BigDecimal valortotal;
	
	//@NotNull(message = "A quantidade  é obrigatória")
	//@Min(value = 1, message =" A quantidade do produto não pode ser Zero")
	@Max(value = 9999, message = "A quantidade em estoque deve ser menor que 9.999")
	private int quantidade;
	
	@NotNull(message = "A quantidade em estoque é obrigatória")
	@Min(value = 1,  message =" A quantidade do produto não pode ser Zero")
	@Max(value = 9999, message = "A quantidade em estoque deve ser menor que 9.999")
	private int estoque;
	
	@NotNull(message = "O fornecedor é obrigatório")
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "codigo_fornecedor")
	private Fornecedor fornecedor;
	
	@NotNull(message = "A unidade é obrigatória")
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "codigo_unidade")
	private Unidade unidade;
	
	private String foto;
	
	@Column(name = "content_type")
	private String contentType;
	
	@Transient
	private boolean novaFoto;
	
	@PrePersist @PreUpdate
	private void prePersistUpdate(){
		descricao = descricao.toUpperCase();
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public BigDecimal getValortotal() {
		return valortotal;
	}
	public void setValortotal(BigDecimal valortotal) {
		this.valortotal = valortotal;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getEstoque() {
		return estoque;
	}
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getFotoOuMock(){
		return !StringUtils.isEmpty(foto) ? foto : "produto-mock.png";
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(this.foto);
		
	}
	
	public boolean isNovo(){
		return codigo == null;
	}
	
	public boolean isNovaFoto() {
		return novaFoto;
	}

	public void setNovaFoto(boolean novaFoto) {
		this.novaFoto = novaFoto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	

}
