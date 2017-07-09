package com.pc.cofipa.repository.filter;

import java.math.BigDecimal;

import com.pc.cofipa.model.Andar;
import com.pc.cofipa.model.Departamento;
import com.pc.cofipa.model.Divisao;
import com.pc.cofipa.model.ItemMaterialInformatica;
import com.pc.cofipa.model.MaterialInformatica;
import com.pc.cofipa.model.Secao;
import com.pc.cofipa.model.Tipo;
import com.pc.cofipa.model.TipoSistema;
import com.pc.cofipa.model.Uge;

public class PatrimonioInformaticaFilter {
	
	private String numero;
	private String serial;
	private String data;
	private String mac;
	private String ip;
	private String rede;
	private String sala;
	private BigDecimal valor;
	private String obs;
	private String conta;
	private String nl;
	private String antigo;
	private Uge uge;
	private MaterialInformatica materialInformatica;
	private ItemMaterialInformatica itemMaterialInformatica;
	private Departamento departamento;
	private Divisao divisao;
	private Secao secao;
	private Andar andar;
	private TipoSistema tipoSistema;
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
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRede() {
		return rede;
	}
	public void setRede(String rede) {
		this.rede = rede;
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
	public MaterialInformatica getMaterialInformatica() {
		return materialInformatica;
	}
	public void setMaterialInformatica(MaterialInformatica materialInformatica) {
		this.materialInformatica = materialInformatica;
	}
	public ItemMaterialInformatica getItemMaterialInformatica() {
		return itemMaterialInformatica;
	}
	public void setItemMaterialInformatica(ItemMaterialInformatica itemMaterialInformatica) {
		this.itemMaterialInformatica = itemMaterialInformatica;
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
	public TipoSistema getTipoSistema() {
		return tipoSistema;
	}
	public void setTipoSistema(TipoSistema tipoSistema) {
		this.tipoSistema = tipoSistema;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}


}
