package org.revature.ers;
import org.revature.ers.RegisterDao;

import java.io.IOException;
import java.sql.SQLException;

import org.revature.ers.Employee;

public class RegisterService {
	
	public static RegisterDao registerDao;
	
	public RegisterService() {
		registerDao = new RegisterDao();
	}
	
	public static void RegisterEmployee(Employee employee) throws SQLException, IOException  {
		
		
			registerDao.registerNewEmployee(employee);
		
	
		
	}
}
