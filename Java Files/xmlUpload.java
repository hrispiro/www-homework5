import java.awt.image.BufferedImage;
import java.io.PrintWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.Writer;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class xmlUpload extends HttpServlet {

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,
      IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();

    String description = req.getParameter("description");
    String comment = req.getParameter("comment");
    String filename = req.getParameter("filename");
    out.println(description+"\n"+comment+"\n"+filename);
    
    
    
    int height =0;
    int width =0;
    BufferedImage readImage = null;
    String whFileName = "/usr/share/apache-tomcat-7.0.32/webapps/hrispiro/Pictures/"+filename;
    File myfile = new File(whFileName);
    try {
    readImage = ImageIO.read(myfile);
        height = readImage.getHeight();
        width = readImage.getWidth();
    } catch (Exception e) {
    readImage = null;
    }
    
    
    
     
     Writer writer = null;
 
        try {
            String text = "<PICTURE>"+"\n"+"<DESCRIPTION>"+description+
                    "</DESCRIPTION>"+"\n"+"<COMMENT>"+comment+"</COMMENT>" +"\n"
                    +"<HEIGHT>"+height+"</HEIGHT>\n<WIDTH>"+width+"</WIDTH>\n"
                    +"<ROTATE>"+0+"</ROTATE>\n"+"</PICTURE>";
            String myFileName = "/usr/share/apache-tomcat-7.0.32/webapps/hrispiro/PicXml/"+filename+".xml";
            File file = new File(myFileName);
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);
             HttpSession session = req.getSession();
      session.setAttribute("XmlEdited", filename+".xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    res.sendRedirect("/hrispiro/homepage.jsp");

  }
}
