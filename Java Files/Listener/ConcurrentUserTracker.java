package Listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.StringTokenizer;
import javax.servlet.*;
import javax.servlet.http.*;

public class ConcurrentUserTracker implements HttpSessionListener,HttpSessionAttributeListener {
  static int users = 0;
   static int  loginuser=0;
   public static ArrayList userList=new ArrayList();
  public void sessionCreated(HttpSessionEvent e) {
    users++;
    hit("/usr/share/apache-tomcat-7.0.32/webapps/hrispiro/Stats/hitcounter.txt");
  }
  public void sessionDestroyed(HttpSessionEvent e) {
    if(users>0){
      users--;
    }
  }
  
  public void attributeAdded(HttpSessionBindingEvent 
sessionBindingEvent) {
      if(sessionBindingEvent.getName().compareTo("logon.isDone")==0){
          //loginuser = (String) sessionBindingEvent.getValue();
    userList.add(sessionBindingEvent.getValue());
      loginuser = userList.indexOf(sessionBindingEvent.getValue());
      } 
      else if(sessionBindingEvent.getName().compareTo("UploadedPic")==0)
      {
          hit("/usr/share/apache-tomcat-7.0.32/webapps/hrispiro/Stats/uploadCounter.txt");
      }
      else if(sessionBindingEvent.getName().compareTo("XmlEdited")==0)
      {
          hit("/usr/share/apache-tomcat-7.0.32/webapps/hrispiro/Stats/xmlEditCounter.txt");
      }
      else{hit("/usr/share/apache-tomcat-7.0.32/webapps/hrispiro/Stats/Error.txt");}
     
      
 
} 
  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
 
public void attributeRemoved(HttpSessionBindingEvent 
sessionBindingEvent) {
    userList.remove(userList.indexOf(sessionBindingEvent.getValue()));
    loginuser=userList.size()-1;
    

} 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
public void attributeReplaced(HttpSessionBindingEvent 
sessionBindingEvent) {
    
     if(sessionBindingEvent.getName().compareTo("XmlEdited")==0)
      {
          hit("/usr/share/apache-tomcat-7.0.32/webapps/hrispiro/Stats/xmlEditCounter.txt");
      }
    

}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 

  public static int getConcurrentUsers() {
    return users;
  }
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
  public static String getLoginName() {
    String str="There is no user loged in";
      if(!ConcurrentUserTracker.getArray().isEmpty()){
    str = (String) ConcurrentUserTracker.getArray().get(ConcurrentUserTracker.loginuser);
    return str;
    }
      return str;
      
  }
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
  public static ArrayList getArray() {
    return userList;
  }
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
  public void hit(String path){
  
  String DataLine = "";
    try {
      File inFile = new File(path);
      BufferedReader br = new BufferedReader(new InputStreamReader(
          new FileInputStream(inFile)));

      DataLine = br.readLine();
      br.close();
    } catch (FileNotFoundException ex) {
      //return (null);
    } catch (IOException ex) {
      //return (null);
    }
    //return (DataLine);

    int i=Integer.parseInt(DataLine);
    i++;
    
    ///////////////////////////////////////////////////////////////////////////////////////
     Writer writer = null;
 
        try {
            String text = Integer.toString(i);
            String myFileName = path;
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
    
    /////////////////////////////////////////////////////////////////////////////////////
  }
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
  public static String globalhit(int index){
  String path;
  if(index==0){path="/usr/share/apache-tomcat-7.0.32/webapps/hrispiro/Stats/hitcounter.txt";}
  else if(index==1){path="/usr/share/apache-tomcat-7.0.32/webapps/hrispiro/Stats/uploadCounter.txt";}
  else if (index==2){path="/usr/share/apache-tomcat-7.0.32/webapps/hrispiro/Stats/xmlEditCounter.txt";}
  else{path="/usr/share/apache-tomcat-7.0.32/webapps/hrispiro/Stats/hitcounter.txt";}
      
  String DataLine = "";
    try {
      File inFile = new File(path);
      BufferedReader br = new BufferedReader(new InputStreamReader(
          new FileInputStream(inFile)));

      DataLine = br.readLine();
      br.close();
    } catch (FileNotFoundException ex) {
      return (null);
    } catch (IOException ex) {
      return (null);
    }
    return (DataLine);
  }
  
  
  }
