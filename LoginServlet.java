package com.project;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.PreparableStatement;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			PrintWriter pw = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver");
			//connection to the database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db","root","Vasuyadav@000");
			PreparedStatement ps = con.prepareStatement("select userName from logins_table where userName=? and password=?");
			String userName=request.getParameter("username");
			String passWord=request.getParameter("password");
			ps.setString(1, userName);
			ps.setString(2, passWord);
			ResultSet rs= ps.executeQuery();
			if (rs.next()) {
				RequestDispatcher rd = request.getRequestDispatcher("DashBoard.jsp");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("LoginFailed.html");
				rd.forward(request, response);
			}
					
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
