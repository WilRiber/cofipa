package com.pc.cofipa.repository.filter;

import java.math.BigDecimal;

import com.pc.cofipa.model.Andar;
import com.pc.cofipa.model.Departamento;
import com.pc.cofipa.model.Divisao;

import com.pc.cofipa.model.ItemMaterialMobiliario;

import com.pc.cofipa.model.MaterialMobiliario;
import com.pc.cofipa.model.Secao;
import com.pc.cofipa.model.Tipo;

import com.pc.cofipa.model.Uge;

public class PatrimonioMobiliarioFilter {
	
	private String numero;
	private String serial;
	private String data;
	private String sala;
	private BigDecimal valor;
	private String obs;
	private String conta;
	private String nl;
	private String antigo;
	private Uge uge;
	private MaterialMobiliario materialMobiliario;
	private ItemMaterialMobiliario itemMaterialMobiliario;
	private Departamento departamento;
	private Divisao divisao;
	private Secao secao;
	private Andar andar;
	private Tipo tipo;
	
	
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
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
	public Uge getUge() {
		return uge;
	}
	public void setUge(Uge uge) {
		this.uge = uge;
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
	public Andar getAndar() {
		return andar;
	}
	public void setAndar(Andar andar) {
		this.andar = andar;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	

}
