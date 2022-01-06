package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.mysql.cj.xdevapi.Result;

import db.DB;


public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// inserindo um novo vendendor na tabela sellers
		Connection conn = null;
		PreparedStatement st = null;
		try {
			// conectando ao banco de dados
			conn = DB.getConnection();
			
			// inserindo dados 
			st = conn.prepareStatement(
					"INSERT INTO seller "
					// campos da tabale que será preenchida
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			//atribuindo valores em cada ?
			st.setString(1, "Carl Gomes");
			st.setString(2, "carl@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1987").getTime()));
			st.setDouble(4, 4000.0);
			st.setInt(5, 4);
			
			// para a chamada da alteração e saber quantas linhas foram alteradas
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0 ) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			}
			else {
				System.out.println("No rows affected!");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			// sempre fechar por último
			DB.closeConnection();
		}
		
	}
}
