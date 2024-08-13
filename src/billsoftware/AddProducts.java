/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package billsoftware;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 *
 * @author liyan
 */
public class AddProducts extends javax.swing.JFrame {

    /**
     * Creates new form AddProducts
     */
    private ProductDisplayService productdisplayservice;
    private final Databaseconfig dbConfig;
    String productcode;
    
    private String newproductid;
    
    /**
     *
     * @param config
     * @throws IOException
     */
    public AddProducts(Databaseconfig config)throws IOException {
        initComponents();
        this.dbConfig = config;
        reCall();
    }
    private void reCall()throws IOException{
    productdisplayservice = new ProductDisplayService(dbConfig);
    try{
    DefaultTableModel tblModel = (DefaultTableModel)table1.getModel();
    productdisplayservice.showProducts(tblModel);
    
    }catch(SQLException ex){
     System.out.print(ex);
    }
    
    }
    
    private void generateNewPid(){
    
    try{
    Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(dbConfig.getUrl(),dbConfig.getUsername(),dbConfig.getPassword())) {
            Statement stmt = con.createStatement();
            String sql = "SELECT productid  FROM products ORDER BY productid DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.next()){
                String lastId = rs.getString("productid");
                System.out.print(lastId);
                String numericPart = lastId.substring(1);
                int newId = Integer.parseInt(numericPart)+1;
                newproductid = 'P'+String.format("%03d", newId);
                
                
            }else{
                newproductid = "P001";
                
            }
            System.out.println("The new user ID is: " + newproductid);
            
            rs.close();
            stmt.close();
        }
    
    
    } catch (SQLException | ClassNotFoundException ex) {
        
    }
    
    
    
    }
    private void priceActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        pname = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        pmanu = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        pmodelno = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        pqty = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        pprice = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        addstock = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1120, 600));
        setMinimumSize(new java.awt.Dimension(1120, 630));
        setPreferredSize(new java.awt.Dimension(1120, 630));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(176, 211, 209));
        jPanel1.setMaximumSize(new java.awt.Dimension(1100, 600));
        jPanel1.setMinimumSize(new java.awt.Dimension(1100, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Add products");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 460, 60));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Product Id", "Product Name", "Manufacturer", "Model No", "Price", "Qty"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 1080, 220));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Products");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 170, 40));
        jPanel1.add(pname, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 160, 40));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Product name");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 170, 40));
        jPanel1.add(pmanu, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 160, 40));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Manufaturer");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 170, 40));
        jPanel1.add(pmodelno, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 160, 40));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Model No");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 170, 40));
        jPanel1.add(pqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 180, 160, 40));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Quantity");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, 170, 40));
        jPanel1.add(pprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, 160, 40));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Price");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 160, 40));

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 1100, 10));

        addstock.setBackground(new java.awt.Color(189, 103, 219));
        addstock.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        addstock.setForeground(new java.awt.Color(255, 255, 255));
        addstock.setText("Add stock");
        addstock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addstockMouseClicked(evt);
            }
        });
        jPanel1.add(addstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 140, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons8-back-52.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addstockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addstockMouseClicked
        // TODO add your handling code here:
        
        try{
        generateNewPid();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
        Statement stmt = con.createStatement();
        
        String sql = "INSERT INTO products (productid,productname,manufacturer,modelno,price,Qty) VALUES ('"+newproductid+"','"+pname.getText()+"','"+pmanu.getText()+"','"+pmodelno.getText()+"','"+pprice.getText()+"','"+pqty.getText()+"')";
        int result = stmt.executeUpdate(sql);
        
        if(result>0){
        JOptionPane.showMessageDialog(this, "Product added successfully!");
        
        }else{
        JOptionPane.showMessageDialog(this, "Failed to add Product.");
        }
        
        }catch(ClassNotFoundException | SQLException ex){
         System.out.println(ex);
        }finally{
            
            try {
                reCall();
            } catch (IOException ex) {
                Logger.getLogger(AddProducts.class.getName()).log(Level.SEVERE, null, ex);
            }
            pname.setText("");
            pmanu.setText("");
            pmodelno.setText("");
            pprice.setText("");
            pqty.setText("");
        }
        
    }//GEN-LAST:event_addstockMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:

            AdminHome info = new AdminHome();
            info.setVisible(true);
            this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                Databaseconfig config = new Databaseconfig();
                new AddProducts(config).setVisible(true);
                
                }catch(IOException ex){
                System.out.println(ex);
                } catch (Exception ex) {
                    Logger.getLogger(AddProducts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addstock;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField pmanu;
    private javax.swing.JTextField pmodelno;
    private javax.swing.JTextField pname;
    private javax.swing.JTextField pprice;
    private javax.swing.JTextField pqty;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
