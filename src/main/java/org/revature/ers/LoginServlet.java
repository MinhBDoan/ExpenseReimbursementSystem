package org.revature.ers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private LoginService loginService = new LoginService();
	private LoginDao loginDao = new LoginDao();
	
	protected void doGet() {
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		BufferedReader br = req.getReader();
		
		Employee employee = new Employee();
		
		JSONObject json = new JSONObject(br.readLine());
		
		String email = json.getString("email");
		String password = json.getString("password");
		
		boolean isManager= false;
		
		System.out.println(email);
		 try {
			employee =loginDao.getEmployeeByEmail(email, password);
			if(employee!= null && employee.getPosition().equals("Employee")) {
				System.out.println("is an employee!");
				
				JSONObject obj = new JSONObject();
				obj.put("id", employee.getId());
				obj.put("position", employee.getPosition());
				obj.put("fullname", employee.getFullName());
				obj.put("email", employee.getEmail());
				obj.put("password", employee.getPassword());
				
				PrintWriter output = res.getWriter();
				System.out.println(obj);
				output.print(obj);
				
			}else if(employee!= null && employee.getPosition().equals("Manager")) {
				System.out.println("is a manager!");

				JSONObject obj = new JSONObject();
				obj.put("id", employee.getId());
				obj.put("position", employee.getPosition());
				obj.put("fullname", employee.getFullName());
				obj.put("email", employee.getEmail());
				obj.put("password", employee.getPassword());
				
				PrintWriter output = res.getWriter();
				output.print(obj);
				
			}else {
				System.out.println("Does Not Work Here");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
//		}else{
//			System.out.println("Invalid Credentials");
//		}
		
		
		
		
}
}
