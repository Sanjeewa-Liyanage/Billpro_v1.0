/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package billsoftware;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;

/**
 *
 * @author liyan
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form UserHome
     */
    
    //variables declaration
    public String productname;
    public String manufac;
    public String serielno;
    public String modelno;
    public String price;
    public String qty;
    public String pid;
    public double unitprice;
    public double itmqty;
    public double unittotal;
    public int invoiceid;
    public double subtotalt;
    public double discountt;
    public double discountpre;
    public double totalt;
    Timer timer;
    SimpleDateFormat st;
    
    
    private ProductDisplayService productdisplayservice;
    private final Databaseconfig dbConfig;
    
    
    public Home(Databaseconfig config)throws IOException {
        
        initComponents();
        date();
        time();
        this.dbConfig = config;
        reCall();
    }
    //reCall mathod for call to display of data in table this function is used in every class//
    private void reCall()throws IOException{
    productdisplayservice = new ProductDisplayService(dbConfig);
    try{
    DefaultTableModel tblModel = (DefaultTableModel)table2.getModel();
    productdisplayservice.showproducts2(tblModel);
    
    }catch(SQLException ex){
     System.out.print(ex);
    }
    }
    //generate invoice for auto generate invoice 
    private void generateinvoice()throws SQLException{
    try{
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(dbConfig.getUrl(),dbConfig.getUsername(),dbConfig.getPassword());
    Statement stmt = con.createStatement();
    String sql = "SELECT invoice_no FROM invoices ORDER BY invoice_no DESC limit 1";
    ResultSet rs = stmt.executeQuery(sql);
    
    if (rs.next()){
    int invoiceId = rs.getInt("invoice_no");
    invoiceid = invoiceId +1;
    }else{
    invoiceid= 01;
    }
    }catch(Exception ex){
    System.out.println(ex);
    }finally{
    System.out.println(invoiceid);
    }
    }
    
    //separete function for insert data into invoices table
    
    private void insertdata() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbConfig.getUrl(),dbConfig.getUsername(),dbConfig.getPassword());
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO invoices VALUES ('"+invoiceid+"','"+cname.getText()+"','"+cphone.getText()+"','"+datel.getText()+"','"+timel.getText().toString()+"','"+subtotal.getText()+"','"+discount.getText()+"','"+total.getText()+"')";
            int result = stmt.executeUpdate(sql);
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    private void insertproductdata()throws SQLException{
        PreparedStatement statement = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbConfig.getUrl(),dbConfig.getUsername(),dbConfig.getPassword());
            String sql = "INSERT INTO invoice_items (item_id,invoice_no, product_name, manufacturer, model_no, serial_no, quantity, unit_price) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            statement = con.prepareStatement(sql);
            DefaultTableModel tblmodel = (DefaultTableModel) table3.getModel();
            for (int row = 0; row < tblmodel.getRowCount(); row++){
             String productid = (String)tblmodel.getValueAt(row, 6);
             String productname = (String)tblmodel.getValueAt(row, 0);
             String manufacturer = (String)tblmodel.getValueAt(row,7);
             String modelno = (String)tblmodel.getValueAt(row, 1);
             String seriel = (String)tblmodel.getValueAt(row, 2);
             double unitprice = Double.parseDouble(tblmodel.getValueAt(row, 5).toString());
             int qty = Integer.parseInt(tblmodel.getValueAt(row, 3).toString());
             
             
             
             statement.setString(1, productid);
             statement.setInt(2, invoiceid);
             statement.setString(3, productname);
             statement.setString(4,manufacturer);
             statement.setString(5,modelno);
             statement.setString(6, seriel);
             statement.setInt(7,qty);
             statement.setDouble(8, unitprice);
            
             System.out.println(invoiceid);
             statement.addBatch();
            }
            statement.executeBatch();
    
    }catch(Exception e){
        
    System.out.println(e);
    
    }
    
    }
    
    
    private void calculatetotal(){
    for(int i =0; i<table3.getRowCount(); i++){
    double price = Double.parseDouble(table3.getValueAt(i, 5).toString());
    subtotalt+= price;
    
    }
    

    totalt = subtotalt ;
    subtotal.setText(String.format("%.2f", subtotalt));
    total.setText(String.format("%.2f",totalt));
    }

    private void discount(){
    
    if(!discount.getText().isEmpty()){
    discountpre = Double.parseDouble(discount.getText());
    discountt = subtotalt*(discountpre /100);
    
    }
    totalt = subtotalt - discountt;
    subtotal.setText(String.format("%.2f", subtotalt));
    total.setText(String.format("%.2f",totalt));
    
    }
    
    public void date(){
    
    Date date = new Date();
    
    SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd");
    String d = smdf.format(date);
    datel.setText(d);
    }
    
    public void time(){
    
    timer = new Timer(0, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        
        Date dt = new Date();
        st = new SimpleDateFormat("hh:mm:ss a");
        
        String tt = st.format(dt);
        timel.setText(tt);
        }
        
    });
    timer.start();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        timel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cphone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        datel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        pname = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        pmanu = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        pseriel = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        pmodelno = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        pprice = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        pqty = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        btnsearch = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table3 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        subtotal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        discount = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        dis = new javax.swing.JButton();
        btnremove1 = new javax.swing.JButton();
        cal1 = new javax.swing.JButton();
        newbill = new javax.swing.JButton();
        save = new javax.swing.JButton();
        exitsys = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(77, 134, 156));
        setLocation(new java.awt.Point(100, 0));
        setMinimumSize(new java.awt.Dimension(1377, 785));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(176, 211, 209));
        jPanel1.setMaximumSize(new java.awt.Dimension(1200, 600));
        jPanel1.setMinimumSize(new java.awt.Dimension(1200, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        timel.setBackground(new java.awt.Color(77, 134, 156));
        timel.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        timel.setForeground(new java.awt.Color(0, 0, 0));
        timel.setText("time");
        jPanel1.add(timel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 60, 130, 40));

        jLabel2.setBackground(new java.awt.Color(77, 134, 156));
        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Billpro Billing system");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 380, 40));

        cname.setBackground(new java.awt.Color(122, 178, 178));
        cname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnameActionPerformed(evt);
            }
        });
        jPanel1.add(cname, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 200, 30));

        jLabel3.setBackground(new java.awt.Color(77, 134, 156));
        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Customer Name :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 130, 30));

        cphone.setBackground(new java.awt.Color(122, 178, 178));
        cphone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cphoneActionPerformed(evt);
            }
        });
        jPanel1.add(cphone, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 200, 30));

        jLabel4.setBackground(new java.awt.Color(77, 134, 156));
        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Phone number :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 130, 30));

        datel.setBackground(new java.awt.Color(77, 134, 156));
        datel.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        datel.setForeground(new java.awt.Color(0, 0, 0));
        datel.setText("date");
        jPanel1.add(datel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 20, 130, 40));

        jPanel2.setBackground(new java.awt.Color(176, 211, 209));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setBackground(new java.awt.Color(77, 134, 156));
        jLabel9.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Product Name :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 130, 30));

        pname.setBackground(new java.awt.Color(122, 178, 178));
        pname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnameActionPerformed(evt);
            }
        });
        jPanel2.add(pname, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 170, 30));

        jLabel10.setBackground(new java.awt.Color(77, 134, 156));
        jLabel10.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Manufatrurer :");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 130, 30));

        pmanu.setBackground(new java.awt.Color(122, 178, 178));
        pmanu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmanuActionPerformed(evt);
            }
        });
        jPanel2.add(pmanu, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 170, 30));

        jLabel11.setBackground(new java.awt.Color(77, 134, 156));
        jLabel11.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Seriel No :");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 130, 30));

        pseriel.setBackground(new java.awt.Color(122, 178, 178));
        pseriel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pserielActionPerformed(evt);
            }
        });
        jPanel2.add(pseriel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 170, 30));

        jLabel12.setBackground(new java.awt.Color(77, 134, 156));
        jLabel12.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Model No :");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 130, 30));

        pmodelno.setBackground(new java.awt.Color(122, 178, 178));
        pmodelno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmodelnoActionPerformed(evt);
            }
        });
        jPanel2.add(pmodelno, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 170, 30));

        jLabel13.setBackground(new java.awt.Color(77, 134, 156));
        jLabel13.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Unit Price :");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 130, 30));

        pprice.setBackground(new java.awt.Color(122, 178, 178));
        pprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppriceActionPerformed(evt);
            }
        });
        jPanel2.add(pprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 40, 170, 30));

        jLabel14.setBackground(new java.awt.Color(77, 134, 156));
        jLabel14.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Quantity :");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 130, 30));

        pqty.setBackground(new java.awt.Color(122, 178, 178));
        pqty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pqtyActionPerformed(evt);
            }
        });
        jPanel2.add(pqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 40, 170, 30));

        add.setBackground(new java.awt.Color(157, 222, 139));
        add.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        add.setForeground(new java.awt.Color(0, 0, 0));
        add.setText("Add");
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
        });
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel2.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 30, 140, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 1350, 140));

        jPanel3.setBackground(new java.awt.Color(176, 211, 209));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setBackground(new java.awt.Color(77, 134, 156));
        jLabel15.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Search :");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 70, 30));

        search.setBackground(new java.awt.Color(122, 178, 178));
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        jPanel3.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 170, 30));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "productid", "product name", "manufacturer", "model no", "price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table2);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 440, 170));

        btnsearch.setBackground(new java.awt.Color(0, 51, 255));
        btnsearch.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        btnsearch.setForeground(new java.awt.Color(0, 0, 0));
        btnsearch.setText("Search");
        btnsearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsearchMouseClicked(evt);
            }
        });
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });
        jPanel3.add(btnsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 120, 40));

        btnclear.setBackground(new java.awt.Color(232, 141, 103));
        btnclear.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        btnclear.setForeground(new java.awt.Color(0, 0, 0));
        btnclear.setText("Clear");
        btnclear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnclearMouseClicked(evt);
            }
        });
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });
        jPanel3.add(btnclear, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 140, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 460, 330));

        jPanel4.setBackground(new java.awt.Color(176, 211, 209));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "product name", "model no", "seriel no", "qty", "productprice", "product total", "productid", "manufacture"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table3);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 860, 200));

        jLabel16.setBackground(new java.awt.Color(77, 134, 156));
        jLabel16.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Subtotal :");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, 130, 30));

        subtotal.setBackground(new java.awt.Color(122, 178, 178));
        subtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subtotalActionPerformed(evt);
            }
        });
        jPanel4.add(subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 210, 170, 30));

        jLabel17.setBackground(new java.awt.Color(77, 134, 156));
        jLabel17.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Discount :");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 250, 130, 30));

        discount.setBackground(new java.awt.Color(122, 178, 178));
        discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountActionPerformed(evt);
            }
        });
        jPanel4.add(discount, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 250, 170, 30));

        jLabel18.setBackground(new java.awt.Color(77, 134, 156));
        jLabel18.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Total :");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 290, 130, 30));

        total.setBackground(new java.awt.Color(122, 178, 178));
        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });
        jPanel4.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 290, 170, 30));

        dis.setBackground(new java.awt.Color(51, 102, 255));
        dis.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        dis.setForeground(new java.awt.Color(0, 0, 0));
        dis.setText("Add discount");
        dis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disMouseClicked(evt);
            }
        });
        dis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disActionPerformed(evt);
            }
        });
        jPanel4.add(dis, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 280, 130, 40));

        btnremove1.setBackground(new java.awt.Color(232, 141, 103));
        btnremove1.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        btnremove1.setForeground(new java.awt.Color(0, 0, 0));
        btnremove1.setText("remove");
        btnremove1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnremove1MouseClicked(evt);
            }
        });
        btnremove1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnremove1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnremove1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 140, 50));

        cal1.setBackground(new java.awt.Color(65, 176, 59));
        cal1.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        cal1.setForeground(new java.awt.Color(0, 0, 0));
        cal1.setText("Calculate");
        cal1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cal1MouseClicked(evt);
            }
        });
        cal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cal1ActionPerformed(evt);
            }
        });
        jPanel4.add(cal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 140, 50));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, 880, 330));

        newbill.setBackground(new java.awt.Color(128, 188, 189));
        newbill.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        newbill.setForeground(new java.awt.Color(0, 0, 0));
        newbill.setText("New");
        newbill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newbillMouseClicked(evt);
            }
        });
        newbill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newbillActionPerformed(evt);
            }
        });
        jPanel1.add(newbill, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 690, 140, 50));

        save.setBackground(new java.awt.Color(65, 176, 59));
        save.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        save.setForeground(new java.awt.Color(0, 0, 0));
        save.setText("Save");
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveMouseClicked(evt);
            }
        });
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel1.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 690, 140, 50));

        exitsys.setBackground(new java.awt.Color(232, 141, 103));
        exitsys.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 24)); // NOI18N
        exitsys.setForeground(new java.awt.Color(0, 0, 0));
        exitsys.setText("Exit");
        exitsys.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitsysMouseClicked(evt);
            }
        });
        exitsys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitsysActionPerformed(evt);
            }
        });
        jPanel1.add(exitsys, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 690, 140, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1380, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cnameActionPerformed

    private void cphoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cphoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cphoneActionPerformed

    private void pnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnameActionPerformed

    private void pmanuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmanuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pmanuActionPerformed

    private void pserielActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pserielActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pserielActionPerformed

    private void pmodelnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmodelnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pmodelnoActionPerformed

    private void ppriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ppriceActionPerformed

    private void pqtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pqtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pqtyActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsearchActionPerformed

    private void subtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subtotalActionPerformed

    private void discountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discountActionPerformed

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalActionPerformed

    private void newbillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newbillActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newbillActionPerformed

    private void disActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_disActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveActionPerformed

    private void exitsysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitsysActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exitsysActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnclearActionPerformed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        // TODO add your handling code here:
        DefaultTableModel tblModel = (DefaultTableModel)table2.getModel();
        int selectedRowIndex = table2.getSelectedRow();
        pid = tblModel.getValueAt(selectedRowIndex,0).toString();
        pname.setText(tblModel.getValueAt(selectedRowIndex,1).toString());
        pmanu.setText(tblModel.getValueAt(selectedRowIndex,2).toString());
        pmodelno.setText(tblModel.getValueAt(selectedRowIndex,3).toString());
        pprice.setText(tblModel.getValueAt(selectedRowIndex,4).toString());
    }//GEN-LAST:event_table2MouseClicked

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        // TODO add your handling code here:
        if(pqty.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Enter quantity!");
        }else{
        
        DefaultTableModel tblModel2 = (DefaultTableModel)table3.getModel();
        productname = pname.getText();
        manufac = pmanu.getText();
        serielno = pseriel.getText();
        modelno = pmodelno.getText();
        price = pprice.getText();
        qty = pqty.getText();
        unitprice = Double.parseDouble(price);
        itmqty = Integer.parseInt(qty);
        unittotal = (double) (itmqty*unitprice);
        String unittotalS = String.valueOf(unittotal);
        String tbData[] ={productname,modelno,serielno,qty,price,unittotalS,pid,manufac};
        tblModel2.addRow(tbData);
        
        }
    }//GEN-LAST:event_addMouseClicked

    private void saveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMouseClicked
        try {
            // TODO add your handling code here:
            generateinvoice();
            insertdata();
            insertproductdata();
            
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveMouseClicked

    private void btnremove1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremove1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnremove1ActionPerformed

    private void disMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disMouseClicked
        // TODO add your handling code here:
        discount();
        
    }//GEN-LAST:event_disMouseClicked

    private void cal1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cal1MouseClicked
        // TODO add your handling code here:
        calculatetotal();
    }//GEN-LAST:event_cal1MouseClicked

    private void cal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cal1ActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_cal1ActionPerformed

    private void btnsearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsearchMouseClicked
        // TODO add your handling code here:
        try{
        
        DefaultTableModel tblModel = (DefaultTableModel)table2.getModel();
        tblModel.setRowCount(0);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(dbConfig.getUrl(),dbConfig.getUsername(),dbConfig.getPassword());
        Statement stmt = con.createStatement();
        String sql ="SELECT * FROM products WHERE productid = '"+search.getText()+"' OR productname = '"+search.getText()+"' OR manufacturer = '"+search.getText()+"' OR modelno = '"+search.getText()+"'";
        ResultSet rs = stmt.executeQuery(sql);
        
        while(rs.next()){
        String pid = rs.getString("productid");
        String Pname = rs.getString("productname");
        String manu = rs.getString("manufacturer");
        String modelNo = rs.getString("modelno");
        String price = rs.getString("price");
    
    
        String tbData[]= {pid,Pname,manu,modelNo,price};
        tblModel.addRow(tbData);
        
        }
        
        
        }catch(Exception e){
        System.out.println(e);
        
        }finally{
        search.setText("");
        
        }
    }//GEN-LAST:event_btnsearchMouseClicked

    private void btnclearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnclearMouseClicked
        try {
            // TODO add your handling code here:
            reCall();
            search.setText("");
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnclearMouseClicked

    private void btnremove1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnremove1MouseClicked
           // TODO add your handling code here:
           DefaultTableModel tblModel = (DefaultTableModel)table3.getModel();
           int selectedRow = table3.getSelectedRow();
           tblModel.removeRow(selectedRow);
           
    }//GEN-LAST:event_btnremove1MouseClicked

    private void exitsysMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitsysMouseClicked
        // TODO add your handling code here:
        try{
        Databaseconfig config = new Databaseconfig();
        Login info = new Login(config);
        info.setVisible(true);
        this.dispose();
        
        }catch(IOException ex){
            System.out.println(ex);
        
        } catch (Exception ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_exitsysMouseClicked

    private void newbillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newbillMouseClicked
        // TODO add your handling code here:
        try{
        Databaseconfig config = new Databaseconfig();
        Home info = new Home(config);
        info.setVisible(true);
        this.dispose();
        
        }catch(Exception ex){
        
        System.out.println(ex);
        }
        
    }//GEN-LAST:event_newbillMouseClicked
    
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               try{
               Databaseconfig config = new Databaseconfig();
                new Home(config).setVisible(true);
                
               }catch(IOException ex){
               System.out.println(ex);
               } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btnremove1;
    private javax.swing.JButton btnsearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cal1;
    private javax.swing.JTextField cname;
    private javax.swing.JTextField cphone;
    private javax.swing.JLabel datel;
    private javax.swing.JButton dis;
    private javax.swing.JTextField discount;
    private javax.swing.JButton exitsys;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton newbill;
    private javax.swing.JTextField pmanu;
    private javax.swing.JTextField pmodelno;
    private javax.swing.JTextField pname;
    private javax.swing.JTextField pprice;
    private javax.swing.JTextField pqty;
    private javax.swing.JTextField pseriel;
    private javax.swing.JButton save;
    private javax.swing.JTextField search;
    private javax.swing.JTextField subtotal;
    private javax.swing.JTable table2;
    private javax.swing.JTable table3;
    private javax.swing.JLabel timel;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
}
