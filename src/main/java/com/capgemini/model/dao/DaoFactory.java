package com.capgemini.model.dao;

import com.capgemini.db.DB;
import com.capgemini.model.dao.impl.ClienteDaoJDBC;
import com.capgemini.model.dao.impl.AnuncioDaoJDBC;

public class DaoFactory {

	public static AnuncioDao createAnuncioDao() {
		return new AnuncioDaoJDBC(DB.getConnection());
	}
	
	public static ClienteDao createClienteDao() {
		return new ClienteDaoJDBC(DB.getConnection());
	}
}
