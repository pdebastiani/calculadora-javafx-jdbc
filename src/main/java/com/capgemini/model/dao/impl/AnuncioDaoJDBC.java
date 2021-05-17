package com.capgemini.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.mysql.jdbc.Statement;

import com.capgemini.db.DB;
import com.capgemini.db.DbException;
import com.capgemini.model.dao.AnuncioDao;
import com.capgemini.model.entities.Cliente;
import com.capgemini.model.entities.Anuncio;

public class AnuncioDaoJDBC implements AnuncioDao {

	private Connection conn;
	
	public AnuncioDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Anuncio obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO anuncio "
					+ "(anuncio, dataInicio, dataFinal, investimentoDiario, idCliente) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
			Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getAnuncio());
			st.setDate(2, new java.sql.Date(obj.getDataInicio().getTime()));
			st.setDate(3, new java.sql.Date(obj.getDataFinal().getTime()));
			st.setDouble(4, obj.getInvestimentoDiario());
			st.setInt(5, obj.getCliente().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Anuncio obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE anuncio "
					+ "SET anuncio = ?, dataInicio = ?, dataFinal = ?, investimentoDiario = ?, idCliente = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getAnuncio());
			st.setDate(2, new java.sql.Date(obj.getDataInicio().getTime()));
			st.setDate(3, new java.sql.Date(obj.getDataFinal().getTime()));
			st.setDouble(4, obj.getInvestimentoDiario());
			st.setInt(5, obj.getCliente().getId());
			st.setInt(6, obj.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM anuncio WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Anuncio findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT anuncio.*,cliente.nome as CliNome "
					+ "FROM anuncio INNER JOIN cliente "
					+ "ON anuncio.idCliente = cliente.id "
					+ "WHERE anuncio.id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Cliente dep = instantiateCliente(rs);
				Anuncio obj = instantiateAnuncio(rs, dep);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Anuncio instantiateAnuncio(ResultSet rs, Cliente cli) throws SQLException {
		Anuncio obj = new Anuncio();
		obj.setId(rs.getInt("id"));
		obj.setAnuncio(rs.getString("anuncio"));
		obj.setDataInicio(rs.getDate("dataInicio"));
		obj.setDataFinal(rs.getDate("dataFinal"));
		obj.setInvestimentoDiario(rs.getDouble("investimentoDiario"));
		obj.setCliente(cli);
		return obj;
	}

	private Cliente instantiateCliente(ResultSet rs) throws SQLException {
		Cliente cli = new Cliente();
		cli.setId(rs.getInt("idCliente"));
		cli.setNome(rs.getString("nome"));
		return cli;
	}

	@Override
	public List<Anuncio> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT anuncio.*,cliente.nome as CliNome "
					+ "FROM anuncio INNER JOIN cliente "
					+ "ON anuncio.idCliente = cliente.id "
					+ "ORDER BY anuncio");
			
			rs = st.executeQuery();
			
			List<Anuncio> list = new ArrayList<>();
			Map<Integer, Cliente> map = new HashMap<>();
			
			while (rs.next()) {
				
				Cliente cli = map.get(rs.getInt("idCliente"));
				
				if (cli == null) {
					cli = instantiateCliente(rs);
					map.put(rs.getInt("idCliente"), cli);
				}
				
				Anuncio obj = instantiateAnuncio(rs, cli);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Anuncio> findByCliente(Cliente cliente) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT anuncio.*,cliente.nome as CliNome "
					+ "FROM anuncio INNER JOIN cliente "
					+ "ON anuncio.idCliente = cliente.id "
					+ "WHERE idCliente = ? "
					+ "ORDER BY anuncio");
			
			st.setInt(1, cliente.getId());
			
			rs = st.executeQuery();
			
			List<Anuncio> list = new ArrayList<>();
			Map<Integer, Cliente> map = new HashMap<>();
			
			while (rs.next()) {
				
				Cliente cli = map.get(rs.getInt("idCliente"));
				
				if (cli == null) {
					cli = instantiateCliente(rs);
					map.put(rs.getInt("idCliente"), cli);
				}
				
				Anuncio obj = instantiateAnuncio(rs, cli);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
