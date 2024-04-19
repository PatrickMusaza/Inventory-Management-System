
import java.sql.ResultSetMetaData;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Patrick
 */
public class NewCustomer extends javax.swing.JFrame {

    public NewCustomer() {
        initComponents();

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        Image logo = new ImageIcon(this.getClass().getResource("/Logo.png")).getImage();
        this.setIconImage(logo);

        table_update();

        this.customerFrame.requestFocus();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        itemFrame = new javax.swing.JButton();
        customerFrame = new javax.swing.JButton();
        saleFrame = new javax.swing.JButton();
        purchaseFrame = new javax.swing.JButton();
        stockFrame = new javax.swing.JButton();
        importFrame = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        addNew = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Nationality = new javax.swing.JComboBox<>();
        Email = new javax.swing.JTextField();
        Account = new javax.swing.JTextField();
        Address = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Phone1 = new javax.swing.JTextField();
        Use = new javax.swing.JCheckBox();
        Individual = new javax.swing.JRadioButton();
        Corporate = new javax.swing.JRadioButton();
        Name = new java.awt.TextField();
        Bank = new java.awt.TextField();
        Phone2 = new javax.swing.JTextField();
        Safety2 = new javax.swing.JTextField();
        Safety3 = new javax.swing.JTextField();
        Remark = new javax.swing.JTextField();
        TIN = new java.awt.TextField();
        Delegator = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        srcCode = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        srcName = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        srcActive = new javax.swing.JCheckBox();
        Search = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setTitle("Electronic Billing Machine"); // NOI18N
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        setForeground(java.awt.Color.green);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/dot.png"))); // NOI18N
        jLabel1.setText("Environment");

        jPanel1.setBackground(new java.awt.Color(0, 51, 0));

        itemFrame.setBackground(new java.awt.Color(0, 51, 0));
        itemFrame.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        itemFrame.setForeground(new java.awt.Color(255, 255, 255));
        itemFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/shopping-bag.png"))); // NOI18N
        itemFrame.setText("Item");
        itemFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFrameActionPerformed(evt);
            }
        });

        customerFrame.setBackground(new java.awt.Color(0, 51, 0));
        customerFrame.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        customerFrame.setForeground(new java.awt.Color(255, 255, 255));
        customerFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/name-tag.png"))); // NOI18N
        customerFrame.setText("Customer");
        customerFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerFrameActionPerformed(evt);
            }
        });

        saleFrame.setBackground(new java.awt.Color(0, 51, 0));
        saleFrame.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        saleFrame.setForeground(new java.awt.Color(255, 255, 255));
        saleFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/sale-tag.png"))); // NOI18N
        saleFrame.setText("Sales");
        saleFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleFrameActionPerformed(evt);
            }
        });

        purchaseFrame.setBackground(new java.awt.Color(0, 51, 0));
        purchaseFrame.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        purchaseFrame.setForeground(new java.awt.Color(255, 255, 255));
        purchaseFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/document.png"))); // NOI18N
        purchaseFrame.setText("Purchase");
        purchaseFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseFrameActionPerformed(evt);
            }
        });

        stockFrame.setBackground(new java.awt.Color(0, 51, 0));
        stockFrame.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        stockFrame.setForeground(new java.awt.Color(255, 255, 255));
        stockFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/packages.png"))); // NOI18N
        stockFrame.setText("Stock");
        stockFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockFrameActionPerformed(evt);
            }
        });

        importFrame.setBackground(new java.awt.Color(0, 51, 0));
        importFrame.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        importFrame.setForeground(new java.awt.Color(255, 255, 255));
        importFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/import.png"))); // NOI18N
        importFrame.setText("Import");
        importFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importFrameActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 51, 0));
        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 51, 51));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/power.png"))); // NOI18N
        jButton1.setText("Log Out");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(itemFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customerFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saleFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(purchaseFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stockFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(importFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemFrame)
                    .addComponent(customerFrame)
                    .addComponent(saleFrame)
                    .addComponent(purchaseFrame)
                    .addComponent(stockFrame)
                    .addComponent(importFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Customer Management");

        addNew.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        addNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/credit-card-add.png"))); // NOI18N
        addNew.setText("New");
        addNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addNewMouseClicked(evt);
            }
        });
        addNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewActionPerformed(evt);
            }
        });

        Exit.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/close.png"))); // NOI18N
        Exit.setText("Close");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Exit)
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(addNew)
                    .addComponent(Exit))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(136, 131, 131)));

        jLabel4.setText("Type");

        jLabel5.setText("TIN");

        jLabel6.setText("Name");

        jLabel7.setText("Bank");

        jLabel8.setText("Address");

        jLabel9.setText("Delegator");

        jLabel10.setText("Nationality");

        jLabel11.setText("E-Mail");

        jLabel12.setText("Account");

        jLabel13.setText("Remark");

        jLabel14.setText("Phone 1");

        Nationality.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rwanda", "China", "USA", "Germany", "Eritrea", "Ethiopia", "Zambia", "India" }));

        jLabel15.setText("Phone 2");

        jLabel16.setText("FAX No.");

        jLabel17.setText("Depositor");

        jLabel18.setText("Use");

        Phone1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Phone1KeyTyped(evt);
            }
        });

        Use.setText("Yes ");

        Individual.setSelected(true);
        Individual.setText("Individual");
        Individual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IndividualActionPerformed(evt);
            }
        });

        Corporate.setText("Corporate");
        Corporate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CorporateActionPerformed(evt);
            }
        });

        Phone2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Phone2KeyTyped(evt);
            }
        });

        Safety3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Safety3KeyTyped(evt);
            }
        });

        TIN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TINKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4))
                .addGap(82, 82, 82)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TIN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Bank, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Individual, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Corporate, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Address, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Delegator, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Phone1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(Phone2)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Safety2)
                                    .addComponent(Safety3)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Nationality, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Remark, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Account, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(Use, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Individual)
                    .addComponent(Corporate)
                    .addComponent(jLabel9)
                    .addComponent(jLabel14)
                    .addComponent(Phone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Delegator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel5)
                        .addComponent(Nationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addComponent(Phone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(Safety3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel12)
                        .addComponent(Account, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Bank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(Safety2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Address, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(Remark)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(Use)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(16, 16, 16))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel13)
                            .addComponent(jLabel18))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(64, 60, 60)));

        jLabel19.setText("Customer Code");

        srcCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                srcCodeKeyReleased(evt);
            }
        });

        jLabel20.setText("Customer Name");

        srcName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                srcNameKeyReleased(evt);
            }
        });

        jLabel21.setText("Use");

        srcActive.setText("Yes ");

        Search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/loupe.png"))); // NOI18N
        Search.setText("Search");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(srcCode, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(srcName, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(srcActive, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(Search)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(srcName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(srcActive)
                    .addComponent(Search)
                    .addComponent(srcCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Type", "Name", "Delegator", "Nationality", "Email", "Phone 1", "Phone 2", "FAX"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 816, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    PreparedStatement insert;

    private void table_update() {

        int count;

        try {

            Connection con = Connect.getConnection();
            insert = con.prepareStatement("select * from customer");

            ResultSet rs = insert.executeQuery();
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            count = rsmd.getColumnCount();

            DefaultTableModel Df = (DefaultTableModel) jTable1.getModel();
            Df.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int i = 1; i <= count; i++) {

                    v2.add(rs.getString("TIN"));
                    v2.add(rs.getString("Type"));
                    v2.add(rs.getString("Name"));
                    v2.add(rs.getString("Delegator"));
                    v2.add(rs.getString("Nationality"));
                    v2.add(rs.getString("Email"));
                    v2.add(rs.getString("Phone1"));
                    v2.add(rs.getString("Phone2"));
                    v2.add(rs.getString("FAX"));
                    //v2.add(rs.getString("TaxType"));
                    //v2.add(rs.getString("BeginningStock"));
                    //v2.add(rs.getString("CurrentStock"));
                    //v2.add(rs.getString("SafetyStock"));
                    //v2.add(rs.getString("Description"));
                    //v2.add(rs.getString("Use"));

                }

                Df.addRow(v2);
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ItemM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }


    private void itemFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFrameActionPerformed
        // TODO add your handling code here:

        //new ItemM().setVisible(true);
        this.dispose();

    }//GEN-LAST:event_itemFrameActionPerformed

    private void customerFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerFrameActionPerformed
        // TODO add your handling code here:

        // new Customer().setVisible(true);
        //this.setVisible(false);

    }//GEN-LAST:event_customerFrameActionPerformed

    private void saleFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saleFrameActionPerformed
        // TODO add your handling code here:

        //  new Sale().setVisible(true);
        this.dispose();

    }//GEN-LAST:event_saleFrameActionPerformed

    private void purchaseFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseFrameActionPerformed
        // TODO add your handling code here:

        // new Purchase().setVisible(true);
        this.dispose();

    }//GEN-LAST:event_purchaseFrameActionPerformed

    private void stockFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockFrameActionPerformed
        // TODO add your handling code here:

        //new Stock().setVisible(true);
        this.dispose();

    }//GEN-LAST:event_stockFrameActionPerformed

    private void importFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importFrameActionPerformed
        // TODO add your handling code here:

        //new Import().setVisible(true);

    }//GEN-LAST:event_importFrameActionPerformed

    private void addNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewActionPerformed
        // TODO add your handling code here:

        if (!ValidateForm.isInternetReachable()) {
            try {
                Connection con = Connect.getConnection();
                con.close();
                JOptionPane.showMessageDialog(null, "There is no internet Connection!", "Internet Connectivity", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String input1 = this.Name.getText().trim();
            String input2 = this.TIN.getText().trim();
            String input3 = this.Phone1.getText().trim();
            JFrame frame = new JFrame();
            if (input1.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please Enter Customer Name.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (this.Corporate.isSelected() && input2.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please Enter Customer TIN Number.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (input3.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please Enter Customer Phone Number.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure about the data regarding this customer", "Warning", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {

                    String Type;
                    Type = null;

                    String TIN = this.TIN.getText();
                    String Name = this.Name.getText();
                    String Bank = this.Bank.getText();
                    String Address = this.Address.getText();
                    String Delegator = this.Delegator.getText();
                    String Nationality;
                    String Email = this.Email.getText();
                    String Account = this.Account.getText();
                    String Remark = this.Remark.getText();
                    String Phone1 = this.Phone1.getText();
                    String Phone2 = this.Phone2.getText();
                    String FAX = this.Safety3.getText();
                    String Depositor = this.Safety2.getText();
                    String Use;
                    int index;

                    String Username = Login.Username.getText();

                    index = this.Nationality.getSelectedIndex();
                    Nationality = this.Nationality.getItemAt(index);

                    if (this.Individual.isSelected()) {
                        Type = "Individual";

                        this.Delegator.setEditable(false);
                        Delegator = this.Name.getText();

                    } else if (this.Corporate.isSelected()) {
                        Type = "Corporate";
                    }

                    if (this.Use.isSelected()) {
                        Use = "Yes";
                    } else {
                        Use = "No";
                    }

                    if (this.Individual.isSelected()) {
                        TIN = Phone1;
                    }

                    try {

                        Connection con = Connect.getConnection();
                        insert = con.prepareStatement("insert into customer (Type,TIN,Name,Bank,Address,Delegator,Nationality,Email,Account,Remark,Phone1,Phone2,FAX,Depositor,Active, createdBy) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        insert.setString(1, Type);
                        insert.setString(2, TIN);
                        insert.setString(3, Name);
                        insert.setString(4, Bank);
                        insert.setString(5, Address);
                        insert.setString(6, Delegator);
                        insert.setString(7, Nationality);
                        insert.setString(8, Email);
                        insert.setString(9, Account);
                        insert.setString(10, Remark);
                        insert.setString(11, Phone1);
                        insert.setString(12, Phone2);
                        insert.setString(13, FAX);
                        insert.setString(14, Depositor);
                        insert.setString(15, Use);
                        insert.setString(16, Username);
                        insert.executeUpdate();

                        JOptionPane.showMessageDialog(this, "New Customer Recorded");
                        table_update();

                        this.Individual.equals("Individual");
                        this.Corporate.setText("Corporate");
                        this.TIN.setText("");
                        this.Name.setText("");
                        this.Phone2.setText("");
                        this.Nationality.setSelectedItem(null);
                        this.Safety2.setText("");
                        this.Safety3.setText("");
                        this.Address.setText("");
                        this.Email.setText("");
                        this.Delegator.setText("");
                        this.Account.setText("");
                        this.Phone1.setText("");
                        this.Bank.setText("");
                        this.Remark.setText("");
                        this.Use.setText("Yes");
                        this.TIN.requestFocus();

                        new CustomerList().setVisible(true);
                        this.dispose();

                    } catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(ItemM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_addNewActionPerformed

    private void addNewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addNewMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addNewMouseClicked

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:

        this.setVisible(false);

    }//GEN-LAST:event_ExitActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:

        DefaultTableModel Df = (DefaultTableModel) jTable1.getModel();
        int selectedIndex = jTable1.getSelectedRow();

        this.TIN.setText(Df.getValueAt(selectedIndex, 0).toString());

        String Type = Df.getValueAt(selectedIndex, 1).toString();
        if (Type.equals("Individual")) {
            this.Individual.isSelected();
        } else if (Type.equals("Corporate")) {
            this.Corporate.isSelected();
        }

        this.Name.setText(Df.getValueAt(selectedIndex, 2).toString());
        this.Delegator.setText(Df.getValueAt(selectedIndex, 3).toString());

        String Nationality = Df.getValueAt(selectedIndex, 4).toString();
        this.Nationality.setSelectedItem(Nationality);

        this.Email.setText(Df.getValueAt(selectedIndex, 5).toString());
        this.Phone1.setText(Df.getValueAt(selectedIndex, 6).toString());
        this.Phone2.setText(Df.getValueAt(selectedIndex, 7).toString());
        this.Safety3.setText(Df.getValueAt(selectedIndex, 8).toString());

        PreparedStatement select;

        try {

            String TIN = this.TIN.getText();

            Connection con = Connect.getConnection();

            select = con.prepareStatement("SELECT Bank, Address, Account, Remark, Depositor, Active FROM customer WHERE TIN = ?");
            select.setString(1, TIN);

            ResultSet rs = select.executeQuery();

            // Check if the item with the given code exists
            if (rs.next()) {
                // Retrieve values from the ResultSet
                String Bank = rs.getString("Bank");
                this.Bank.setText(Bank);

                String Address = rs.getString("Address");
                this.Address.setText(Address);

                String Account = rs.getString("Account");
                this.Account.setText(Account);

                String Remark = rs.getString("Remark");
                this.Remark.setText(Remark);

                String Depositor = rs.getString("Depositor");
                this.Safety2.setText(Depositor);

                String active = rs.getString("Active");
                if (active.equals("Yes")) {
                    this.Use.setSelected(true);
                } else {
                    this.Use.setSelected(false);
                }
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ItemM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void IndividualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IndividualActionPerformed
        // TODO add your handling code here:

        if (evt.getSource() == this.Individual) {
            this.Individual.setSelected(true);
            this.Corporate.setSelected(false);
            this.Delegator.setEditable(false);
        }

    }//GEN-LAST:event_IndividualActionPerformed

    private void CorporateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CorporateActionPerformed
        // TODO add your handling code here:

        if (evt.getSource() == this.Corporate) {
            this.Corporate.setSelected(true);
            this.Individual.setSelected(false);
            this.Delegator.setEditable(true);
        }
    }//GEN-LAST:event_CorporateActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:

        DefaultTableModel src = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(src);
        jTable1.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(srcCode.getText(), 0));
        obj.setRowFilter(RowFilter.regexFilter(srcName.getText(), 2));
        //obj.setRowFilter(RowFilter.regexFilter(srcActive.getText()));

    }//GEN-LAST:event_SearchActionPerformed

    private void srcCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srcCodeKeyReleased
        // TODO add your handling code here:

        DefaultTableModel src = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(src);
        jTable1.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(srcCode.getText(), 0));

    }//GEN-LAST:event_srcCodeKeyReleased

    private void srcNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srcNameKeyReleased
        // TODO add your handling code here:

        DefaultTableModel src = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(src);
        jTable1.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(srcName.getText(), 2));

    }//GEN-LAST:event_srcNameKeyReleased

    private void Phone1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Phone1KeyTyped
        // TODO add your handling code here:

        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }

    }//GEN-LAST:event_Phone1KeyTyped

    private void Phone2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Phone2KeyTyped
        // TODO add your handling code here:

        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }

    }//GEN-LAST:event_Phone2KeyTyped

    private void Safety3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Safety3KeyTyped
        // TODO add your handling code here:

        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }

    }//GEN-LAST:event_Safety3KeyTyped

    private void TINKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TINKeyTyped
        // TODO add your handling code here:

        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }

    }//GEN-LAST:event_TINKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        new Login().setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ItemM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ItemM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ItemM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItemM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewCustomer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Account;
    private javax.swing.JTextField Address;
    private java.awt.TextField Bank;
    private javax.swing.JRadioButton Corporate;
    private javax.swing.JTextField Delegator;
    private javax.swing.JTextField Email;
    private javax.swing.JButton Exit;
    private javax.swing.JRadioButton Individual;
    private java.awt.TextField Name;
    private javax.swing.JComboBox<String> Nationality;
    private javax.swing.JTextField Phone1;
    private javax.swing.JTextField Phone2;
    private javax.swing.JTextField Remark;
    private javax.swing.JTextField Safety2;
    private javax.swing.JTextField Safety3;
    private javax.swing.JButton Search;
    private java.awt.TextField TIN;
    private javax.swing.JCheckBox Use;
    private javax.swing.JButton addNew;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton customerFrame;
    private javax.swing.JButton importFrame;
    private javax.swing.JButton itemFrame;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton purchaseFrame;
    private javax.swing.JButton saleFrame;
    private javax.swing.JCheckBox srcActive;
    private javax.swing.JTextField srcCode;
    private javax.swing.JTextField srcName;
    private javax.swing.JButton stockFrame;
    // End of variables declaration//GEN-END:variables
}
