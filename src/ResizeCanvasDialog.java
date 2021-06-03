import javax.swing.*;
import java.awt.*;

public class ResizeCanvasDialog extends JDialog {


    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel heightLabel;
    private javax.swing.JTextField heightTextField;
    private javax.swing.JLabel infoLab;
    private javax.swing.JComboBox<CanvasSizePattern> patternComboBox;
    private javax.swing.JLabel patternLabel;
    private javax.swing.JButton resizeBtn;
    private javax.swing.JLabel widthLabel;
    private javax.swing.JTextField widthTextField;
    static public ResizeDialogListner.TextFieldLister textFieldLister = new ResizeDialogListner.TextFieldLister();
    static public ResizeDialogListner.ButtonListener buttonListener = new ResizeDialogListner.ButtonListener();
    static public ResizeDialogListner.ComboBoxListener comboBoxListener = new ResizeDialogListner.ComboBoxListener();
    JPanel main = new JPanel();

    ResizeCanvasDialog(JFrame parent){
        super(parent, "Resize Canvas", true);
        ResizeDialogListner.resizeCanvasDialog = this;
        initComponent();
        updateLocation();
    }

    public void initComponent(){
        cancelBtn = new javax.swing.JButton();
        cancelBtn.setActionCommand("cancel-resize-dialog");
        cancelBtn.addActionListener(buttonListener);
        resizeBtn = new javax.swing.JButton();
        resizeBtn.setActionCommand("resize-canvas");
        resizeBtn.addActionListener(buttonListener);
        widthLabel = new javax.swing.JLabel();
        widthTextField = new javax.swing.JTextField();

        widthTextField.setPreferredSize(new Dimension(60, 20));
        heightLabel = new javax.swing.JLabel();
        heightTextField = new javax.swing.JTextField();

        heightTextField.setPreferredSize(new Dimension(60, 20));
        patternComboBox = new javax.swing.JComboBox<>();
        patternComboBox.addItemListener(comboBoxListener);
        patternLabel = new javax.swing.JLabel();
        infoLab = new javax.swing.JLabel();

        cancelBtn.setText("Cancel");

        resizeBtn.setText("Resize");

        widthLabel.setText("Width:");

        widthTextField.setText("0");
        widthTextField.getDocument().addDocumentListener(textFieldLister);

        heightLabel.setText("Height:");
        heightTextField.setText("0");
        heightTextField.getDocument().addDocumentListener(textFieldLister);

        patternComboBox.setModel(new javax.swing.DefaultComboBoxModel<CanvasSizePattern>(new CanvasSizePattern[] { CanvasSizePattern.P1, CanvasSizePattern.P2, CanvasSizePattern.P3, CanvasSizePattern.P4, CanvasSizePattern.P5, CanvasSizePattern.P6, CanvasSizePattern.P7, CanvasSizePattern.P8}));

        patternComboBox.setSelectedItem(CanvasSizePattern.P6);
        patternLabel.setText("Predefined Size Pattern");

        infoLab.setText("Define new size for the canvas:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(main);
        main.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(infoLab)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(patternLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(patternComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(resizeBtn)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(6, 6, 6)
                                                                                .addComponent(widthLabel)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(widthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(cancelBtn))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(0, 14, Short.MAX_VALUE)
                                                                                .addComponent(heightLabel)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(heightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                .addGap(31, 31, 31))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(infoLab)
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(widthLabel)
                                        .addComponent(heightLabel)
                                        .addComponent(heightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(widthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(patternComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(patternLabel))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelBtn)
                                        .addComponent(resizeBtn))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        main.setPreferredSize(new Dimension(350, 175));
        add(main);
        setResizable(false);
        pack();
    }

    public void updateLocation(){
        setLocation((UserInterface.windowSize.width-getWidth())/2 + UserInterface.windowLocation.x , (UserInterface.windowSize.height-getHeight())/2 + UserInterface.windowLocation.y);

    }

    public JTextField getHeightTextField() {
        return heightTextField;
    }

    public void setHeightTextField(JTextField heightTextField) {
        this.heightTextField = heightTextField;
    }

    public JComboBox<CanvasSizePattern> getPatternComboBox() {
        return patternComboBox;
    }

    public void setPatternComboBox(JComboBox<CanvasSizePattern> patternComboBox) {
        this.patternComboBox = patternComboBox;
    }

    public JTextField getWidthTextField() {
        return widthTextField;
    }

    public void setWidthTextField(JTextField widthTextField) {
        this.widthTextField = widthTextField;
    }
}
