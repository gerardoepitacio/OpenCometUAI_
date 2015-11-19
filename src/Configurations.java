
import java.awt.event.KeyAdapter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Configurations extends javax.swing.JDialog {

    Properties properties;

    public Configurations() {
        loadProperties();
    }

    public Configurations(java.awt.Frame parent, String name, boolean modal) {
        super(parent, name, modal);
        initComponents();
        this.setLocationRelativeTo(ij.IJ.getInstance());
        this.loadProperties();
        this.setProperties();
        //validate if is a double number when key released
        new ValidateDoubleNumber(this.cometMinConvexity);
        new ValidateDoubleNumber(this.cometMaxSymmetry);
        new ValidateDoubleNumber(this.cometMaxHRatio);
        new ValidateDoubleNumber(this.cometMaxCLDOutler);
        new ValidateDoubleNumber(this.cometMaxCLDBig);

        //select text when gained Focus
        new SelectionText(this.cometMinConvexity);
        new SelectionText(this.cometMaxSymmetry);
        new SelectionText(this.cometMaxHRatio);
        new SelectionText(this.cometMaxCLDOutler);
        new SelectionText(this.cometMaxCLDBig);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cometMinConvexity = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cometMaxSymmetry = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cometMaxHRatio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cometMaxCLDOutler = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cometMaxCLDBig = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        thresholdingMethod = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        bgCorrectCheck = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        headFindingAuto = new javax.swing.JRadioButton();
        headFindingProfile = new javax.swing.JRadioButton();
        headFindingBrightest = new javax.swing.JRadioButton();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel3.setText("Minimun Convexity");

        jLabel4.setText("Maximun Symmetry");

        jLabel5.setText("Maximun HRatio");

        cometMaxHRatio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cometMaxHRatioActionPerformed(evt);
            }
        });

        jLabel6.setText("Maximun for OUTLER");

        cometMaxCLDOutler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cometMaxCLDOutlerActionPerformed(evt);
            }
        });

        jLabel8.setText("Center Line Diff");

        jLabel9.setText("Maximun for BIG");

        cometMaxCLDBig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cometMaxCLDBigActionPerformed(evt);
            }
        });

        jLabel10.setText("Shape parameters");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cometMaxSymmetry, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cometMinConvexity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(cometMaxHRatio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cometMaxCLDBig, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cometMaxCLDOutler, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator3))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cometMinConvexity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cometMaxSymmetry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cometMaxHRatio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(cometMaxCLDBig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cometMaxCLDOutler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(256, 256, 256)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Comet Finding", jPanel1);

        thresholdingMethod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Huang" }));

        jLabel1.setText("Method");

        bgCorrectCheck.setText("Background correction");
        bgCorrectCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bgCorrectCheckActionPerformed(evt);
            }
        });

        jLabel7.setText("Head finding");

        buttonGroup1.add(headFindingAuto);
        headFindingAuto.setText("Auto");
        headFindingAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                headFindingAutoActionPerformed(evt);
            }
        });

        buttonGroup1.add(headFindingProfile);
        headFindingProfile.setText("Profile analysis");
        headFindingProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                headFindingProfileActionPerformed(evt);
            }
        });

        buttonGroup1.add(headFindingBrightest);
        headFindingBrightest.setText("Brigthest region");
        headFindingBrightest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                headFindingBrightestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(headFindingBrightest)
                    .addComponent(headFindingProfile)
                    .addComponent(headFindingAuto)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator6))
                        .addComponent(thresholdingMethod, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bgCorrectCheck, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thresholdingMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(bgCorrectCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(headFindingAuto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(headFindingProfile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(headFindingBrightest)
                .addGap(18, 18, 18)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thresholding", jPanel2);

        jButton2.setText("Restart to default");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.SaveDefaultProperties();
        this.loadProperties();
        this.setProperties();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void headFindingBrightestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_headFindingBrightestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_headFindingBrightestActionPerformed

    private void headFindingProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_headFindingProfileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_headFindingProfileActionPerformed

    private void headFindingAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_headFindingAutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_headFindingAutoActionPerformed

    private void bgCorrectCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bgCorrectCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bgCorrectCheckActionPerformed

    private void cometMaxHRatioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cometMaxHRatioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cometMaxHRatioActionPerformed

    private void cometMaxCLDBigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cometMaxCLDBigActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cometMaxCLDBigActionPerformed

    private void cometMaxCLDOutlerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cometMaxCLDOutlerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cometMaxCLDOutlerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox bgCorrectCheck;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField cometMaxCLDBig;
    private javax.swing.JTextField cometMaxCLDOutler;
    private javax.swing.JTextField cometMaxHRatio;
    private javax.swing.JTextField cometMaxSymmetry;
    private javax.swing.JTextField cometMinConvexity;
    private javax.swing.JRadioButton headFindingAuto;
    private javax.swing.JRadioButton headFindingBrightest;
    private javax.swing.JRadioButton headFindingProfile;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox thresholdingMethod;
    // End of variables declaration//GEN-END:variables
void SaveDefaultProperties() {
        properties = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("config.properties");
            //Thresholding
            properties.setProperty("bgCorrectCheck", "true");
            properties.setProperty("headFindingAuto", "true");
            properties.setProperty("headFindingProfile", "false");
            properties.setProperty("headFindingBrightest", "false");
            properties.setProperty("thresholdingMethod", "0");

            //CometFinding
            properties.setProperty("cometMinConvexity", "0.85");
            properties.setProperty("cometMaxSymmetry", "0.5");
            properties.setProperty("cometMaxHRatio", "1.05");
            properties.setProperty("cometMaxCLDOutler", "0.15");
            properties.setProperty("cometMaxCLDBig", "0.2");
            properties.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void SaveProperties() {
        properties = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("config.properties");
            //Thresholding
            properties.setProperty("bgCorrectCheck", this.bgCorrectCheck.isSelected() + "");
            properties.setProperty("headFindingAuto", this.headFindingAuto.isSelected() + "");
            properties.setProperty("headFindingProfile", this.headFindingProfile.isSelected() + "");
            properties.setProperty("headFindingBrightest", this.headFindingBrightest.isSelected() + "");
            properties.setProperty("thresholdingMethod", this.thresholdingMethod.getSelectedIndex() + "");

            //CometFinding
            properties.setProperty("cometMinConvexity", this.cometMinConvexity.getText());
            properties.setProperty("cometMaxSymmetry", this.cometMaxSymmetry.getText());
            properties.setProperty("cometMaxHRatio", this.cometMaxHRatio.getText());
            properties.setProperty("cometMaxCLDOutler", this.cometMaxCLDOutler.getText());
            properties.setProperty("cometMaxCLDBig", this.cometMaxCLDBig.getText());
            properties.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveProperties(Properties prop) {
        OutputStream output = null;
        try {
            output = new FileOutputStream("config.properties");
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setProperties() {
        //Thresholding
        this.bgCorrectCheck.setSelected(properties.getProperty("bgCorrectCheck").equals("true"));
        this.headFindingAuto.setSelected(properties.getProperty("headFindingAuto").equals("true"));
        this.headFindingProfile.setSelected(properties.getProperty("headFindingProfile").equals("true"));
        this.headFindingBrightest.setSelected(properties.getProperty("headFindingBrightest").equals("true"));
        this.thresholdingMethod.setSelectedIndex(Integer.parseInt(properties.getProperty("thresholdingMethod")));

        //CometFinding
        this.cometMinConvexity.setText(properties.getProperty("cometMinConvexity"));
        this.cometMaxSymmetry.setText(properties.getProperty("cometMaxSymmetry"));
        this.cometMaxHRatio.setText(properties.getProperty("cometMaxHRatio"));
        this.cometMaxCLDOutler.setText(properties.getProperty("cometMaxCLDOutler"));
        this.cometMaxCLDBig.setText(properties.getProperty("cometMaxCLDBig"));
    }

    private void loadProperties() {
        properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("config.properties");
            ij.IJ.log("Settings ready");
            properties.load(input);
        } catch (FileNotFoundException ex) {
            ij.IJ.log("Loading Default Settings");
            this.SaveDefaultProperties();
            this.loadProperties();
        } catch (IOException ex) {
            ij.IJ.log("can't read settings");
            Logger.getLogger(Configurations.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Properties getProproperties() {
        return properties;
    }

}

class ValidateDoubleNumber extends KeyAdapter {

    javax.swing.JTextField jtf;

    public ValidateDoubleNumber(javax.swing.JTextField jtf) {
        jtf.addKeyListener(this);
        this.jtf = jtf;
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        String text = this.jtf.getText();
        String pattern = "^\\d+(\\.)?(\\d+)?";
        Pattern pat = Pattern.compile(pattern);
        Matcher mat = pat.matcher(text);
        if (mat.matches()) {
        } else {
            if (text.length() != 0) {
                text = text.substring(0, text.length() - 1);
                this.jtf.setText(text);
            }

        }
    }
}

class SelectionText extends java.awt.event.FocusAdapter {

    javax.swing.JTextField jtf;

    public SelectionText(javax.swing.JTextField jtf) {
        this.jtf = jtf;
        jtf.addFocusListener(this);
    }

    public void focusGained(java.awt.event.FocusEvent evt) {
        this.jtf.setSelectionStart(0);
        this.jtf.setSelectionEnd(this.jtf.getText().length());
    }
}
