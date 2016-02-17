
import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.WindowManager;
import ij.gui.ImageCanvas;
import ij.gui.ImageWindow;
import ij.gui.Overlay;
import ij.gui.TextRoi;
import ij.process.Blitter;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;
import ij.process.TypeConverter;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JFileChooser;

public class OpenCometUAI extends javax.swing.JFrame implements MouseListener {

    private Properties properties;
    private java.util.HashMap<ImagePlus, Comet[]> Comets;
    private JFileChooser inFileChooser, outDirChooser;
    private String outDirPath;
    private String outFileName;
    private File[] inFiles;
    private File outDir;
    private int cometOptions;
    private int CurrentPreview = 0;
    private Configurations Config;
    private static Color labelInvalidColor = new Color(200, 0, 0);
    private static Color labelValidColor = new Color(0, 150, 0);
    private static Color cometValidColor = Color.red;
    private static Color cometInvalidColor = Color.gray;
    private static Color cometOutlierColor = Color.orange;
    private static Color cometColor = Color.yellow;
    private static Color headColor = Color.green;
    private static Color tailColor = Color.blue;

    public OpenCometUAI(ImageJ parent, String name, boolean modal) {
        initComponents();
        this.setLocationRelativeTo(ij.IJ.getInstance());
        this.setResizable(false);
        this.setTitle(name);
        inFileChooser = new JFileChooser();
        outDirChooser = new JFileChooser();
        this.headFindingAuto.setSelected(true);
        this.bgCorrectCheck.setSelected(true);
        Date dateNow = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MMdd_HHmmss");
        outFileName = "comets_" + dateFormat.format(dateNow);
        outFileNameField.setText(outFileName);
        this.Config = new Configurations();
        this.setProperties(Config.getProproperties());
        updateOutputButton.setEnabled(false);
    }

    /**
     * Return true o or false to create the preview.
     *
     * @return boolean
     */
    public boolean SelectedFiles() {
        return this.inFiles != null && this.inFiles.length > 0;
    }

    /**
     * Start a new OpenComet window.
     */
    public static void startJDialog() {
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(OpenCometUAI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(OpenCometUAI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(OpenCometUAI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(OpenCometUAI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ij.util.Java2.setSystemLookAndFeel();
                OpenCometUAI dialog = new OpenCometUAI(ij.IJ.getInstance(), "OpenComet", false);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        dialog.properties.setProperty("bgCorrectCheck", dialog.bgCorrectCheck.isSelected() + "");
                        dialog.properties.setProperty("headFindingAuto", dialog.headFindingAuto.isSelected() + "");
                        dialog.properties.setProperty("headFindingProfile", dialog.headFindingProfile.isSelected() + "");
                        dialog.properties.setProperty("headFindingBrightest", dialog.headFindingBrightest.isSelected() + "");
                        dialog.Config.saveProperties(dialog.properties);
                    }
                });
                dialog.setVisible(true);
            }
        });

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
        liveButton = new javax.swing.JButton();
        updateOutputButton = new javax.swing.JButton();
        runButton = new javax.swing.JButton();
        headFindingBrightest = new javax.swing.JRadioButton();
        headFindingProfile = new javax.swing.JRadioButton();
        headFindingAuto = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        bgCorrectCheck = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        outFileNameField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        outDirStatusLabel = new javax.swing.JLabel();
        outDirButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        inFilesStatusLabel = new javax.swing.JLabel();
        inFilesButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("OpenComet");

        jPanel1.setBorder(null);

        liveButton.setText("Live");
        liveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liveButtonActionPerformed(evt);
            }
        });

        updateOutputButton.setText("Update");
        updateOutputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateOutputButtonActionPerformed(evt);
            }
        });

        runButton.setText("Run");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(headFindingBrightest);
        headFindingBrightest.setText("Brigthest region");
        headFindingBrightest.setEnabled(false);

        buttonGroup1.add(headFindingProfile);
        headFindingProfile.setText("Profile analysis");
        headFindingProfile.setEnabled(false);
        headFindingProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                headFindingProfileActionPerformed(evt);
            }
        });

        buttonGroup1.add(headFindingAuto);
        headFindingAuto.setSelected(true);
        headFindingAuto.setText("Auto");
        headFindingAuto.setEnabled(false);

        jLabel7.setText("Head finding");

        bgCorrectCheck.setText("Background correction");
        bgCorrectCheck.setEnabled(false);

        jLabel6.setText("Comet finding");

        jLabel5.setText("Output file name");

        outDirStatusLabel.setForeground(new java.awt.Color(255, 0, 0));
        outDirStatusLabel.setText("Choose output directory");

        outDirButton.setText("Browse");
        outDirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outDirButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Output directory");

        inFilesStatusLabel.setForeground(new java.awt.Color(255, 0, 0));
        inFilesStatusLabel.setText("Choose input files");

        inFilesButton.setText("Browse");
        inFilesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inFilesButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Input files");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inFilesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(inFilesStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(outDirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(outDirStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(headFindingAuto))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(headFindingProfile))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(headFindingBrightest))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(runButton)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(updateOutputButton)))
                        .addGap(2, 2, 2)
                        .addComponent(liveButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bgCorrectCheck)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(outFileNameField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inFilesButton))
                .addGap(6, 6, 6)
                .addComponent(inFilesStatusLabel)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(outDirButton))
                .addGap(6, 6, 6)
                .addComponent(outDirStatusLabel)
                .addGap(6, 6, 6)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addGap(6, 6, 6)
                .addComponent(outFileNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel6)
                .addGap(12, 12, 12)
                .addComponent(bgCorrectCheck)
                .addGap(12, 12, 12)
                .addComponent(jLabel7)
                .addGap(6, 6, 6)
                .addComponent(headFindingAuto)
                .addGap(6, 6, 6)
                .addComponent(headFindingProfile)
                .addGap(6, 6, 6)
                .addComponent(headFindingBrightest)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(runButton)
                    .addComponent(updateOutputButton)
                    .addComponent(liveButton)))
        );

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("New Window");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Configurations");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Dialog for select input files.
     *
     * @param evt
     */
    private void inFilesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inFilesButtonActionPerformed
        inFileChooser.setDialogTitle("Input Files");
        inFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        inFileChooser.setMultiSelectionEnabled(true);
        inFileChooser.showOpenDialog(null);
        inFiles = inFileChooser.getSelectedFiles();
        if (inFiles.length == 0) {
            inFilesStatusLabel.setText("Choose input files");
            inFilesStatusLabel.setForeground(labelInvalidColor);
        } else if (inFiles.length == 1) {
            inFilesStatusLabel.setText("1 file selected");
            inFilesStatusLabel.setForeground(labelValidColor);
        } else {
            inFilesStatusLabel.setText(inFiles.length + " files selected");
            inFilesStatusLabel.setForeground(labelValidColor);
        }
        inFilesStatusLabel.repaint();
    }//GEN-LAST:event_inFilesButtonActionPerformed

    /**
     * Dialog for Output files.
     *
     * @param evt
     */
    private void outDirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outDirButtonActionPerformed
        outDirChooser.setDialogTitle("Output Directory");
        outDirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        outDirChooser.setMultiSelectionEnabled(false);

        int result = outDirChooser.showOpenDialog(null);
        outDir = outDirChooser.getSelectedFile();
        if (result == 0) {
            outDirPath = outDir + "";
            int len = outDirPath.length();

            if (outDirPath.charAt(len - 1) != File.separatorChar) {
                outDirPath = outDirPath + File.separatorChar;
            }

            String outDirStatusLabelText = outDirPath;

            if (len > 25) {
                outDirStatusLabelText = outDirPath.substring(0, 12) + "..." + outDirPath.substring(len - 12, len);
            }

            boolean canWrite = checkWriteAccess(outDirPath);

            if (canWrite) {
                outDirStatusLabel.setText(outDirStatusLabelText);
                outDirStatusLabel.setForeground(labelValidColor);
            } else {
                outDirStatusLabel.setText(outDirStatusLabelText);
                outDirStatusLabel.setForeground(labelInvalidColor);
            }
        } else {
            outDirStatusLabel.setText("Choose output directory");
            outDirStatusLabel.setForeground(labelInvalidColor);
        }

        outDirStatusLabel.repaint();
    }//GEN-LAST:event_outDirButtonActionPerformed

    /**
     * Run event, starting analysis.
     *
     * @param evt
     */
    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        if ((inFiles == null) || (inFiles.length == 0)) {
            IJ.log("Select input files");
        } else if ((outDir == null)) {
            IJ.log("Select output directory");
        } else {
            runOnInput(0);
        }
    }//GEN-LAST:event_runButtonActionPerformed

    /**
     * Action for update comets.
     *
     * @param evt
     */
    private void updateOutputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateOutputButtonActionPerformed
        printComets(outFileName + "_update");
        for (Map.Entry<ImagePlus, Comet[]> element : Comets.entrySet()) {
            ImagePlus imp = element.getKey();
            if (imp.getCanvas() == null) {
                continue;
            }
            ImagePlus impSave = imp.flatten();
            IJ.save(impSave, outDirPath + imp.getTitle());
        }
    }//GEN-LAST:event_updateOutputButtonActionPerformed

    private void liveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_liveButtonActionPerformed
        // TODO add your handling code here:
        if ((outDir == null)) {
            IJ.log("Select output directory");
        } else {
            runOnInput(1);
        }
    }//GEN-LAST:event_liveButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        IJ.log("Welcome to OpenComet");
        OpenCometUAI.startJDialog();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.Config = new Configurations(this, "Configurations", true);
        this.Config.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                Config.SaveProperties();
                Config.dispose();
            }
        });
        this.Config.setVisible(true);
        System.out.println("Updating Configurations.");
        this.setProperties(this.Config.getProproperties());
        System.out.println("Configurations updated");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void headFindingProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_headFindingProfileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_headFindingProfileActionPerformed

    /**
     * Update of settings.
     *
     * @param p
     */
    public void setProperties(Properties p) {
        this.properties = p;
        this.bgCorrectCheck.setSelected(p.getProperty("bgCorrectCheck").equals("true"));
        this.headFindingAuto.setSelected(p.getProperty("headFindingAuto").equals("true"));
        this.headFindingProfile.setSelected(p.getProperty("headFindingProfile").equals("true"));
        this.headFindingBrightest.setSelected(p.getProperty("headFindingBrightest").equals("true"));
    }

    /**
     * Return an ImagePlus[] array with four preview images.
     *
     * @return
     */
    public ImagePlus[] getPreview() {
        if (inFiles.length > 0) {
            ImagePlus imp = IJ.openImage(inFiles[this.CurrentPreview].getPath());

            CometAnalyzer analizer = new CometAnalyzer();
            if (imp != null) {
                String imageKey = inFiles[0].getName();
                IJ.log("Preview started, image key: " + imageKey);
                //Segmentation
                Comet[] cometsOut = analizer.cometAnalyzerRun(imp, this.properties);

                //For Results
                int imgType = imp.getType();
                ColorProcessor ip_out = null;
                if (imgType == ImagePlus.GRAY8) {
                    TypeConverter t = new TypeConverter(imp.getProcessor(), false);
                    ip_out = (ColorProcessor) t.convertToRGB();
                } else if (imgType == ImagePlus.GRAY16 || imgType == ImagePlus.GRAY32) {
                    ImageProcessor ip_temp = imp.getProcessor().convertToByte(true);
                    TypeConverter t = new TypeConverter(ip_temp, false);
                    ip_out = (ColorProcessor) t.convertToRGB();
                } else if (imgType == ImagePlus.COLOR_RGB) {
                    ip_out = (ColorProcessor) (imp.getProcessor().duplicate());
                } else {
                    IJ.log("Unhandled image type: " + imgType);
                }

                String imgOutFileName = imageKey + "_out.tif";
                ImagePlus img_out = new ImagePlus(imgOutFileName, ip_out);

                if (cometsOut != null && cometsOut.length > 0) {
                    Overlay cometOverlay = new Overlay();
                    drawComets(ip_out, cometOverlay, cometsOut);
                    img_out.setOverlay(cometOverlay);
                } else {
                    IJ.log("No comets in image stored.");
                }
                ImagePlus PreviewCommets[] = new ImagePlus[4];
                ImagePlus PreviewImage = IJ.openImage(inFiles[this.CurrentPreview].getPath());
                ImagePlus PreviewSegmented[] = analizer.GetPreview(PreviewImage, properties);
                System.arraycopy(PreviewSegmented, 0, PreviewCommets, 0, PreviewSegmented.length);
                PreviewCommets[3] = img_out;
                IJ.log("Preview complete, image key: " + imageKey);
                return PreviewCommets;
            }
            return null;
        }
        return null;
    }

    /**
     * Set counter for the next preview image.
     */
    public void setNextPreview() {
        if (this.inFiles.length > (this.CurrentPreview + 1)) {
            this.CurrentPreview++;
        } else {
            this.CurrentPreview = 0;
        }
    }

    /**
     * Set counter for previous image.
     */
    public void setBackPreview() {
        if (this.CurrentPreview - 1 >= 0) {
            this.CurrentPreview--;
        } else {
            this.CurrentPreview = this.inFiles.length - 1;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        OpenCometUAI.startJDialog();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox bgCorrectCheck;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton headFindingAuto;
    private javax.swing.JRadioButton headFindingBrightest;
    private javax.swing.JRadioButton headFindingProfile;
    private javax.swing.JButton inFilesButton;
    private javax.swing.JLabel inFilesStatusLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton liveButton;
    private javax.swing.JButton outDirButton;
    private javax.swing.JLabel outDirStatusLabel;
    private javax.swing.JTextField outFileNameField;
    private javax.swing.JButton runButton;
    private javax.swing.JButton updateOutputButton;
    // End of variables declaration//GEN-END:variables

    /**
     * Launch OpenComet's analyzer
     *
     * @param type
     */
    private void runOnInput(int type) {
        // Make an instance of the comet analyzer class
        CometAnalyzer cometAnalyzer = new CometAnalyzer();
        Comets = new HashMap<ImagePlus, Comet[]>();
        cometOptions = 0;

        String tmpText = outFileNameField.getText();
        if ((tmpText != null) && (tmpText.length() > 1)) {
            outFileName = tmpText;
        }

        IJ.log(cometOptions + "");

        if (type == 0) {
            // Open each input image and run comet analysis
            // Iterate over each input file
            for (int i = 0; i < inFiles.length; i++) {
                // Try to open file as image
                ImagePlus imp = IJ.openImage(inFiles[i].getPath());

                // If image could be opened, run comet analysis
                if (imp != null) {
                    String imageKey = inFiles[i].getName();

                    IJ.log("Run started, image key: " + imageKey);

                    Comet[] cometsOut = cometAnalyzer.cometAnalyzerRun(imp, this.properties);

                    IJ.log("Run complete, image key: " + imageKey);

                    storeComets(cometsOut, imp, imageKey);

                    // if(cometsOut!=null && cometsOut.length>0){
                    // ImageProcessor ip = imp.getProcessor();
                    // int imgType = imp.getType();
                    // ColorProcessor ip_out = null;
                    // if(imgType == 0){
                    // TypeConverter t = new TypeConverter(ip,false);
                    // ip_out = (ColorProcessor)t.convertToRGB();
                    // }
                    //RGB
                    // else if(imgType == 4){
                    // ip_out = (ColorProcessor)(ip.duplicate());
                    // }
                    // String imgOutFileName = imageKey +"_out.tif";					
                    // ImagePlus img_out = new ImagePlus(imgOutFileName,ip_out);
                    // Comets.put(img_out, cometsOut);
                    // for(int j=0;j<cometsOut.length;j++){
                    // if(cometsOut[j].status==Comet.VALID)
                    // printArray(cometsOut[j].cometProfile);
                    // }
                    // Overlay cometOverlay = new Overlay();
                    // drawComets(ip_out, cometOverlay, cometsOut);
                    // img_out.setOverlay(cometOverlay);
                    // img_out.show();
                    // img_out.getCanvas().addMouseListener(this);
                    // ImagePlus img_out_save = img_out.flatten();
                    // IJ.save(img_out_save, outDirPath + imgOutFileName);
                    // }
                    // else {
                    // IJ.log("No comets stored");
                    // }
                } else {
                    IJ.log("Could not open " + inFiles[i].getName() + ", unsupported format");
                }
            }
        } else {
            ImageWindow imw = WindowManager.getCurrentWindow();
            if (imw != null) {
                ImagePlus img = imw.getImagePlus();
                ImagePlus img2 = imw.getImagePlus().duplicate();
                if (img != null) {
                    Comet[] cometsOut = cometAnalyzer.cometAnalyzerRun(img, this.properties);
                    if (cometsOut == null || cometsOut.length == 0) {
                        IJ.log("No comets found.");
                    } else {
                        IJ.log("Number of comets found: " + cometsOut.length);
                    }
                    storeComets(cometsOut, img2, outFileName + "_image");
                }
            }
        }

        printComets(outFileName);

        updateOutputButton.setEnabled(true);
        runButton.setEnabled(false);
    }

    private void printComets(String fileName) {
        // Setup output data file
        try {
            String outFileName = outDirPath + fileName + ".xls";
            FileOutputStream fos = new FileOutputStream(outFileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            PrintWriter outPrintWriter = new PrintWriter(bos);

            IJ.log("Out filename: " + outFileName);

            String sep = "\t";
            String headerstr = "FileName" + sep + "Num" + sep + "Flag" + sep + "CometArea" + sep
                    + "CometIntensity" + sep
                    + "CometLength" + sep + "CometDNA" + sep
                    + "HeadArea" + sep + "HeadIntensity" + sep
                    + "HeadLength" + sep + "HeadDNA" + sep
                    + "HeadDNAPercent" + sep
                    + "TailArea" + sep + "TailIntensity" + sep
                    + "TailLength" + sep + "TailDNA" + sep
                    + "TailDNAPercent" + sep + "TailMoment"
                    + sep + "OliveMoment";
            outPrintWriter.println(headerstr);

            for (Map.Entry<ImagePlus, Comet[]> element : Comets.entrySet()) {
                ImagePlus imp = element.getKey();
                String imgTitle = imp.getTitle();
                IJ.log("Image title: " + imgTitle);
                int endIdx = imgTitle.lastIndexOf('_');
                if (endIdx > 0) {
                    String imageKey = imgTitle.substring(0, endIdx);
                    Comet[] imageComets = element.getValue();
                    for (int i = 0; i < imageComets.length; i++) {
                        if (imageComets[i].status != Comet.INVALID) {
                            String outstr = imageKey + sep;
                            outstr += imageComets[i].getMeasurementString(sep);
                            outPrintWriter.println(outstr);
                        }
                    }
                }
            }

            CometStatistics cometStats = new CometStatistics(Comets, Comet.VALID);
            String outstr = cometStats.getStatisticsString(sep, "normal");
            outPrintWriter.print(outstr);

            cometStats = new CometStatistics(Comets, Comet.VALID | Comet.OUTLIER);
            outstr = cometStats.getStatisticsString(sep, "normal+outlier");
            outPrintWriter.print(outstr);

            outPrintWriter.close();
        } catch (Exception ex) {
            IJ.log(ex.toString());
        }
    }

    private void storeComets(Comet[] cometsOut, ImagePlus imp, String imageKey) {
        ImageProcessor ip = imp.getProcessor();
        int imgType = imp.getType();
        ColorProcessor ip_out = null;
        if (imgType == ImagePlus.GRAY8) {
            TypeConverter t = new TypeConverter(ip, false);
            ip_out = (ColorProcessor) t.convertToRGB();
        } else if (imgType == ImagePlus.GRAY16 || imgType == ImagePlus.GRAY32) {
            ImageProcessor ip_temp = ip.convertToByte(true);
            TypeConverter t = new TypeConverter(ip_temp, false);
            ip_out = (ColorProcessor) t.convertToRGB();
        } else if (imgType == ImagePlus.COLOR_RGB) {
            ip_out = (ColorProcessor) (ip.duplicate());
        } else {
            IJ.log("Unhandled image type: " + imgType);
        }

        String imgOutFileName = imageKey + "_out.tif";
        ImagePlus img_out = new ImagePlus(imgOutFileName, ip_out);

        if (cometsOut != null && cometsOut.length > 0) {
            Comets.put(img_out, cometsOut);

//            for (int j = 0; j < cometsOut.length; j++) {
//                if (cometsOut[j].status == Comet.VALID) {
//                    printArray(cometsOut[j].cometProfile);
//                }
//            }

            Overlay cometOverlay = new Overlay();
            drawComets(ip_out, cometOverlay, cometsOut);
            img_out.setOverlay(cometOverlay);
        } else {
            IJ.log("No comets in image stored.");
        }

        img_out.show();
        img_out.getCanvas().addMouseListener(this);

        ImagePlus img_out_save = img_out.flatten();
        IJ.save(img_out_save, outDirPath + imgOutFileName);
    }

    private void drawComets(ImageProcessor ip, Overlay overlay, Comet[] comets) {
        for (int i = 0; i < comets.length; i++) {
            if (comets[i].status == Comet.INVALID) {
                comets[i].cometRoi.setStrokeColor(cometInvalidColor);
                overlay.add(comets[i].cometRoi);
            } else if (comets[i].status == Comet.OUTLIER) {
                comets[i].cometRoi.setStrokeColor(cometOutlierColor);
                comets[i].headRoi.setStrokeColor(cometOutlierColor);
                //overlay.add(comets[i].cometRoi);
                overlay.add(comets[i].oldRoi);
                overlay.add(comets[i].headRoi);

                ip.setColor(Color.red);
                TextRoi textRoi = new TextRoi(comets[i].x + comets[i].width,
                        comets[i].y, "" + comets[i].id, new Font("Arial", Font.BOLD, 22));
                textRoi.drawPixels(ip);
            } else if (comets[i].status == Comet.VALID) {
                comets[i].cometRoi.setStrokeColor(cometValidColor);
                comets[i].headRoi.setStrokeColor(cometValidColor);
                overlay.add(comets[i].cometRoi);
                overlay.add(comets[i].headRoi);

                ip.setColor(Color.red);
                TextRoi textRoi = new TextRoi(comets[i].x + comets[i].width,
                        comets[i].y, "" + comets[i].id, new Font("Arial", Font.BOLD, 22));
                textRoi.drawPixels(ip);
                ImageProcessor ipProfile = getCometProfilePlot(comets[i]);
                ip.copyBits(ipProfile, comets[i].x, comets[i].y, Blitter.COPY_TRANSPARENT);
            }
        }
    }

    private ImageProcessor getCometProfilePlot(Comet comet) {
        ImageProcessor ip = new ColorProcessor(comet.width, comet.height);
        ip.invert();

        double heightStep = comet.height / comet.profileMax;
        drawProfilePlot(ip, comet.cometProfile, cometColor, heightStep);
        drawProfilePlot(ip, comet.headProfile, headColor, heightStep);
        drawProfilePlot(ip, comet.tailProfile, tailColor, heightStep);

        return ip;
    }

    private void drawProfilePlot(ImageProcessor ip, double[] profile, Color col, double heightStep) {
        int height = ip.getHeight();
        int width = ip.getWidth();
        int x1, x2, y1, y2;
        ip.setColor(col);
        for (int x = 0; x < width - 1; x++) {
            x1 = x;
            x2 = x + 1;
            y1 = (int) ((height - 1) - profile[x] * heightStep);
            y2 = (int) ((height - 1) - profile[x + 1] * heightStep);
            ip.drawLine(x1, y1, x2, y2);
        }
    }

    private void printArray(double[] arr) {
        String s = "";
        for (int i = 0; i < arr.length; i++) {
            s += arr[i];
            if (i < arr.length - 1) {
                s += ",";
            }
        }
        IJ.log(s);
    }

    private boolean checkWriteAccess(String path) {
        File tmpFile = new File(path + "tmp");
        try {
            tmpFile.createNewFile();
            tmpFile.delete();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        ImageCanvas canvas = (ImageCanvas) e.getSource();
        ImagePlus imp = canvas.getImage();
        x = canvas.offScreenX(x);
        y = canvas.offScreenY(y);
        IJ.log(x + "," + y);
        Comet comet = findClickedComet(imp, x, y);
        if (comet != null) {
            IJ.log("Comet found");
            comet.status = Comet.DELETED;
            comet.cometRoi.setStrokeColor(Color.gray);
            comet.oldRoi.setStrokeColor(Color.gray);
            if (comet.headRoi != null) {
                comet.headRoi.setStrokeColor(Color.gray);
            }
        }
    }

    private Comet findClickedComet(ImagePlus imp, int x, int y) {
        Comet[] comets = (Comet[]) Comets.get(imp);
        if (comets != null) {
            for (int i = 0; i < comets.length; i++) {
                if (comets[i].cometRoi.contains(x, y)) {
                    return comets[i];
                }
            }
        }
        return null;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
