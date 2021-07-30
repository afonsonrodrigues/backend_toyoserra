package com.toyoserra.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chassi {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String numero;
	
	private String veiculo;
	
	private LocalDate producao;
	
	private LocalDate venda;
	
	private LocalDate entrega;
	
	private String anoFabModelo;
	
	private String katashiki;
	
	private String tipoUso;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	public LocalDate getProducao() {
		return producao;
	}

	public void setProducao(LocalDate producao) {
		this.producao = producao;
	}

	public LocalDate getVenda() {
		return venda;
	}

	public void setVenda(LocalDate venda) {
		this.venda = venda;
	}

	public LocalDate getEntrega() {
		return entrega;
	}

	public void setEntrega(LocalDate entrega) {
		this.entrega = entrega;
	}

	public String getAnoFabModelo() {
		return anoFabModelo;
	}

	public void setAnoFabModelo(String anoFabModelo) {
		this.anoFabModelo = anoFabModelo;
	}

	public String getKatashiki() {
		return katashiki;
	}

	public void setKatashiki(String katashiki) {
		this.katashiki = katashiki;
	}

	public String getTipoUso() {
		return tipoUso;
	}

	public void setTipoUso(String tipoUso) {
		this.tipoUso = tipoUso;
	}

}
