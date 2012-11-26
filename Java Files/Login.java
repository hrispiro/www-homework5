import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.String;

public class Login extends HttpServlet {

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,
      IOException {

      boolean entry=false;
      Connection con = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      res.setContentType("text/html");
      PrintWriter out = res.getWriter();

      String user = req.getParameter("UserName");
      String password = req.getParameter("Password");
      
  
  try {
  Class.forName("com.mysql.jdbc.Driver");
  con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hw3","root","********");
  stmt = con.createStatement();
  rs = stmt.executeQuery("SELECT * FROM login");
  // displaying records
  while(rs.next()){
  if(rs.getObject(1).toString().equals(user)&&rs.getObject(2).toString().equals(password)){entry=true; break;};
  
  }
  } catch (SQLException e) {
 throw new ServletException("Servlet Could not display records.", e);
  } catch (ClassNotFoundException e) {
  throw new ServletException("JDBC Driver not found.", e);
  } finally {
  try {
  if(rs != null) {
  rs.close();
  rs = null;
  }
  if(stmt != null) {
  stmt.close();
  stmt = null;
  }
  if(con != null) {
  con.close();
  con = null;
  }
  } catch (SQLException e) {}
  
    res.setContentType("text/html");

    if (!entry) {
      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
      out.println("<BODY>Your login and password are invalid.<BR>");
      out.println("You may want to <A HREF=\"/hrispiro/index.html\">try again</A>");
      out.println("</BODY></HTML>");
    } else {
      // Valid login. Make a note in the session object.
      HttpSession session = req.getSession();
      session.setAttribute("logon.isDone", user);
      // Try redirecting the client to the page he first tried to access
      try {
        String target = "homepage.jsp";//(String) session.getAttribute("login.target");
        if (target != null) {
          res.sendRedirect(target);
          return;
        }
      } catch (Exception ignored) {
      }
      // Couldn't redirect to the target. Redirect to the site's home page.
      res.sendRedirect("/");
    }
  }
  }
  
}