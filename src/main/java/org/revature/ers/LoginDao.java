package org.revature.ers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDao {

	public boolean checkValidation(String email, String password) throws SQLException, IOException {
		PreparedStatement ps = null;
		List<String>accountInfo = new ArrayList<String>();
		boolean checkValidation=false;
		
		DBUtilities dbutilities = new DBUtilities();
		try {
			String sql= "SELECT email, password FROM employee WHERE email=? AND password=?";
			ps=dbutilities.connection.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String emailDb=rs.getString("email");
				System.out.println(emailDb);
				accountInfo.add(emailDb);
				String passwordDb=rs.getString("password");
				System.out.println(passwordDb);
				accountInfo.add(passwordDb);
			}
			if(accountInfo.contains(email) && accountInfo.contains(password))
				checkValidation=true;
			dbutilities.DisconnectFromDB();
		}catch(SQLException ex){
			System.out.println("The following error has occured" + ex.getMessage());
		}
		return checkValidation;
	}
	public Employee getEmployeeByEmail(String email, String password) throws SQLException, IOException{
		Employee employee=null;
		PreparedStatement ps = null;
		DBUtilities dbutilities = new DBUtilities();
		
		try {
			String sql ="SELECT * FROM Employee WHERE email = ? AND password=?";
			 ps = dbutilities.connection.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				 employee = new Employee();
						employee.setId(rs.getInt("id"));
						employee.setPosition(rs.getString("position"));
						employee.setFullName(rs.getString("fullname"));
						employee.setEmail(rs.getString("email"));
						employee.setPassword(rs.getString("password"));
			}
		} catch(SQLException ex) {
			System.out.println("The following error has occured" + ex.getMessage());
		}
		
		return employee;
	}
	

}
