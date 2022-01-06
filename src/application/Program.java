package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

// Atualizando o sal�rio de um vendedor no banco de dados
public class Program {

	public static void main(String[] args) {
		
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"UPDATE seller "
					+ "SET BaseSalary = BaseSalary + ? "
					+ "WHERE "
					+"(DepartmentId = ?)");
				//atribuindo valores ao simbolo de interroga��o
				st.setDouble(1, 200.0);
				st.setInt(2, 2);
				
				// para executar o c�digo e mostrar a quantidade de linhas afetadas
				int rowsAffected = st.executeUpdate();
				
				System.out.println("Done! Rows affected: " + rowsAffected);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
