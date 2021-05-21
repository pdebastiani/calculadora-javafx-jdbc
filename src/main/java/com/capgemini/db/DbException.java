package com.capgemini.db;

/**
 * Classe Exception própria.
 * 
 * @author Paulo Sergio Debastiani <paulo.s.debastiani@gmail.com>
 *
 */
public class DbException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DbException(String msg) {
		super(msg);
	}
}
