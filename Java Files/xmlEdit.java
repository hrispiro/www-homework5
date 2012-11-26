import java.io.PrintWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.Writer;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class xmlEdit extends HttpServlet {

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,
      IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();

    String description = req.getParameter("description");
    String comment = req.getParameter("comment");
    String height = req.getParameter("height");
    String width = req.getParameter("width");
    String rot = req.getParameter("txtDeg");
    String filename = req.getParameter("filename");
       
    Writer writer = null;
 
        try {
            String text = "<PICTURE>"+"\n"+"<DESCRIPTION>"+description+
                    "</DESCRIPTION>"+"\n"+"<COMMENT>"+comment+"</COMMENT>" +"\n"
                    +"<HEIGHT>"+height+"</HEIGHT>\n<WIDTH>"+width+"</WIDTH>\n"
                    +"<ROTATE>"+rot+"</ROTATE>\n"+"</PICTURE>";
            String myFileName = "/usr/share/apache-tomcat-7.0.32/webapps/hrispiro/PicXml/"+filename+".xml";
            File file = new File(myFileName);
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    HttpSession session = req.getSession();
      session.setAttribute("XmlEdited", filename+".xml");
    String target = "PicEdit.jsp?PictureName="+filename+"&xmlname=PicXml/"+filename;
    res.sendRedirect(target);
}
}
