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
public class UserDisplayService {
    private Databaseconfig dbConfig;
    
    public UserDisplayService(Databaseconfig config){
    this.dbConfig = config;
    
    }
    
    public void showUsers(DefaultTableModel tblModel) throws SQLException{
    try{
        clearTable(tblModel);
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(dbConfig.getUrl(),dbConfig.getUsername(),dbConfig.getPassword());
    Statement stmt = con.createStatement();
    String sql = "SELECT uid,username,role FROM users";
    ResultSet rs = stmt.executeQuery(sql);
    
    while(rs.next()){
    String uid = rs.getString("uid");
    String username = rs.getString("username");
    String role = rs.getString("role");
    String tbData[]= {uid,username,role};
    tblModel.addRow(tbData);
    }
    
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDisplayService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    private void clearTable(DefaultTableModel tblModel){
           tblModel.setRowCount(0);
    
    }
    
    public static void main() throws IOException{
    Databaseconfig config = new Databaseconfig();
    UserDisplayService userdisplayservoce = new UserDisplayService(config);
    
    }
    
    
    
}
