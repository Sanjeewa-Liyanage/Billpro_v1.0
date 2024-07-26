/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package billsoftware;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author liyan
 */
public class UpdateUsers extends javax.swing.JFrame {

    /**
     * Creates new form UpdateUsers
     */
    private UserDisplayService userdisplayservice;
    private final Databaseconfig dbConfig;
    
    
    public UpdateUsers(Databaseconfig config)throws IOException {
        initComponents();
        this.dbConfig = config;
        reCall();
        
    }
    private void reCall()throws IOException{
    userdisplayservice = new UserDisplayService(dbConfig);
    try{
        DefaultTableModel tblModel = (DefaultTableModel)table1.getModel();
        userdisplayservice.showUsers(tblModel);
        }catch(SQLException ex){
            System.out.print(ex);
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        selectedid = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        username = new javax.swing.JTextField();
        update = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(900, 550));
        setMinimumSize(new java.awt.Dimension(900, 550));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(176, 211, 209));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Update or Delete Users");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 260, 40));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "uid", "username", "role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, 210));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Users");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 150, 40));

        selectedid.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        selectedid.setForeground(new java.awt.Color(0, 0, 0));
        selectedid.setText("Selected Id");
        jPanel1.add(selectedid, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 180, 150, 40));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Password");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, 150, 40));

        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 320, 180, 40));

        username.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 250, 180, 40));

        update.setBackground(new java.awt.Color(46, 109, 223));
        update.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        update.setText("Update User");
        update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateMouseClicked(evt);
            }
        });
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jPanel1.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 390, 150, 40));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Username");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, 150, 40));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Selected Id");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 150, 40));

        delete.setBackground(new java.awt.Color(255, 0, 51));
        delete.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        delete.setText("delete User");
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
        });
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel1.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 450, 150, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseClicked
        // TODO add your handling code here:
       
        
        if(password.getText().toString().equals("")){
         JOptionPane.showMessageDialog(this, "Enter new password.");
        }else{
        try{
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection con = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
         Statement stmt = con.createStatement();
         String sql = "UPDATE users SET username = '" + username.getText() + "', password = '" + password.getText().toString() + "' WHERE uid = '" + selectedid.getText() + "'";
         int rs = stmt.executeUpdate(sql);
         if(rs > 0) {
                JOptionPane.showMessageDialog(this, "User updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update user.");
            }
        }catch(ClassNotFoundException | SQLException ex){
        System.out.println(ex);
        }finally{
        username.setText("");
        password.setText("");
        selectedid.setText("");
            try {
                reCall();
            } catch (IOException ex) {
                Logger.getLogger(UpdateUsers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        }
        
    }//GEN-LAST:event_updateMouseClicked

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
      DefaultTableModel tblModel = (DefaultTableModel)table1.getModel();
      int selectedRowIndex = table1.getSelectedRow();
      
      selectedid.setText(tblModel.getValueAt(selectedRowIndex, 0).toString());
      username.setText(tblModel.getValueAt(selectedRowIndex,1).toString());
      
    }//GEN-LAST:event_table1MouseClicked

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
        // TODO add your handling code here:
         try{
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection con = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
         Statement stmt = con.createStatement();
         String sql = "DELETE FROM users WHERE uid = '" + selectedid.getText() + "'";

         int rs = stmt.executeUpdate(sql);
         if(rs > 0) {
                JOptionPane.showMessageDialog(this, "User deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete user.");
            }
        }catch(ClassNotFoundException | SQLException ex){
        System.out.println(ex);
        }finally{
        username.setText("");
        password.setText("");
        selectedid.setText("");
            try {
                reCall();
            } catch (IOException ex) {
                Logger.getLogger(UpdateUsers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_deleteMouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                 Databaseconfig config = new Databaseconfig();
                  new UpdateUsers(config).setVisible(true);
                }catch(IOException ex){
                System.out.println(ex);
                
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel selectedid;
    private javax.swing.JTable table1;
    private javax.swing.JButton update;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
