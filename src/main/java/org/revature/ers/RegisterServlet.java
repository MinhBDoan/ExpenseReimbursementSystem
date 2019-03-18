package org.revature.ers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
import org.revature.ers.RegisterService;
import org.revature.ers.Employee;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	public RegisterService registerService = new RegisterService();

			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				PrintWriter output = res.getWriter();
				output.write("Hello Get");
			}
			@Override
			protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				BufferedReader br = req.getReader();

				JSONObject json = new JSONObject(br.readLine());
				Employee employee = new Employee(json.getString("fullname"),json.getString("email"),json.getString("password"),json.getString("position"));
				
				try {
					RegisterService.RegisterEmployee(employee);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.print(employee + " 232323323" );
				
				
				
				
				
				
				
			}

}
