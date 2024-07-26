/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package billsoftware;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author liyan
 */
public class ProductDisplayService {
    private Databaseconfig  dbConfig;
    
    public ProductDisplayService(Databaseconfig config){
    this.dbConfig = config;
    
    
    }
    
    public void showProducts(DefaultTableModel tblModel)throws SQLException{
    try{
    clearTable(tblModel);
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(dbConfig.getUrl(),dbConfig.getUsername(),dbConfig.getPassword());
    Statement stmt = con.createStatement();
    String sql = "SELECT * from products";
    ResultSet rs = stmt.executeQuery(sql);
    
    
    while (rs.next()){
    String pId = rs.getString("productid");
    String Pname = rs.getString("productname");
    String manu = rs.getString("manufacturer");
    String modelNo = rs.getString("modelno");
    String price = rs.getString("price");
    String Qty =  rs.getString("Qty");
    
    String tbData[]= {pId,Pname,manu,modelNo,price,Qty};
    tblModel.addRow(tbData);
    
    }
    
    }catch(ClassNotFoundException ex){
     Logger.getLogger(UserDisplayService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public void showproducts2(DefaultTableModel tblModel) throws SQLException{
    try{
    clearTable(tblModel);
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(dbConfig.getUrl(),dbConfig.getUsername(),dbConfig.getPassword());
    Statement stmt = con.createStatement();
    String sql = "SELECT productid,productname,manufacturer,modelno,price from products";
    ResultSet rs = stmt.executeQuery(sql);
    
    
    while (rs.next()){
    String pid = rs.getString("productid");
    String Pname = rs.getString("productname");
    String manu = rs.getString("manufacturer");
    String modelNo = rs.getString("modelno");
    String price = rs.getString("price");
    
    
    String tbData[]= {pid,Pname,manu,modelNo,price};
    tblModel.addRow(tbData);
    
    }
    
    }catch(ClassNotFoundException ex){
     Logger.getLogger(UserDisplayService.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    }
    
    
    
    private void clearTable(DefaultTableModel tblModel){
           tblModel.setRowCount(0);
    
    }
    public static void main()throws IOException{
    Databaseconfig config = new Databaseconfig();
    ProductDisplayService productdisplayservice =new ProductDisplayService(config);
    
    }
    
}
