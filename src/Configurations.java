
import ij.ImagePlus;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
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
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Configurations extends javax.swing.JDialog {

    Properties properties;
    private OpenCometUAI Principal;
    private ImagePlus Images[];
    public static final int RESULTS_PREVIEW = 3;
    public static final int CORRECTION_PREVIEW = 0;
    public static final int SEGMENTED_PREVIEW = 1;
    public Configurations() {
        loadProperties();
    }

    public Configurations(OpenCometUAI parent, String name, boolean modal) {
        super(parent, name, modal);
        initComponents();
        this.Principal = parent;
        this.setLocationRelativeTo(ij.IJ.getInstance());
        this.loadProperties();
        this.setProperties();
        DoubleValidation();
        TextSelect();
        installEscapeCloseOperation(this);
        if (this.Principal.SelectedFiles() == true) {
            this.jPPreview.setVisible(true);
            this.jBPreview.setEnabled(true);
            this.jPButtons.setVisible(true);
            this.setResizable(true);
            this.addComponentListener(new resizeListener());
            this.pack();
            this.GetPreview();
        } else {
            this.jTPPreview.setVisible(false);
            this.jBPreview.setEnabled(false);
            this.jPButtons.setVisible(false);
            this.pack();
        }
    }

    class resizeListener extends ComponentAdapter {

        public void componentResized(ComponentEvent e) {
            if (ResizedTimer.isRunning()) {
                ResizedTimer.restart();
            } else {
                ResizedTimer.start();
            }
        }
    }

    Timer ResizedTimer = new Timer(5, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            ResizedTimer.stop();
            setLabelImage(jPResults, Images[RESULTS_PREVIEW]);
            setLabelImage(jPCorrectons, Images[CORRECTION_PREVIEW]);
            setLabelImage(jPSegmented, Images[SEGMENTED_PREVIEW]);
        }
    });

    private void setLabelImage(javax.swing.JPanel panel, ImagePlus img) {
        Image image = img.getImage();
        if (img.getHeight() > panel.getHeight()) {
            Double Dif = (1.0 - ((double) panel.getHeight() / (double) img.getHeight()));
            image = img.getImage().getScaledInstance(img.getWidth() - (int) (img.getWidth() * Dif),
                    img.getHeight() - (int) (img.getHeight() * Dif), Image.SCALE_AREA_AVERAGING);
        }
        JLabel picLabel = (JLabel) panel.getComponent(0);
        picLabel.setIcon(new ImageIcon(image));
        picLabel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        panel.repaint();
    }

    private void DoubleValidation() {
        //validate if is a double number when key released
        new ValidateDoubleNumber(this.cometMinArea);
        new ValidateDoubleNumber(this.cometMinConvexity);
        new ValidateDoubleNumber(this.cometMaxSymmetry);
        new ValidateDoubleNumber(this.cometMaxHRatio);
        new ValidateDoubleNumber(this.cometMaxCLDOutler);
        new ValidateDoubleNumber(this.cometMaxCLDBig);
    }

    private void TextSelect() {
        //select text when gained Focus
        new SelectionText(this.cometMinArea);
        new SelectionText(this.cometMinConvexity);
        new SelectionText(this.cometMaxSymmetry);
        new SelectionText(this.cometMaxHRatio);
        new SelectionText(this.cometMaxCLDOutler);
        new SelectionText(this.cometMaxCLDBig);
    }

    private static final KeyStroke escapeStroke
            = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
    public static final String dispatchWindowClosingActionMapKey
            = "com.spodding.tackline.dispatch:WINDOW_CLOSING";

    public void installEscapeCloseOperation(final JDialog dialog) {
        Action dispatchClosing = new AbstractAction() {
            public void actionPerformed(ActionEvent event) {
                dialog.dispatchEvent(new WindowEvent(
                        dialog, WindowEvent.WINDOW_CLOSING
                ));
            }
        };
        JRootPane root = dialog.getRootPane();
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                escapeStroke, dispatchWindowClosingActionMapKey
        );
        root.getActionMap().put(dispatchWindowClosingActionMapKey, dispatchClosing
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
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
        jSeparator9 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
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
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        cometMinArea = new javax.swing.JTextField();
        cometClearEdges = new javax.swing.JCheckBox();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jBPreview = new javax.swing.JButton();
        jPPreview = new javax.swing.JPanel();
        jTPPreview = new javax.swing.JTabbedPane();
        jPResults = new javax.swing.JPanel();
        jPCorrectons = new javax.swing.JPanel();
        jPSegmented = new javax.swing.JPanel();
        jPButtons = new javax.swing.JPanel();
        jBPrevious = new javax.swing.JButton();
        jBNextPreview = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        thresholdingMethod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Huang", "LocalThesholding" }));

        jLabel1.setText("Correction");

        bgCorrectCheck.setText("Background correction");

        jLabel7.setText("Head finding");

        buttonGroup1.add(headFindingAuto);
        headFindingAuto.setText("Auto");

        buttonGroup1.add(headFindingProfile);
        headFindingProfile.setText("Profile analysis");

        buttonGroup1.add(headFindingBrightest);
        headFindingBrightest.setText("Brigthest region");

        jLabel12.setText("Method");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(headFindingBrightest)
                        .addComponent(headFindingProfile)
                        .addComponent(headFindingAuto)
                        .addComponent(jLabel7)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(200, 200, 200)
                            .addComponent(jSeparator6))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(thresholdingMethod, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bgCorrectCheck, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(thresholdingMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bgCorrectCheck)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(headFindingAuto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(headFindingProfile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(headFindingBrightest)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thresholding", jPanel2);

        jLabel3.setText("Minimun Convexity");

        jLabel4.setText("Maximun Symmetry");

        jLabel5.setText("Maximun HRatio");

        jLabel6.setText("Maximun for OUTLER");

        jLabel8.setText("Center Line Diff");

        jLabel9.setText("Maximun for BIG");

        jLabel10.setText("Shape parameters");

        jLabel2.setText("Minimun Area (px)");

        cometClearEdges.setText("Clear edges");

        jLabel11.setText("Edges");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cometMaxCLDBig, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cometMaxCLDOutler, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cometClearEdges, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cometMaxHRatio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cometMaxSymmetry, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cometMinConvexity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cometMinArea, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator8)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(cometMaxSymmetry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cometMaxHRatio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addComponent(cometMinConvexity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cometMinArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cometMaxCLDBig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(cometMaxCLDOutler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cometClearEdges)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Comet Finding", jPanel1);

        jButton2.setText("Restart to default");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jBPreview.setText("Preview");
        jBPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPreviewActionPerformed(evt);
            }
        });

        jTPPreview.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPResultsLayout = new javax.swing.GroupLayout(jPResults);
        jPResults.setLayout(jPResultsLayout);
        jPResultsLayout.setHorizontalGroup(
            jPResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );
        jPResultsLayout.setVerticalGroup(
            jPResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 346, Short.MAX_VALUE)
        );

        jTPPreview.addTab("Results", jPResults);

        javax.swing.GroupLayout jPCorrectonsLayout = new javax.swing.GroupLayout(jPCorrectons);
        jPCorrectons.setLayout(jPCorrectonsLayout);
        jPCorrectonsLayout.setHorizontalGroup(
            jPCorrectonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );
        jPCorrectonsLayout.setVerticalGroup(
            jPCorrectonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 346, Short.MAX_VALUE)
        );

        jTPPreview.addTab("Corrections", jPCorrectons);

        javax.swing.GroupLayout jPSegmentedLayout = new javax.swing.GroupLayout(jPSegmented);
        jPSegmented.setLayout(jPSegmentedLayout);
        jPSegmentedLayout.setHorizontalGroup(
            jPSegmentedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );
        jPSegmentedLayout.setVerticalGroup(
            jPSegmentedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        jTPPreview.addTab("Segmented", jPSegmented);

        jBPrevious.setText("Previous");
        jBPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPreviousActionPerformed(evt);
            }
        });
        jPButtons.add(jBPrevious);

        jBNextPreview.setText("Next");
        jBNextPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNextPreviewActionPerformed(evt);
            }
        });
        jPButtons.add(jBNextPreview);

        javax.swing.GroupLayout jPPreviewLayout = new javax.swing.GroupLayout(jPPreview);
        jPPreview.setLayout(jPPreviewLayout);
        jPPreviewLayout.setHorizontalGroup(
            jPPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTPPreview)
            .addComponent(jPButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPPreviewLayout.setVerticalGroup(
            jPPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPPreviewLayout.createSequentialGroup()
                .addComponent(jTPPreview)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBPreview)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jBPreview)))
            .addComponent(jPPreview, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.SaveDefaultProperties();
        this.loadProperties();
        this.setProperties();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jBPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPreviewActionPerformed
        this.GetPreview();
    }//GEN-LAST:event_jBPreviewActionPerformed

    private void jBPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPreviousActionPerformed
        // TODO add your handling code here:
        this.Principal.setBackPreview();
        this.GetPreview();
    }//GEN-LAST:event_jBPreviousActionPerformed

    private void jBNextPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNextPreviewActionPerformed
        // TODO add your handling code here:
        this.Principal.setNextPreview();
        this.GetPreview();
    }//GEN-LAST:event_jBNextPreviewActionPerformed

    private void GetPreview() {
        this.SaveProperties();
        this.loadProperties();
        this.Principal.setProperties(this.properties);
        this.Images = this.Principal.getPreview();        
        this.SetImages();
    }
    
    private void SetImages(){
        this.SetImagePreview(this.jPResults, this.Images[Configurations.RESULTS_PREVIEW]);
        this.SetImagePreview(this.jPCorrectons, this.Images[Configurations.CORRECTION_PREVIEW]);
        this.SetImagePreview(this.jPSegmented, this.Images[Configurations.SEGMENTED_PREVIEW]);
    }
    
    private void SetImagePreview(javax.swing.JPanel panel, ImagePlus img) {
        panel.removeAll();
        Image image = img.getImage();
        if (img.getHeight() > panel.getHeight()) {
            Double Dif = (1.0 - ((double) panel.getHeight() / (double) img.getHeight()));
            image = img.getImage().getScaledInstance(img.getWidth() - (int) (img.getWidth() * Dif),
                    img.getHeight() - (int) (img.getHeight() * Dif), Image.SCALE_AREA_AVERAGING);
        }
        JLabel picLabel = new JLabel(new ImageIcon(image));
        picLabel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        picLabel.setOpaque(true);
        picLabel.setBackground(new Color(216, 227, 250));
        panel.add(picLabel);
        panel.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox bgCorrectCheck;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cometClearEdges;
    private javax.swing.JTextField cometMaxCLDBig;
    private javax.swing.JTextField cometMaxCLDOutler;
    private javax.swing.JTextField cometMaxHRatio;
    private javax.swing.JTextField cometMaxSymmetry;
    private javax.swing.JTextField cometMinArea;
    private javax.swing.JTextField cometMinConvexity;
    private javax.swing.JRadioButton headFindingAuto;
    private javax.swing.JRadioButton headFindingBrightest;
    private javax.swing.JRadioButton headFindingProfile;
    private javax.swing.JButton jBNextPreview;
    private javax.swing.JButton jBPreview;
    private javax.swing.JButton jBPrevious;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPButtons;
    private javax.swing.JPanel jPCorrectons;
    private javax.swing.JPanel jPPreview;
    private javax.swing.JPanel jPResults;
    private javax.swing.JPanel jPSegmented;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTPPreview;
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
            properties.setProperty("cometMinArea", "0");
            properties.setProperty("cometClearEdges", "true");
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
            properties.setProperty("cometMinArea", this.cometMinArea.getText());
            properties.setProperty("cometClearEdges", this.cometClearEdges.isSelected() + "");
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
        this.cometMinArea.setText(properties.getProperty("cometMinArea"));
        this.cometClearEdges.setSelected(properties.getProperty("cometClearEdges").equals("true"));
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
