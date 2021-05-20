import java.sql.Connection;

import com.capgemini.db.DB;

/**
 * Testa a conexão com o BS MySQL
 *  
 * @author Paulo Sergio Debastiani <paulo.s.debastiani@gmail.com>
 *
 */
public class Program {

	public static void main(String[] args) {
	
		Connection conn = DB.getConnection();
		DB.closeConnection();

	}

}
