package com.capgemini.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Classe que trata das conexões com o BD, conectanto e fechando
 * Se preocupar também em fechar a Statement e ResultSet
 * 
 * @author Paulo Sergio Debastiani <paulo.s.debastiani@gmail.com>
 *
 */
public class DB {

	private static Connection conn = null;
	
	/**
	 * Conecta com o BD
	 * Este método é um Singleton (Design Patterns): Se a conexão for NULL
	 * instancia uma nova conexão, senão retorna a instancia que já está aberta.
	 * @return
	 */
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	/**
	 * Fecha a conexão com o BD.
	 */
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	/**
	 * Faz a leitura do arquivo onde estão descritos os dados da conexão com o BD.
	 * 
	 * @return os dados para um Objeto do tipo Properties
	 */
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	/**
	 * Fecha a Classe Statement.
	 * 
	 * @param st
	 */
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	/**
	 * Fecha a Classe ResultSet.
	 * 
	 * @param rs
	 */
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
