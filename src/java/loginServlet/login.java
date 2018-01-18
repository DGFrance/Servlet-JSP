package loginServlet;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 *
 * @author Donny_F7234
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    String user_ID = "";
    String user_Pasw = "";
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user_name = request.getParameter("u_name").trim();
        String user_pass = request.getParameter("u_pass").trim();

        try {
            //        class_login.forName("org.postgresql.Driver");
            Class.forName(class_login.JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = class_login.DB_URL;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, class_login.UserName, class_login.Password);
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (conn == null) {
            System.out.println("Connection Failed");
        } else {
            System.out.println("Connected database Successfully");
            try {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM public.user where username = '" + user_name + "'");
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (rs != null) {
                try {
                    while (rs.next()) {
                        user_ID = rs.getString("username").trim();
                        user_Pasw = rs.getString("password").trim();
                        System.out.println(user_ID + user_Pasw);
                        if (user_name.equals(user_ID) && user_pass.equals(user_Pasw)) {
                            request.getRequestDispatcher("/succes.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("/failure.jsp").forward(request, response);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {
                request.getRequestDispatcher("/failure.jsp").forward(request, response);
            }

        }

    }
}
