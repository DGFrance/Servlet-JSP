/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud_DB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import loginServlet.class_login;

/**
 *
 * @author Donny_F7234
 */
public class Select_Employee extends HttpServlet {

    //String employee_name = "";
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    @Override
    @SuppressWarnings("empty-statement")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name").trim();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Select_Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = class_login.DB_URL2;
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, class_login.UserName, class_login.Password);
        } catch (SQLException ex) {
            Logger.getLogger(Select_Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (con == null) {
            System.out.println("Connection Failed");
        } else {
            System.out.println("Connected database Successfully");
        }
        try {
            stmt = con.createStatement();
            PreparedStatement ps = con.prepareStatement("select * from public.employee where name = ? ");
            ps.setString(1, name);
            out.print("<table width=25% border=1>");
            out.print("<center><h1>Result:</h1></center>");
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);

            ResultSetMetaData rsmd = rs.getMetaData();
            
            while (rs.next())
            {
                out.print("<tr>");
                out.print("<td>" + rsmd.getColumnName(1) + "</td>");
                out.print("<td>" + rs.getString(1) + "</td></tr>");
                out.print("<tr><td>" + rsmd.getColumnName(2) + "</td>");
                out.print("<td>" + rs.getString(2) + "</td></tr>");
                out.print("<tr><td>" + rsmd.getColumnName(3) + "</td>");
                out.print("<td>" + rs.getString(3) + "</td></tr>");
                out.print("<tr><td>" + rsmd.getColumnName(4) + "</td>");
                out.print("<td>" + rs.getString(4) + "</td></tr>");
            }
            out.print("</table>");
        } catch (SQLException e) {

        }
    }
}
