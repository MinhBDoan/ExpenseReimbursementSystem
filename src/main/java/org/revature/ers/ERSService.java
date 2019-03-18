package org.revature.ers;

import java.io.IOException;
import java.sql.SQLException;

public class ERSService {

	public void  updateEmployeeService(Employee employee) throws SQLException, IOException {
		System.out.println("inside service");
		ERSDao ersDao = new ERSDao();
		 ersDao.updateEmployeePersonalInfoById(employee);
		
	}
	public static void employeeSubmitERS(ERSRequest ersRequest) throws IOException, SQLException {
		ERSDao.submitReimbursement(ersRequest);
		
	}


	
	
}
