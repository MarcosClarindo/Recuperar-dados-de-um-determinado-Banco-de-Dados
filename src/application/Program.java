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
		
		// Necessário o bloco try porque a consulta no banco de dados gera excessões
		try {
			// conectanto ao banco de dados
			conn = DB.getConnection();
			
			// instanciando um objeto do tipo Statement
			st = conn.createStatement();
			
			// método para entrar com o comando SQL
			rs = st.executeQuery("select * from department");
			
			// pecorrendo a tabela no banco de dados
			while (rs.next()) {
				System.out.println(rs.getInt("id") + ", " + rs.getString("Name"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// fechamento do método 
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
