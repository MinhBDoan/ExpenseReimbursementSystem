package org.revature.ers;

import java.sql.SQLException;
import org.revature.ers.DBUtilities;

import java.io.IOException;
import java.sql.*;

public class RegisterDao {

	public void registerNewEmployee(Employee employee) throws SQLException, IOException {
		DBUtilities dbutilities = new DBUtilities();
		PreparedStatement ps = null;
		try {
			String sql = "INSERT INTO employee " + "(position, fullname, email , password) VALUES" + "(?,?,?,?)";
			ps = dbutilities.connection.prepareStatement(sql);
			System.out.println("Register Dao");
			ps.setString(1, employee.getPosition());
			ps.setString(2, employee.getFullName());
			ps.setString(3, employee.getEmail());
			ps.setString(4, employee.getPassword());
			
			ps.executeUpdate();
			dbutilities.DisconnectFromDB();
		}catch(Exception ex) {
			System.out.println(ex);
		}
		
	}

}
