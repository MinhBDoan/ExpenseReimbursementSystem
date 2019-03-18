package org.revature.ers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.revature.ers.ERSRequest;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {

	

			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				  
			}
			@Override
			protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				BufferedReader br = req.getReader();
				JSONObject json = new JSONObject(br.readLine());
				String email = json.getString("email");
				System.out.println("worked");
		        ERSDao ersDao = new ERSDao();
		        
		        ArrayList<ERSRequest> reimbursementRequests = ersDao.viewReimbursementById(email);
		        
		        PrintWriter out = res.getWriter();
		        ObjectMapper mapper = new ObjectMapper();


		        
		        String jsonString = mapper.writeValueAsString(reimbursementRequests);
		        out.println(jsonString);
		        out.close();
		     

			}

}