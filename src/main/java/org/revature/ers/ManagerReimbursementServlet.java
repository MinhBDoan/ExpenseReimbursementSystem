package org.revature.ers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/ManagerReimbursementServlet")
public class ManagerReimbursementServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		
        ERSDao ersDao = new ERSDao();
       System.out.println("managerServllet");
        ArrayList<ERSRequest> reimbursementRequests = ersDao.viewReimbursementAll();
        
        PrintWriter out = res.getWriter();
        ObjectMapper mapper = new ObjectMapper();


        
        String jsonString = mapper.writeValueAsString(reimbursementRequests);
        out.println(jsonString);
        out.close();
		
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {

		
	}
}