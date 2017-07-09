package com.pc.cofipa.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tipo_sistema")
public class TipoSistema implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_sistema")
	private Long codigo;
	
	
	@NotBlank(message = "O nome é obrigatório")
	@Size(max = 20, message = "O tamanho do nome não pode ser maior que {max} caracteres")
	@Column(name = "nome_sistema")
	private String nome;
	
	@ManyToMany(mappedBy = "tipoSistema")
	private List<PatrimonioInformatica> patrimonioInformatica;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<PatrimonioInformatica> getPatrimonioInformatica() {
		return patrimonioInformatica;
	}

	public void setPatrimonioInformatica(List<PatrimonioInformatica> patrimonioInformatica) {
		this.patrimonioInformatica = patrimonioInformatica;
	}

	public boolean isNovo() {
		return codigo == null;
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
		TipoSistema other = (TipoSistema) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	

}
