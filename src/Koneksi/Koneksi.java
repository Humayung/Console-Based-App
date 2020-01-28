/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class Koneksi {

    public String user;
    public String pwd;
    public String host;
    public String db;
    public String urlValue;

    
   public Koneksi(String username,String password, String host, String db){
       this.user = username;
       this.pwd = password;
       this.host = host;
       this.db = db;
       this.urlValue = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pwd;
   }
   
   public int updateDB(String sql, String ... values){
       try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(urlValue);
            PreparedStatement pStatement = (PreparedStatement) conn.prepareStatement(sql);
            
            for(int i = 1;i<=values.length; i++){
                pStatement.setString(i,values[i-1]);
            }
            
            return pStatement.executeUpdate();
                       
        }catch(Exception e){
            System.out.println("Koneksi Gagal \n"+ e.getMessage());
        } 
       return 0;
    }
   
   public ResultSet selectDB(String sql, String ... values){
       try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(urlValue);
            Statement st = conn.createStatement();
            PreparedStatement pStatement = (PreparedStatement) conn.prepareStatement(sql);
            for(int i = 1;i<=values.length; i++){
                pStatement.setString(i,values[i-1]);
            }
            System.out.println(pStatement.toString());
            return pStatement.executeQuery();
                       
        }catch(Exception e){
            System.out.println("Koneksi Gagal \n"+ e.getMessage());
        } 
       return null;
    }
   
   public void closeConn(ResultSet res, Connection conn){
        try {
            res.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
}
   
   
    

