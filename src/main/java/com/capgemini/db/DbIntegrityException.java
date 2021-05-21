package com.capgemini.db;

/**
 * Classe mãe da DbException, que por sua vez é uma classe de exceção própria, 
 * do usuário.
 * 
 * @author Paulo Sergio Debastiani <paulo.s.debastiani@gmail.com>
 *
 */
public class DbIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DbIntegrityException(String msg) {
		super(msg);
	}
}