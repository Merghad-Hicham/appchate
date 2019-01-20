/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;




public class Acces_donnee {

    public static Connection Getconnect(){
    Connection cn=null;
    try {
       
        Class.forName("com.mysql.jdbc.Driver");
    cn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chatapp","root","");
        
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    
    return cn;
}
    public static int mise_ajour(String sql){
   int t=0;
    try {
        Connection cn=Getconnect();
        Statement st=cn.createStatement();
        t=st.executeUpdate(sql);
      
    } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e.getMessage());
    }
 
       return t;
   }
 public static ResultSet selection(String sql){
  ResultSet rs=null;

  try {
        Connection cn=Getconnect();
        Statement st=cn.createStatement();
        rs=st.executeQuery(sql);
       
    } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e.getMessage());
    }
  return rs;
 }
public static int rechercher(String req,String cond){
     int i=0;
    try {
     ResultSet rs=selection(req);
        while (rs.next()) {            
            if (rs.getString(1).equals(cond)) {
                 i=1;
                 return i;
            }
        }
    } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e.getMessage());
    }
   return i;

}
/*public static void main(String[] args)
{
Acces_donnee ac=new Acces_donnee();
ac.Getconnect();
try {
ResultSet rs=Acces_donnee.selection("select * from client");
// parcourir les enregistrement de rs
while (rs.next()) {
System.out.println( rs.getInt(1)+" --"+ rs.getString(2)+" --"+ rs.getString(3) );
}
} catch (Exception e) 
{
e.getMessage();
}
}*/
    
}
