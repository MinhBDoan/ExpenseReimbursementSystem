package org.revature.ers;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.revature.ers.ERSRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/EmployeeERSServlet")
public class EmployeeERSServlet extends HttpServlet {

	

			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				
			}
			@Override
			protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				ERSService ersService = new ERSService();
					System.out.println("servlet");
			
				
					
					
					
					ObjectMapper mapper = new ObjectMapper();
					System.out.println("servlet");
					ERSRequest ersRequest = mapper.readValue(req.getInputStream(), ERSRequest.class);		
					
						
							try {
								ersService.employeeSubmitERS(ersRequest);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
}
				

}