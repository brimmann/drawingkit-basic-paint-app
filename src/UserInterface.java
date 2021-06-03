import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;

public class UserInterface extends JFrame {
    JToolBar toolBar;
    private JLabel zoomLevelLabel;
    JPanel pencilPropertiesPanel, eraserPropertiesPanel, propertiesPanel, statusBar, zoomControlPanel;
    CardLayout cardLayout;
    public JScrollPane scrollPane;
    JButton pencilBtn, eraserBtn, openBtn, newBtn, closeBtn, saveBtn, clearBtn, resizeBtn, zoomInBtn, zoomOutBtn, aboutBtn;
    static public Dimension windowSize;
    static public Point windowLocation;
    private ResizeCanvasDialog resizeCanvasDialog;
    private JComboBox<String> pencilTypesComboBox;
    private JSlider pencilOpcitySlider;
    private JSpinner pencilOpcitySpinner;
    private JSlider eraserSizeSlider;
    private JSpinner eraserSizeSpinner;
    private JSlider eraserOpcitySlider;
    private JSpinner eraserOpcitySpinner;
    JPanel canvasHolder;
    UserCanvas canvas;
    UserInterface(){
        super();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setTitle("DrawingKit");
        Dimension dimension = new Dimension(800, 800);
        setSize(dimension);
        windowSize = dimension;
        windowLocation = new Point(getX(), getY());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        UserCanvas.userInterface = this;
        setIconImage(new ImageIcon("App Icon.png").getImage());
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                super.componentMoved(e);
                windowLocation = new Point(getX(), getY());
                resizeCanvasDialog.updateLocation();
            }

        });
        initComponents();
        initModels();
        setVisible(true);

    }


    private void initComponents(){
        resizeCanvasDialog = new ResizeCanvasDialog(this);
        ToolBoxAction pencilAction = new ToolBoxAction(null, "Pencil.png", false,  KeyEvent.VK_P, "Pencil P");
        ToolBoxAction eraserAction = new ToolBoxAction(null, "Eraser.png", false,  KeyEvent.VK_E, "Eraser E");
        ToolBoxAction clearAction = new ToolBoxAction(null,"Clear.png", true, KeyEvent.VK_C, "Clear Canvas Ctrl+C");
        ToolBoxAction resizeAction = new ToolBoxAction(null, "Resize.png", true, KeyEvent.VK_R, "Resize Canvas Ctrl+R");
        ToolBoxAction zoomInAction = new ToolBoxAction(null, "ZoomIn.png", false, KeyEvent.VK_Z, "Zoom In Canvas Z");
        ToolBoxAction zoomOutAction = new ToolBoxAction(null, "ZoomOut.png", false, KeyEvent.VK_X, "Zoom Out Canvas X");
        ToolBoxAction saveAction = new ToolBoxAction(null, "Save.png", true,  KeyEvent.VK_S, "Save Work Ctrl+S");
        //ToolBoxAction newAction = new ToolBoxAction(null, "New.png", true, KeyEvent.VK_N, "New Work");
        ToolBoxAction openAction = new ToolBoxAction(null, "Open.png", true,  KeyEvent.VK_O, "Open A Work Ctrl+O");
        ToolBoxAction closeAction = new ToolBoxAction(null, "Close.png", true,  KeyEvent.VK_C, "Close Work");
        ToolBoxAction aboutAction = new ToolBoxAction(null, "About.png", false, 0, "About");
        pencilBtn = new JButton(pencilAction);
        eraserBtn = new JButton(eraserAction);
        clearBtn = new JButton(clearAction);
        resizeBtn = new JButton(resizeAction);
        zoomInBtn = new JButton(zoomInAction);
        zoomOutBtn = new JButton(zoomOutAction);
        saveBtn = new JButton(saveAction);
        //newBtn = new JButton(newAction);
        openBtn = new JButton(openAction);
        aboutBtn = new JButton(aboutAction);
        closeBtn = new JButton(closeAction);
        pencilBtn.setActionCommand("draw");
        eraserBtn.setActionCommand("erase");
        clearBtn.setActionCommand("clear");
        resizeBtn.setActionCommand("resize");
        zoomInBtn.setActionCommand("zoom-in");
        zoomOutBtn.setActionCommand("zoom-out");
        saveBtn.setActionCommand("save");
        //newBtn.setActionCommand("new");
        openBtn.setActionCommand("open");
        aboutBtn.setActionCommand("about");
        closeBtn.setActionCommand("close");
        pencilBtn.getActionMap().put(pencilBtn.getActionCommand(), pencilAction);
        pencilBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) pencilAction.getValue(Action.ACCELERATOR_KEY),pencilBtn.getActionCommand());
        eraserBtn.getActionMap().put(eraserBtn.getActionCommand(), eraserAction);
        eraserBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) eraserAction.getValue(Action.ACCELERATOR_KEY),eraserBtn.getActionCommand());
        clearBtn.getActionMap().put(clearBtn.getActionCommand(), clearAction);
        clearBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) clearAction.getValue(Action.ACCELERATOR_KEY),clearBtn.getActionCommand());
        resizeBtn.getActionMap().put(resizeBtn.getActionCommand(), resizeAction);
        resizeBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) resizeAction.getValue(Action.ACCELERATOR_KEY),resizeBtn.getActionCommand());
        zoomInBtn.getActionMap().put(zoomInBtn.getActionCommand(), zoomInAction);
        zoomInBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) zoomInAction.getValue(Action.ACCELERATOR_KEY),zoomInBtn.getActionCommand());
        zoomOutBtn.getActionMap().put(zoomOutBtn.getActionCommand(), zoomOutAction);
        zoomOutBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) zoomOutAction.getValue(Action.ACCELERATOR_KEY),zoomOutBtn.getActionCommand());
        toolBar = new JToolBar(SwingConstants.HORIZONTAL);
        saveBtn.getActionMap().put(saveBtn.getActionCommand(), saveAction);
        saveBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) saveAction.getValue(Action.ACCELERATOR_KEY),saveBtn.getActionCommand());
        openBtn.getActionMap().put(openBtn.getActionCommand(), zoomInAction);
        openBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) openAction.getValue(Action.ACCELERATOR_KEY),openBtn.getActionCommand());
        toolBar.setBorder(BorderFactory.createTitledBorder("Toolbar"));
        toolBar.add(pencilBtn);
        toolBar.add(eraserBtn);
        toolBar.add(clearBtn);
        toolBar.add(resizeBtn);
        toolBar.add(zoomInBtn);
        toolBar.add(zoomOutBtn);
        toolBar.add(saveBtn);
        //toolBar.add(newBtn);
        toolBar.add(openBtn);

        toolBar.add(aboutBtn);
        toolBar.add(closeBtn);

        cardLayout = new CardLayout();
        propertiesPanel = new JPanel();
        propertiesPanel.setBorder(BorderFactory.createTitledBorder("Tool Properties"));
        propertiesPanel.setLayout(cardLayout);

        pencilPropertiesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pencilTypesComboBox = new JComboBox<String>();
        JRadioButton pencilSizeSmall = new JRadioButton("Small", true);
        pencilSizeSmall.setActionCommand("small");
        pencilSizeSmall.addActionListener(new PropertiesListener.ChangePencilSize());
        JRadioButton pencilSizeMedium = new JRadioButton("Medium");
        pencilSizeMedium.setActionCommand("medium");
        pencilSizeMedium.addActionListener(new PropertiesListener.ChangePencilSize());
        JRadioButton pencilSizeLarge = new JRadioButton("Large");
        pencilSizeLarge.setActionCommand("large");
        pencilSizeLarge.addActionListener(new PropertiesListener.ChangePencilSize());
        ButtonGroup pencilSizeButtons = new ButtonGroup();
        pencilSizeButtons.add(pencilSizeSmall);
        pencilSizeButtons.add(pencilSizeMedium);
        pencilSizeButtons.add(pencilSizeLarge);
        pencilOpcitySlider = new JSlider();
        pencilOpcitySlider.addChangeListener(new PropertiesListener.ChangePencilOpacity());
        PropertiesListener.pencilOpcitySlider = pencilOpcitySlider;
        pencilOpcitySpinner = new JSpinner();
        pencilOpcitySpinner.addChangeListener(new PropertiesListener.ChangePencilOpacity());
        PropertiesListener.pencilOpcitySpinner = pencilOpcitySpinner;
        pencilPropertiesPanel.add(new JLabel("Type: "));
        pencilPropertiesPanel.add(pencilTypesComboBox);
        pencilPropertiesPanel.add(new JLabel("Size: "));
        pencilPropertiesPanel.add(pencilSizeSmall);
        pencilPropertiesPanel.add(pencilSizeMedium);
        pencilPropertiesPanel.add(pencilSizeLarge);
        pencilPropertiesPanel.add(new JLabel("Opacity:"));
        pencilPropertiesPanel.add(pencilOpcitySlider);
        pencilPropertiesPanel.add(pencilOpcitySpinner);

        eraserPropertiesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        eraserSizeSlider = new JSlider();
        eraserSizeSlider.addChangeListener(new PropertiesListener.ChangeEraserSize());
        PropertiesListener.eraserSizeSlider = eraserSizeSlider;
        eraserSizeSpinner = new JSpinner();
        eraserSizeSpinner.addChangeListener(new PropertiesListener.ChangeEraserSize());
        PropertiesListener.eraserSizeSpinner = eraserSizeSpinner;
        eraserOpcitySlider = new JSlider();
        eraserOpcitySlider.addChangeListener(new PropertiesListener.ChangeEraserOpacity());
        eraserOpcitySlider.addChangeListener(new PropertiesListener.ChangeEraserOpacity());
        eraserOpcitySpinner = new JSpinner();
        eraserPropertiesPanel.add(new JLabel("Size: "));
        eraserPropertiesPanel.add(eraserSizeSlider);
        eraserPropertiesPanel.add(eraserSizeSpinner);
        eraserPropertiesPanel.add(new JLabel("Opacity: "));
        eraserPropertiesPanel.add(eraserOpcitySlider);
        eraserPropertiesPanel.add(eraserOpcitySpinner);

        zoomControlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        zoomLevelLabel = new JLabel("Zoom Level: 100");
        zoomControlPanel.add(zoomLevelLabel);


        JPanel idleProperties = new JPanel(new FlowLayout(FlowLayout.CENTER));
        idleProperties.add(new JLabel("No Tool Selected"));
        propertiesPanel.add(idleProperties, "idle-properties");
        propertiesPanel.add(pencilPropertiesPanel, "pencil-properties");
        propertiesPanel.add(eraserPropertiesPanel, "eraser-properties");
        propertiesPanel.add(zoomControlPanel, "zoom-control");

        ToolBoxAction.userInterface = this;
        ResizeDialogListner.userInterface = this;
        canvasHolder = new JPanel(new BorderLayout());

        canvas = new UserCanvas();
        scrollPane = new JScrollPane(canvas);

        canvasHolder.setBorder(BorderFactory.createTitledBorder("Canvas"));
        canvasHolder.add(scrollPane);
        ToolBoxAction.canvas = canvas;
        ResizeDialogListner.canvas = canvas;
        statusBar = new JPanel();

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        GroupLayout statusBarLayout = new GroupLayout(statusBar);
        statusBar.setLayout(statusBarLayout);
        statusBarLayout.setHorizontalGroup(
                statusBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        statusBarLayout.setVerticalGroup(
                statusBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 29, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                                .addComponent(propertiesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(canvasHolder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(statusBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                                        .addComponent(propertiesPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(canvasHolder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(statusBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );





    }

    private void initModels(){
        String pencilTypesArray[] = {"6H", "5H", "4H", "3H", "2H", "H", "HB", "B", "2B", "3B", "4B", "5B", "6B"};
        DefaultComboBoxModel<String> pencilTypesComboboxModel = new DefaultComboBoxModel<String>(pencilTypesArray);
        pencilTypesComboBox.setModel(pencilTypesComboboxModel);
        pencilTypesComboBox.setPreferredSize(new Dimension(80, 20));
        pencilTypesComboBox.setSelectedIndex(6);

        DefaultBoundedRangeModel pencilOpcitySliderModel = new DefaultBoundedRangeModel(100, 0,0, 100);
        pencilOpcitySlider.setModel(pencilOpcitySliderModel);
        SpinnerNumberModel pencilOpcitySpinnerModel = new SpinnerNumberModel(100, 0, 100, 1);
        pencilOpcitySpinner.setModel(pencilOpcitySpinnerModel);

        DefaultBoundedRangeModel eraserSizeSliderModel = new DefaultBoundedRangeModel(30, 0,20, 40);
        eraserSizeSlider.setModel(eraserSizeSliderModel);
        SpinnerNumberModel eraserSizeSpinnerModel = new SpinnerNumberModel(30, 20, 40, 1);
        eraserSizeSpinner.setModel(eraserSizeSpinnerModel);

        DefaultBoundedRangeModel eraserOpcitySliderModel = new DefaultBoundedRangeModel(100, 0,0, 100);
        eraserOpcitySlider.setModel(eraserOpcitySliderModel);
        SpinnerNumberModel eraserOpcitySpinnerModel = new SpinnerNumberModel(100, 0, 100, 1);
        eraserOpcitySpinner.setModel(eraserOpcitySpinnerModel);



    }

    public JPanel getPropertiesPanel() {
        return propertiesPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserInterface();

            }
        });
    }

    public ResizeCanvasDialog getResizeCanvasDialog() {
        return resizeCanvasDialog;
    }

    public void setResizeCanvasDialog(ResizeCanvasDialog resizeCanvasDialog) {
        this.resizeCanvasDialog = resizeCanvasDialog;
    }

    public JComboBox<String> getPencilTypesComboBox() {
        return pencilTypesComboBox;
    }

    public JPanel getCanvasHolder() {
        return canvasHolder;
    }

    public void setCanvasHolder(JPanel canvasHolder) {
        this.canvasHolder = canvasHolder;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JLabel getZoomLevelLabel() {
        return zoomLevelLabel;
    }

    public void setZoomLevelLabel(JLabel zoomLevelLabel) {
        this.zoomLevelLabel = zoomLevelLabel;
    }
}
