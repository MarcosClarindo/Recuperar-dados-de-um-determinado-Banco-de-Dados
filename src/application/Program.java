package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;


public class Program {

	public static void main(String[] args) {
		

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		// Necess�rio o bloco try porque a consulta no banco de dados gera excess�es
		try {
			// conectanto ao banco de dados
			conn = DB.getConnection();
			
			// instanciando um objeto do tipo Statement
			st = conn.createStatement();
			
			// m�todo para entrar com o comando SQL
			rs = st.executeQuery("select * from department");
			
			// pecorrendo a tabela no banco de dados
			while (rs.next()) {
				System.out.println(rs.getInt("id") + ", " + rs.getString("Name"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// fechamento do m�todo 
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
