package org.revature.ers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ERSDao {
	
	public void statusChangeReimbursement(ERSRequest ersRequest) throws IOException {

		PreparedStatement ps = null;
		String status = ersRequest.getStatus();
		Integer ManagerId = ersRequest.getManagerId();
		String managerEmail = ersRequest.getManagerEmail();
		String managerPassword = ersRequest.getManagerPassword();
		try {
			DBUtilities dbUtilities = new DBUtilities();

			String sqlStatement = "UPDATE Reimbursements SET status = ?, ManagerId=?, managerEmail=?, managerPassword=? WHERE id=?";
			ps = dbUtilities.connection.prepareStatement(sqlStatement);
			ps.setString(1, status);
			ps.setInt(2, ManagerId);
			ps.setString(3, managerEmail);
			ps.setString(4, managerPassword);
			ps.setInt(5, ersRequest.getId());

			ResultSet resultSet = ps.executeQuery();

			dbUtilities.DisconnectFromDB();

		} catch (SQLException ex) {
			System.out.println("The following error has occured" + ex.getMessage());
		}

	}
	
	public ArrayList<ERSRequest> viewReimbursementAll() {
		PreparedStatement ps = null;
		ERSRequest ersRequest = null;
		ArrayList<ERSRequest> reimbursementRequest = new ArrayList<ERSRequest>();
		try {
			DBUtilities dbutilities = new DBUtilities();
			String sql = "SELECT * FROM Reimbursements";
			ps = dbutilities.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Integer id = rs.getInt("id");
				String status = rs.getString("status");
				String image = rs.getString("image");
				Integer amount = rs.getInt("amount");
				Integer employeeId = rs.getInt("employeeId");
				String employeeName = rs.getString("employeeName");
				String employeeEmail = rs.getString("email");
				String password = rs.getString("password");
				Integer ManagerId = rs.getInt("ManagerId");
				String managerEmail = rs.getString("managerEmail");
				String managerPassword = rs.getString("managerPassword");

				ersRequest = new ERSRequest(id, status, amount, employeeId, employeeName, employeeEmail, password,
						ManagerId, managerEmail, managerPassword);
				ersRequest.setImage(image);

				reimbursementRequest.add(ersRequest);
			}
			dbutilities.DisconnectFromDB();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return reimbursementRequest;

	}
	
	public void updateEmployeePersonalInfoById (Employee employee) throws SQLException, IOException {
		PreparedStatement ps = null;
		Employee emp = null;
		try {
			DBUtilities dbUtilities = new DBUtilities();
			String sqlStatement = "UPDATE Employee SET fullname = ?, email = ?, password = ? WHERE id = ?";
			
			ps = dbUtilities.connection.prepareStatement(sqlStatement);
			
			ps.setString(1, employee.getFullName());
			ps.setString(2, employee.getEmail());
			ps.setString(3, employee.getPassword());
			ps.setInt(4, employee.getId());
			ps.executeUpdate();
			
			dbUtilities.DisconnectFromDB();
		}catch(SQLException ex) {
			System.out.println("The following error has occured" + ex.getMessage());
		}
	}
	
	public static void submitReimbursement(ERSRequest ersRequest) throws SQLException, IOException {
		System.out.println("im back bitches");
		PreparedStatement ps = null;
		String status = ersRequest.getStatus();
		String image = ersRequest.getImage();
		Integer amount = ersRequest.getAmount();
		Integer employeeId=ersRequest.getEmployeeId();
		String employeeName = ersRequest.getEmployeeName();
		String email = ersRequest.getEmail();
		String password = ersRequest.getPassword();
		Integer ManagerId = ersRequest.getManagerId();
		String managerEmail = ersRequest.getManagerEmail();
		String managerPassword = ersRequest.getManagerPassword();
		
		System.out.println(ManagerId);
		try {
			DBUtilities dbUtilities = new DBUtilities();

			String sqlStatement = "INSERT INTO Reimbursements (status,image, amount, employeeId, employeeName, email, password, ManagerId, managerEmail, managerPassword) VALUES(?,?,?,?,?,?,?,?,?,?)";
			ps = dbUtilities.connection.prepareStatement(sqlStatement);
			ps.setString(1,status);
			ps.setString(2,image);
			ps.setInt(3,amount);
			ps.setInt(4,employeeId);
			ps.setString(5,employeeName);
			ps.setString(6,email);
			ps.setString(7,password);
			ps.setInt(8,ManagerId);
			ps.setString(9,managerEmail);
			ps.setString(10,managerPassword);
			
			
			ResultSet resultSet = ps.executeQuery();
			
			System.out.println("ERS Submit Success");
			
			dbUtilities.DisconnectFromDB();

		} catch (SQLException ex) {
			System.out.println("The following error has occured" + ex.getMessage());
		}
		
}
	public ArrayList<ERSRequest> viewReimbursementById(String email) {
		PreparedStatement ps = null;
		ERSRequest reimbursement = null;
		ArrayList<ERSRequest> reimbursementRequest = new ArrayList<ERSRequest>();
		System.out.println(email + "dao email");
		try {
            DBUtilities dbutilities = new DBUtilities();
			String sql = "SELECT * FROM Reimbursements INNER JOIN Employee ON Reimbursements.EmployeeId = Employee.id WHERE Employee.email = ?";
			ps = dbutilities.connection.prepareStatement(sql);
			ps.setString(1, email);
                        
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Integer id = rs.getInt("id");
				String status=rs.getString("status");
				String image =rs.getString("image");
				Integer amount=rs.getInt("amount");
				Integer employeeId=rs.getInt("employeeId");
				String employeeName=rs.getString("employeeName");
				String employeeEmail=rs.getString("email");
				String password =rs.getString("password");
				Integer ManagerId=rs.getInt("ManagerId");
				String managerEmail=rs.getString("managerEmail");
				String managerPassword=rs.getString("managerPassword");
				
				reimbursement = new ERSRequest(id,status,amount,employeeId,employeeName,employeeEmail,password,ManagerId,managerEmail,managerPassword);
				System.out.println(reimbursement.toString());
				reimbursement.setImage(image);
				
				reimbursementRequest.add(reimbursement);
			}
			dbutilities.DisconnectFromDB();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return reimbursementRequest;
    }
	
	
	

}
