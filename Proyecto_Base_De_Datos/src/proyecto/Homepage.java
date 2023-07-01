/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto;

import bdproyectofile.ConexionDB;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import bdproyectofile.Enfermera;
import bdproyectofile.GetBloodPerType;
import bdproyectofile.blood_total;
import bdproyectofile.AutoConnection;
import bdproyectofile.GetAllPcts;
import bdproyectofile.Paciente;
import bdproyectofile.GetAllMuestraPctsBD;
import bdproyectofile.Paciente_muestra;
import java.sql.Connection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author MATHIAS
 */
public class Homepage extends javax.swing.JFrame {
    private Enfermera currentUser;
    private List<blood_total> preload_bt;
    private List<Paciente> inPatientTable;
    private List<Paciente_muestra> inPatientMstTable;
    private DefaultTableModel PatientTable;
    private DefaultTableModel PatientMstsTable;
    /**
     * Creates new form Homepage
     */
   
    public Homepage() {
        
        initComponents();
        this.setLocationRelativeTo(this);
        SetImageLabel(jLabel3,"src/images/fondo2.jpg");
        SetImageLabel(jLabel7,"src/images/corazon.png");
        SetImageLabel(jLabel4,"src/images/Inline.png");
        SetImageLabel(jLabel8,"src/images/Primary50.png");
        SetImageLabel(jLabel9,"src/images/profile.png");
        SetImageLabel(jLabel10,"src/images/Barra.png");
        SetImageLabel(jLabel27,"src/images/Verde.png");
        SetImageLabel(jLabel38,"src/images/barra2.png");
        SetImageLabel(jLabel39,"src/images/fondo53.jpg");
        SetImageLabel(jLabel43,"src/images/barra3.png"); 
        SetImageLabel(jLabel41,"src/images/barrita.png"); 
        SetImageLabel(jLabel45,"src/images/barra3.png");
        SetImageLabel(jLabel49,"src/images/barrita.png");
        SetImageLabel(jLabel30,"src/images/FondoTab5.jpg");
        SetImageLabel(jLabel50,"src/images/barra3.png");
        SetImageLabel(jLabel52,"src/images/Verde.png");
        SetImageLabel(jLabel31,"src/images/fondotab6.jpg");
        SetImageLabel(jLabel53,"src/images/Barra.png");
        jComboBox3.setVisible(false);
        jComboBox5.setVisible(false);
        jTextField9.setVisible(false);
        jTextField8.setVisible(false);
        
        
        jTabbedPane1.setVisible(true);
        this.LoadTableModelPacienteMuestras();
    }
    
    public Homepage(Enfermera currentUser) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.jLabel2.setText(currentUser.getNombre()+" "+currentUser.getApellido());
        this.ChargeBloodTotal();
        SetImageLabel(jLabel3,"src/images/fondo2.jpg");
        SetImageLabel(jLabel7,"src/images/corazon.png");
        SetImageLabel(jLabel4,"src/images/Inline.png");
        SetImageLabel(jLabel8,"src/images/Primary50.png");
        SetImageLabel(jLabel9,"src/images/profile.png");
        SetImageLabel(jLabel10,"src/images/Barra.png");
        SetImageLabel(jLabel27,"src/images/Verde.png");
        SetImageLabel(jLabel38,"src/images/barra2.png");
        SetImageLabel(jLabel39,"src/images/fondo53.jpg");
        SetImageLabel(jLabel43,"src/images/barra3.png"); 
        
        jTabbedPane1.setVisible(true);
    }
    public void chargePatientsMsts(){
        Connection test = new AutoConnection("sqlproject_","Oracle19c").getConnection();
        GetAllMuestraPctsBD toTablePm = new GetAllMuestraPctsBD(test);
        this.inPatientMstTable = toTablePm.GetPctsMstList();
    }
    private void removerows(List<?> obj, DefaultTableModel tbl){
        int size = obj.size();
        size --;
        for (var i : obj){
            tbl.removeRow(size);
            size--;
        }
    }
    private void LoadTableModelPacienteMuestras(){
      //  if (this.inPatientMstTable!=null){
            //this.removerows(inPatientMstTable,PatientMstsTable);
         //   this.inPatientMstTable.clear();
        if (this.inPatientMstTable!=null){
            PatientMstsTable.setRowCount(0);
        }
        this.PatientMstsTable = (DefaultTableModel)this.jTable2.getModel();
        this.chargePatientsMsts();
        String estate;
        for (var i : inPatientMstTable){
            Object PctsMs[];
            if (i.muestra.getEstado().equals("En espera")){
                estate = "Pendiente";
                PctsMs = new Object[]{i.getDNI(),i.getName(),i.getSecondName(),i.muestra.getIdtiposange(),estate}; 
            }else{
                PctsMs = new Object[]{i.getDNI(),i.getName(),i.getSecondName(),i.muestra.getIdtiposange(),i.muestra.getEstado()}; 
            }
            
            PatientMstsTable.addRow(PctsMs);
        }
    }
    public void chargePatients(){
        Connection test = new AutoConnection("sqlproject_","Oracle19c").getConnection();
        GetAllPcts pcts_Dat = new GetAllPcts(test);
        this.inPatientTable = pcts_Dat.GetPctsList();    
    }
    
    private void LoadTableModelPaciente(){
        if (this.inPatientTable!=null){
            PatientTable.setRowCount(0);
        }
        this.chargePatients();
        this.PatientTable = (DefaultTableModel)this.jTable3.getModel();
        for (var i : inPatientTable){
            Object Pcts[] = new Object[]{i.getDNI(),i.getName(),i.getSecondName()}; 
            PatientTable.addRow(Pcts);
        }
    }
    private void ChargeBloodTotal(){
        ConexionDB connect_dat= new ConexionDB();
        Connection test = null;
        connect_dat.setData("sqlproject_","Oracle19c");
        try{
            test = connect_dat.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        GetBloodPerType tb_con= new  GetBloodPerType(test);
        for (var i : tb_con.GetTotalBloodList()){
            System.out.println(i.total);
        }
        this.preload_bt = tb_con.GetTotalBloodList();
        JLabel[] labels = {this.AposTotal,this.AnegTotal ,this.BposTotal,this.BnegTotal
        ,this.ABposTotal,this.ABnegTotal,this.OposTotal,this.OnegTotal};
        int it = 0;
        for (var i : preload_bt){
            if (i != null){
                labels[it].setText(i.total+"LT");
            }
            it++;
        }
    
        
    }
    
private void SetImageLabel(JLabel labelName, String root){
               ImageIcon image = new ImageIcon(root);
               Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
               labelName.setIcon(icon);
               this.repaint();
    }
    private void SetbuttonImage(JButton ButtonName, String root){
               ImageIcon image = new ImageIcon(root);
               Icon icon = new ImageIcon(image.getImage().getScaledInstance(ButtonName.getWidth(), ButtonName.getHeight(), Image.SCALE_DEFAULT));
               ButtonName.setIcon(icon);
               this.repaint();
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
        jLabel38 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        AnegTotal = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        BposTotal = new javax.swing.JLabel();
        BnegTotal = new javax.swing.JLabel();
        ABposTotal = new javax.swing.JLabel();
        ABnegTotal = new javax.swing.JLabel();
        OposTotal = new javax.swing.JLabel();
        OnegTotal = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        AposTotal = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField9 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jTextField17 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jTextField21 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jComboBox11 = new javax.swing.JComboBox<>();
        jComboBox13 = new javax.swing.JComboBox<>();
        jComboBox12 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jTextField20 = new javax.swing.JTextField();
        jComboBox10 = new javax.swing.JComboBox<>();
        jLabel63 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel54 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel38.setBackground(new java.awt.Color(255, 255, 255));
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Barra.png"))); // NOI18N
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 940, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 1, 65)); // NOI18N
        jLabel5.setText("Banco de");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 270, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 1, 60)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("Sangre");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/carnet-de-donante-de-organos.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 60, 60));

        jButton8.setBackground(new java.awt.Color(68, 97, 242));
        jButton8.setFont(new java.awt.Font("Gill Sans MT", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Reportes");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 670, 240, 40));

        jButton1.setBackground(new java.awt.Color(68, 97, 242));
        jButton1.setFont(new java.awt.Font("Gill Sans MT", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Personas");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 620, 240, 40));

        jButton2.setBackground(new java.awt.Color(68, 97, 242));
        jButton2.setFont(new java.awt.Font("Gill Sans MT", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Citas");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 240, 40));

        jButton3.setBackground(new java.awt.Color(68, 97, 242));
        jButton3.setFont(new java.awt.Font("Gill Sans MT", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Muestras de Sangre");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, 240, 40));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Miguel Enrique Perez");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 210, 20));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profile.png"))); // NOI18N
        jLabel9.setText("FOTO REDONDA DEL ENFERMERO O DOCTOR");
        jLabel9.setToolTipText("");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 270, 225, 225));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Primary50.png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 262, 240, 240));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Inline.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 215, 10, 10));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondo2.jpg"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 720));

        jButton11.setBackground(new java.awt.Color(68, 97, 242));
        jButton11.setFont(new java.awt.Font("Gill Sans MT", 1, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Citas");
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 240, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 720));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setVisible(false);
        jPanel3.setBackground(new java.awt.Color(229, 229, 229));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton7.setBackground(new java.awt.Color(68, 97, 242));
        jButton7.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Mostrar Pacientes");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 380, 240, 50));

        jLabel44.setFont(new java.awt.Font("Tw Cen MT", 1, 23)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Citas");
        jPanel3.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 70, 40));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barra3.png"))); // NOI18N
        jPanel3.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 570, 40));

        jTextField5.setBackground(new java.awt.Color(234, 240, 247));
        jTextField5.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(79, 85, 90));
        jTextField5.setText("Cantidad De Sangre (Litros)");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 240, 240, 50));

        jTextField2.setBackground(new java.awt.Color(234, 240, 247));
        jTextField2.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(79, 85, 90));
        jTextField2.setText("Dni Médico/Enfermero");
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField2MouseClicked(evt);
            }
        });
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 570, 50));

        jTextField4.setBackground(new java.awt.Color(234, 240, 247));
        jTextField4.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(79, 85, 90));
        jTextField4.setText("N° de Laboratorio");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, 240, 50));

        AnegTotal.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        AnegTotal.setForeground(new java.awt.Color(244, 36, 149));
        AnegTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AnegTotal.setText("50 Litros");
        jPanel3.add(AnegTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 150, 30));

        jButton4.setBackground(new java.awt.Color(98, 0, 174));
        jButton4.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Generar Cita");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, 240, 50));

        BposTotal.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        BposTotal.setForeground(new java.awt.Color(24, 194, 205));
        BposTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BposTotal.setText("120 Litros");
        jPanel3.add(BposTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 150, 30));

        BnegTotal.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        BnegTotal.setForeground(new java.awt.Color(24, 115, 205));
        BnegTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BnegTotal.setText("80 Litros");
        jPanel3.add(BnegTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 150, 30));

        ABposTotal.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        ABposTotal.setForeground(new java.awt.Color(93, 117, 131));
        ABposTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ABposTotal.setText("90 Litros");
        jPanel3.add(ABposTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 150, 30));

        ABnegTotal.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        ABnegTotal.setForeground(new java.awt.Color(51, 171, 48));
        ABnegTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ABnegTotal.setText("72 Litros");
        jPanel3.add(ABnegTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 460, 150, 30));

        OposTotal.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        OposTotal.setForeground(new java.awt.Color(231, 235, 31));
        OposTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OposTotal.setText("42 Litros");
        jPanel3.add(OposTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, 150, 30));

        OnegTotal.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        OnegTotal.setForeground(new java.awt.Color(243, 13, 13));
        OnegTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OnegTotal.setText("18 litros");
        jPanel3.add(OnegTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 600, 150, 30));

        jLabel40.setBackground(new java.awt.Color(234, 240, 247));
        jLabel40.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(79, 85, 90));
        jLabel40.setText("Tipo de Cita");
        jLabel40.setToolTipText("");
        jPanel3.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 130, 50));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barrita.png"))); // NOI18N
        jLabel41.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true));
        jPanel3.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 240, 50));

        jComboBox1.setBackground(new java.awt.Color(234, 240, 247));
        jComboBox1.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(79, 85, 90));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Donación de Sangre", "Extracción de Muestra" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 170, 240, 50));

        AposTotal.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        AposTotal.setForeground(new java.awt.Color(244, 149, 36));
        AposTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AposTotal.setText("100 Litros");
        AposTotal.setToolTipText("");
        AposTotal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        AposTotal.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                AposTotalComponentAdded(evt);
            }
        });
        jPanel3.add(AposTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 150, 30));

        jLabel29.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Sangre");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 25, -1, -1));

        jTextField6.setBackground(new java.awt.Color(234, 240, 247));
        jTextField6.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(79, 85, 90));
        jTextField6.setText("Dni Paciente");
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 240, 50));

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(33, 203, 103));
        jLabel28.setText("Litros de");
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 25, -1, -1));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Verde.png"))); // NOI18N
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 25, 100, 30));

        jLabel26.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("O+");
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 534, -1, -1));

        jLabel25.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("O-");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 604, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("AB-");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 464, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Nombre", "Apellido", "Tipo de Sangre", "Estado"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 560, 190));

        jLabel23.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("AB+");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 394, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("B-");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 324, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("B+");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 254, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("A-");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 183, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("A+");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 114, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Barra.png"))); // NOI18N
        jLabel10.setText("Disponibilidad de Sangre");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 210, 40));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Label9.png"))); // NOI18N
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 590, 215, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Label1.png"))); // NOI18N
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 215, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Label2.png"))); // NOI18N
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 215, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Label3.png"))); // NOI18N
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 215, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Label5.png"))); // NOI18N
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 215, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Label6.png"))); // NOI18N
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 215, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Label7.png"))); // NOI18N
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 215, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Label8.png"))); // NOI18N
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 215, -1));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondo53.jpg"))); // NOI18N
        jPanel3.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, -4, 950, 700));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barra3.png"))); // NOI18N
        jPanel3.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 20, 255, 40));

        jTabbedPane1.addTab("tab1", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setBackground(new java.awt.Color(98, 0, 174));
        jButton6.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Realizar Cambio");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 560, 270, 50));

        jLabel48.setFont(new java.awt.Font("Tw Cen MT", 1, 23)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(79, 85, 90));
        jLabel48.setText("Modificar Muestra De Sangre");
        jPanel4.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, 280, 30));

        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barrita.png"))); // NOI18N
        jLabel49.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true));
        jPanel4.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 880, 50));

        jComboBox6.setBackground(new java.awt.Color(234, 240, 247));
        jComboBox6.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jComboBox6.setForeground(new java.awt.Color(79, 85, 90));
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aprobar Muestra", "Rechazar Muestra" }));
        jPanel4.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 490, 270, 50));

        jComboBox4.setBackground(new java.awt.Color(234, 240, 247));
        jComboBox4.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(79, 85, 90));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
        jPanel4.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 490, 270, 50));

        jComboBox3.setBackground(new java.awt.Color(234, 240, 247));
        jComboBox3.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(79, 85, 90));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-", "N.A" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 270, 50));

        jComboBox5.setBackground(new java.awt.Color(234, 240, 247));
        jComboBox5.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jComboBox5.setForeground(new java.awt.Color(79, 85, 90));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Aprobado", "Rechazado", "Pendiente" }));
        jPanel4.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 270, 50));

        jTextField9.setBackground(new java.awt.Color(234, 240, 247));
        jTextField9.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(79, 85, 90));
        jTextField9.setText("Fecha");
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 270, 50));

        jTextField8.setBackground(new java.awt.Color(234, 240, 247));
        jTextField8.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(79, 85, 90));
        jTextField8.setText("DNI");
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 270, 50));

        jComboBox2.setBackground(new java.awt.Color(234, 240, 247));
        jComboBox2.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(79, 85, 90));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "DNI", "Tipo de Sangre", "Fecha", "Estado" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 70, 270, 50));

        jTextField7.setBackground(new java.awt.Color(234, 240, 247));
        jTextField7.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(79, 85, 90));
        jTextField7.setText("DNI");
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 270, 50));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "DNI", "Tipo de Sangre", "Fecha", "Estado"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 880, 270));

        jButton5.setBackground(new java.awt.Color(98, 0, 174));
        jButton5.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Mostrar Muestras de Sangre");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 270, 50));

        jLabel47.setFont(new java.awt.Font("Tw Cen MT", 1, 23)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Muestras de Sangre");
        jPanel4.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 190, 40));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barra3.png"))); // NOI18N
        jPanel4.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 920, 40));

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondo52.jpg"))); // NOI18N
        jPanel4.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 690));

        jTabbedPane1.addTab("tab2", jPanel4);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox8.setBackground(new java.awt.Color(234, 240, 247));
        jComboBox8.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jComboBox8.setForeground(new java.awt.Color(79, 85, 90));
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "DNI", "Nombre", "Apellido" }));
        jComboBox8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox8ItemStateChanged(evt);
            }
        });
        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox8ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 270, 50));

        jTextField15.setBackground(new java.awt.Color(234, 240, 247));
        jTextField15.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField15.setForeground(new java.awt.Color(79, 85, 90));
        jTextField15.setText("Nombre ");
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 60, 270, 50));

        jTextField16.setBackground(new java.awt.Color(234, 240, 247));
        jTextField16.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField16.setForeground(new java.awt.Color(79, 85, 90));
        jTextField16.setText("Apellido");
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 330, 270, 50));

        jButton10.setBackground(new java.awt.Color(68, 97, 242));
        jButton10.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Buscar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, 270, 50));

        jTextField17.setBackground(new java.awt.Color(234, 240, 247));
        jTextField17.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField17.setForeground(new java.awt.Color(79, 85, 90));
        jTextField17.setText("Dni Paciente");
        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 330, 270, 50));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Nombre", "Apellido"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 890, 270));

        jComboBox7.setBackground(new java.awt.Color(234, 240, 247));
        jComboBox7.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jComboBox7.setForeground(new java.awt.Color(79, 85, 90));
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Agregar", "Modificar", "Eliminar" }));
        jComboBox7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox7ItemStateChanged(evt);
            }
        });
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 270, 50));

        jLabel55.setFont(new java.awt.Font("Tw Cen MT", 1, 23)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("   Mostrar Personas");
        jPanel5.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 190, 40));

        jButton9.setBackground(new java.awt.Color(68, 97, 242));
        jButton9.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Enviar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 200, 270, 50));

        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barra3.png"))); // NOI18N
        jPanel5.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 890, 40));

        jTextField14.setBackground(new java.awt.Color(234, 240, 247));
        jTextField14.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField14.setForeground(new java.awt.Color(79, 85, 90));
        jTextField14.setText("Nuevo Apellido");
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, 270, 50));

        jTextField13.setBackground(new java.awt.Color(234, 240, 247));
        jTextField13.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField13.setForeground(new java.awt.Color(79, 85, 90));
        jTextField13.setText("Nuevo Nombre");
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 130, 270, 50));

        jTextField12.setBackground(new java.awt.Color(234, 240, 247));
        jTextField12.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField12.setForeground(new java.awt.Color(79, 85, 90));
        jTextField12.setText("Apellido");
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 270, 50));

        jTextField11.setBackground(new java.awt.Color(234, 240, 247));
        jTextField11.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField11.setForeground(new java.awt.Color(79, 85, 90));
        jTextField11.setText("Nombre ");
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 330, 270, 50));

        jTextField10.setBackground(new java.awt.Color(234, 240, 247));
        jTextField10.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField10.setForeground(new java.awt.Color(79, 85, 90));
        jTextField10.setText("Dni Paciente");
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 270, 50));

        jLabel51.setFont(new java.awt.Font("Tw Cen MT", 1, 23)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("       Personas");
        jPanel5.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 190, 40));

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barra3.png"))); // NOI18N
        jPanel5.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 880, 40));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Fotoo.jpg"))); // NOI18N
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 690));

        jTabbedPane1.addTab("tab3", jPanel5);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField21.setBackground(new java.awt.Color(234, 240, 247));
        jTextField21.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField21.setForeground(new java.awt.Color(79, 85, 90));
        jTextField21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField21ActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, 270, 50));

        jButton12.setBackground(new java.awt.Color(68, 97, 242));
        jButton12.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Mostrar");
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton12MouseClicked(evt);
            }
        });
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 350, 270, 50));

        jComboBox11.setBackground(new java.awt.Color(234, 240, 247));
        jComboBox11.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jComboBox11.setForeground(new java.awt.Color(79, 85, 90));
        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Donación de Sangre", "Extracción de Muestra" }));
        jComboBox11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox11ItemStateChanged(evt);
            }
        });
        jComboBox11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox11ActionPerformed(evt);
            }
        });
        jPanel6.add(jComboBox11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 270, 50));

        jComboBox13.setBackground(new java.awt.Color(234, 240, 247));
        jComboBox13.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jComboBox13.setForeground(new java.awt.Color(79, 85, 90));
        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "ID", "Nombre", "Apellido" }));
        jComboBox13.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox13ItemStateChanged(evt);
            }
        });
        jComboBox13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox13ActionPerformed(evt);
            }
        });
        jPanel6.add(jComboBox13, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 270, 50));

        jComboBox12.setBackground(new java.awt.Color(234, 240, 247));
        jComboBox12.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jComboBox12.setForeground(new java.awt.Color(79, 85, 90));
        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "ID", "Nombre", "Apellido" }));
        jComboBox12.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox12ItemStateChanged(evt);
            }
        });
        jComboBox12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox12ActionPerformed(evt);
            }
        });
        jPanel6.add(jComboBox12, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 270, 50));

        jComboBox9.setBackground(new java.awt.Color(234, 240, 247));
        jComboBox9.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jComboBox9.setForeground(new java.awt.Color(79, 85, 90));
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Bioquímica", "Microbiología", "Inmunología", "Genética", "Farmacología", "Hematología", "Virología", "Patología", "Toxicología", "Epidemiología", "Biología-Molecular", "Química", "Analítica", "Anatomía", "Patológica", "Citología", "Biomedicina", "Bioinformática", "Neurociencia", "Biotecnología", "Fisiología", "Investigación" }));
        jComboBox9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox9ItemStateChanged(evt);
            }
        });
        jComboBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox9ActionPerformed(evt);
            }
        });
        jPanel6.add(jComboBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 270, 50));

        jTextField20.setBackground(new java.awt.Color(234, 240, 247));
        jTextField20.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jTextField20.setForeground(new java.awt.Color(79, 85, 90));
        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField20, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 280, 270, 50));

        jComboBox10.setBackground(new java.awt.Color(234, 240, 247));
        jComboBox10.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jComboBox10.setForeground(new java.awt.Color(79, 85, 90));
        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-", "N.A" }));
        jComboBox10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox10ItemStateChanged(evt);
            }
        });
        jComboBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox10ActionPerformed(evt);
            }
        });
        jPanel6.add(jComboBox10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 270, 50));

        jLabel63.setBackground(new java.awt.Color(255, 255, 255));
        jLabel63.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("       Enfermera");
        jLabel63.setToolTipText("");
        jPanel6.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 130, 50));

        jLabel61.setBackground(new java.awt.Color(68, 97, 242));
        jLabel61.setForeground(new java.awt.Color(68, 97, 242));
        jLabel61.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true));
        jLabel61.setOpaque(true);
        jPanel6.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 240, 50));

        jLabel65.setBackground(new java.awt.Color(255, 255, 255));
        jLabel65.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("    Tipo de Sangre");
        jLabel65.setToolTipText("");
        jPanel6.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 130, 50));

        jLabel64.setBackground(new java.awt.Color(255, 255, 255));
        jLabel64.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("         Paciente");
        jLabel64.setToolTipText("");
        jPanel6.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 130, 50));

        jLabel62.setBackground(new java.awt.Color(255, 255, 255));
        jLabel62.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("     Laboratorios");
        jLabel62.setToolTipText("");
        jPanel6.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 130, 50));

        jLabel60.setBackground(new java.awt.Color(68, 97, 242));
        jLabel60.setForeground(new java.awt.Color(68, 97, 242));
        jLabel60.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true));
        jLabel60.setOpaque(true);
        jPanel6.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 240, 50));

        jLabel59.setBackground(new java.awt.Color(68, 97, 242));
        jLabel59.setForeground(new java.awt.Color(68, 97, 242));
        jLabel59.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true));
        jLabel59.setOpaque(true);
        jPanel6.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 240, 50));

        jLabel58.setBackground(new java.awt.Color(68, 97, 242));
        jLabel58.setForeground(new java.awt.Color(68, 97, 242));
        jLabel58.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true));
        jLabel58.setOpaque(true);
        jPanel6.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 240, 50));

        jLabel57.setBackground(new java.awt.Color(255, 255, 255));
        jLabel57.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("            Citas");
        jLabel57.setToolTipText("");
        jPanel6.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 130, 50));

        jLabel56.setBackground(new java.awt.Color(68, 97, 242));
        jLabel56.setForeground(new java.awt.Color(68, 97, 242));
        jLabel56.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true));
        jLabel56.setOpaque(true);
        jPanel6.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 240, 50));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Cita", "Tipo de Cita", "Nombre de Laboratorio", "ID Enfermera", "Nombre de Enfermera", "DNI Paciente", "Nombre de Paciente", "Tipo de Sangre"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jPanel6.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 920, 250));

        jLabel54.setBackground(new java.awt.Color(33, 203, 103));
        jLabel54.setFont(new java.awt.Font("Tw Cen MT", 1, 23)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(33, 203, 103));
        jLabel54.setText("        Reportes");
        jPanel6.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 190, 40));

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barra3.png"))); // NOI18N
        jPanel6.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 920, 40));
        jPanel6.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 690));

        jTabbedPane1.addTab("tab3", jPanel6);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 940, 720));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hospital2.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        try{
            this.LoadTableModelPacienteMuestras();
            if (!jPanel3.isVisible()) {
                this.jTabbedPane1.setSelectedIndex(0);
            }
        }catch(Exception e){
            this.jTabbedPane1.setSelectedIndex(0);
            e.printStackTrace();
        }

        revalidate();
        repaint();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2MouseClicked

    private void AposTotalComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_AposTotalComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_AposTotalComponentAdded

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (evt.getItem().toString().equals("Extracción de Muestra")){
            jTextField5.setEditable(false);
            jTextField5.setVisible(false);
        }else{
            jTextField5.setEditable(true);
            jTextField5.setVisible(true);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
    if (!jPanel4.isVisible()) {
        this.jTabbedPane1.setSelectedIndex(1);
    }
    revalidate();
    repaint();
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        /*Todos-> Ninguno
            DNI->jTextField9
            Fecha -> jTextField8
            Tipo de Sangre -> jComboBox3
            Estado -> jComboBox5*/
            jComboBox3.setVisible(false);
            jComboBox5.setVisible(false);
            jTextField9.setVisible(false);
            jTextField8.setVisible(false);
        if(evt.getItem().toString().equals("Fecha")){
            jTextField9.setVisible(true);
        }
        if(evt.getItem().toString().equals("DNI")){
            jTextField8.setVisible(true);
        }
        if(evt.getItem().toString().equals("Tipo de Sangre")){
            jComboBox3.setVisible(true);
        }
        if (evt.getItem().toString().equals("Estado")){
            jComboBox5.setVisible(true);
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jComboBox7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox7ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7ItemStateChanged

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jComboBox8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox8ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox8ItemStateChanged

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox8ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        try{
            LoadTableModelPaciente();
            if (!jPanel5.isVisible()) {
                this.jTabbedPane1.setSelectedIndex(2);
            }
        }catch(Exception e){
            this.jTabbedPane1.setSelectedIndex(2);
           e.printStackTrace();
        }
        
        revalidate();
        repaint();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jComboBox9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox9ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox9ItemStateChanged

    private void jComboBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox9ActionPerformed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

    private void jComboBox10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox10ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox10ItemStateChanged

    private void jComboBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox10ActionPerformed

    private void jComboBox11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox11ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox11ItemStateChanged

    private void jComboBox11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox11ActionPerformed

    private void jComboBox12ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox12ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox12ItemStateChanged

    private void jComboBox12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox12ActionPerformed

    private void jComboBox13ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox13ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox13ItemStateChanged

    private void jComboBox13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox13ActionPerformed

    private void jTextField21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField21ActionPerformed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

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
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Homepage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ABnegTotal;
    private javax.swing.JLabel ABposTotal;
    private javax.swing.JLabel AnegTotal;
    private javax.swing.JLabel AposTotal;
    private javax.swing.JLabel BnegTotal;
    private javax.swing.JLabel BposTotal;
    private javax.swing.JLabel OnegTotal;
    private javax.swing.JLabel OposTotal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
