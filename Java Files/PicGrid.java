import java.io.*;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PicGrid extends HttpServlet {
   
  

  
   public void doPost(HttpServletRequest request, 
               HttpServletResponse response)
              throws ServletException, java.io.IOException {

      // Check that we have a file upload request
      
      response.setContentType("text/html");
      java.io.PrintWriter out = response.getWriter( );
     


      

      out.println("<html>");
      out.println("<head>");
      out.println("<title>Photo List</title>");  
      out.println("</head>");
      out.println("<body>");
      out.println("<div id=\"images\">");
     
        
     
      
      
        String path = "/usr/share/apache-tomcat-7.0.32/webapps/hrispiro/Pictures/"; 
 
  String files;
  File folder = new File(path);
  File[] listOfFiles = folder.listFiles(); 
 
  for (int i = 0; i < listOfFiles.length; i++) 
  {
 
   if (listOfFiles[i].isFile()) 
   {
   files = listOfFiles[i].getName();
  //HttpSession session = request.getSession();
    //  session.setAttribute("Picpath", files);
    out.println("<link rel='stylesheet' href='style/style.css' type='text/css'> ");  
    out.println("<form action=\"PicEdit.jsp?PictureName="+files+"&xmlname=PicXml/"+files+"\" method=\"post\">"+"</form>"); 
    out.print("<a href=\"javascript:document.forms["+i+"].submit()\"> <img src='Pictures/"+files+"' alt='image' />"+"</a>");
      }
  }
      
      
      out.println("</div>");
      out.println("</body>");
      out.println("</html>");
      
      
      
      
      
      
      
   }
   public void doGet(HttpServletRequest request, 
                       HttpServletResponse response)
        throws ServletException, java.io.IOException {
        
        throw new ServletException("GET method used with " +
                getClass( ).getName( )+": POST method required.");
   } 
   
   
}
