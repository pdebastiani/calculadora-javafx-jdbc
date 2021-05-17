package com.capgemini.model.entities;

import java.io.Serializable;
import java.util.Date;

public class Anuncio implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String anuncio;
	private Date dataInicio;
	private Date dataFinal;
	private Double investimentoDiario;
	
	private Cliente cliente;
	
	public Anuncio() {
	}

	public Anuncio(Integer id, String anuncio, Date dataInicio, Date dataFinal, Double investimentoDiario, Cliente cliente) {
		this.id = id;
		this.anuncio = anuncio;
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.investimentoDiario = investimentoDiario;
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(String anuncio) {
		this.anuncio = anuncio;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Double getInvestimentoDiario() {
		return investimentoDiario;
	}

	public void setInvestimentoDiario(Double investimentoDiario) {
		this.investimentoDiario = investimentoDiario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Anuncio other = (Anuncio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Anuncio [id=" + id + ", anuncio=" + anuncio + ", dataInicio=" + dataInicio + ", dataFinal=" + dataFinal
				+ ", investimentoDiario=" + investimentoDiario + ", cliente=" + cliente + "]";
	}

}
