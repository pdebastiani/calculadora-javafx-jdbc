package com.capgemini.model.dao;

import java.util.List;

import com.capgemini.model.entities.Cliente;
import com.capgemini.model.entities.Anuncio;

public interface AnuncioDao {

	void insert(Anuncio obj);
	void update(Anuncio obj);
	void deleteById(Integer id);
	Anuncio findById(Integer id);
	List<Anuncio> findAll();
	List<Anuncio> findByCliente(Cliente cliente);
}
