package com.pc.cofipa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "patrimonio_mobiliario")
public class PatrimonioMobiliario implements Serializable {
			
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "codigo_patrimonio")
		private Long codigo;
		
		@NotBlank(message = "O número do patrimônio  é obrigatório")
		@Column(name ="numero_patrimonio ")
		private String numero;
		
		@Column(name = "serial_patrimonio")
		private String serial;
		
		@Column(name = "data_patrimonio")
		private LocalDate dataPatrimonio;
		
		@Column(name = "sala_patrimonio")
		private String sala;
		
		@Column(name = "valor_patrimonio")
		private BigDecimal valor;
		
		@Column(name = "obs_patrimonio")
		private String obs;
		
		@Column(name= "conta_patrimonio")
		private String conta;
		
		@Column(name = "nl_patrimonio")
		private String nl;
		
		@Column(name = "antigo_patrimonio")
		private String antigo;
		
		@NotNull(message = "O tipo de material é obribatório")
		@ManyToOne
		@JoinColumn(name = "codigo_material_mobiliario")
		private MaterialMobiliario materialMobiliario;
		
		@NotNull(message = "O Item Material  é obrigatório")
		@ManyToOne
	    @JoinColumn(name = "codigo_item_material_mobiliario")
		private ItemMaterialMobiliario itemMaterialMobiliario;
			
		@NotNull(message = "O tipo é obrigatório")
		@ManyToOne
		@JoinColumn(name = "codigo_tipo")
		private Tipo tipo;
		
		@NotNull(message = "A Uge é obrigatória")
		@ManyToOne
		@JoinColumn(name ="codigo_uge")
		private Uge uge;
		
		@NotNull(message = "O andar é obrigatório")
		@ManyToOne
		@JoinColumn(name = "codigo_andar")
		private Andar andar;
		
		@NotNull(message = "O Departamento é obrigatório")
		@ManyToOne
		@JoinColumn(name = "codigo_departamento")
		private Departamento departamento;
		
		@NotNull(message = "A Divisão é obrigatória")
		@ManyToOne
		@JoinColumn(name = "codigo_divisao")
		private Divisao divisao;
		
		@NotNull(message = "A Seção é obrigatória")
		@ManyToOne
		@JoinColumn(name = "codigo_secao")
		private Secao secao;
		
		private String foto;
		
		@Column(name = "content_type")
		private String contentType;
		
		@Transient
		private boolean novaFoto;
		
		public Long getCodigo() {
			return codigo;
		}

		public void setCodigo(Long codigo) {
			this.codigo = codigo;
		}

		public String getNumero() {
			return numero;
		}

		public void setNumero(String numero) {
			this.numero = numero;
		}

		public String getSerial() {
			return serial;
		}

		public void setSerial(String serial) {
			this.serial = serial;
		}

		public LocalDate getDataPatrimonio() {
			return dataPatrimonio;
		}

		public void setDataPatrimonio(LocalDate dataPatrimonio) {
			this.dataPatrimonio = dataPatrimonio;
		}

		public String getSala() {
			return sala;
		}

		public void setSala(String sala) {
			this.sala = sala;
		}

		public BigDecimal getValor() {
			return valor;
		}

		public void setValor(BigDecimal valor) {
			this.valor = valor;
		}

		public String getObs() {
			return obs;
		}

		public void setObs(String obs) {
			this.obs = obs;
		}

		public String getConta() {
			return conta;
		}

		public void setConta(String conta) {
			this.conta = conta;
		}

		public String getNl() {
			return nl;
		}

		public void setNl(String nl) {
			this.nl = nl;
		}

		public String getAntigo() {
			return antigo;
		}

		public void setAntigo(String antigo) {
			this.antigo = antigo;
		}

		public MaterialMobiliario getMaterialMobiliario() {
			return materialMobiliario;
		}

		public void setMaterialMobiliario(MaterialMobiliario materialMobiliario) {
			this.materialMobiliario = materialMobiliario;
		}

		public ItemMaterialMobiliario getItemMaterialMobiliario() {
			return itemMaterialMobiliario;
		}

		public void setItemMaterialMobiliario(ItemMaterialMobiliario itemMaterialMobiliario) {
			this.itemMaterialMobiliario = itemMaterialMobiliario;
		}
		public Tipo getTipo() {
			return tipo;
		}

		public void setTipo(Tipo tipo) {
			this.tipo = tipo;
		}

		public Uge getUge() {
			return uge;
		}

		public void setUge(Uge uge) {
			this.uge = uge;
		}

		public Andar getAndar() {
			return andar;
		}

		public void setAndar(Andar andar) {
			this.andar = andar;
		}

		public Departamento getDepartamento() {
			return departamento;
		}

		public void setDepartamento(Departamento departamento) {
			this.departamento = departamento;
		}

		public Divisao getDivisao() {
			return divisao;
		}

		public void setDivisao(Divisao divisao) {
			this.divisao = divisao;
		}

		public Secao getSecao() {
			return secao;
		}

		public void setSecao(Secao secao) {
			this.secao = secao;
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
			return !StringUtils.isEmpty(foto)? foto : "produto-mock.png";
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
			PatrimonioMobiliario other = (PatrimonioMobiliario) obj;
			if (codigo == null) {
				if (other.codigo != null)
					return false;
			} else if (!codigo.equals(other.codigo))
				return false;
			return true;
		}

	}



