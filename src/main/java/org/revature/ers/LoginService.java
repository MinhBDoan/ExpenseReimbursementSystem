package org.revature.ers;

import java.io.IOException;
import java.sql.SQLException;

public class LoginService {

	public boolean isValid(String email, String password) throws SQLException, IOException {
		LoginDao loginDao = new LoginDao();
		boolean isValid = false;
		try {
			if(loginDao.checkValidation(email, password)) {
				System.out.println("Successful Login From LoginService");
				isValid = true;
				return isValid;
			}else{
				System.out.println("Unsuccessful Login From LoginService");
			return false;
			}
		}catch(SQLException ex){
			System.out.println("The following error has occured" + ex.getMessage());
		}
		return isValid;
	}

	

}
