package org.revature.ers;

import javax.servlet.http.HttpServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/UpdateEmployeeServlet")
public class UpdateEmployeeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter output = res.getWriter();
		output.write("GET");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BufferedReader br = req.getReader();

		JSONObject json = new JSONObject(br.readLine());
		Employee employee = new Employee(json.getInt("id"), json.getString("fullname"), json.getString("email"), json.getString("password"));
		System.out.println(employee);
		System.out.println(json.getString("fullname"));
		ERSService ersService = new ERSService();
		try {
			ersService.updateEmployeeService(employee);
		} catch (SQLException ex) {
			System.out.println("The following error has occured" + ex.getMessage());
		}

	}
}