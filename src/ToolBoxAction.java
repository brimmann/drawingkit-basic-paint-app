import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.io.File;
import java.io.IOException;

public class ToolBoxAction extends AbstractAction {
    public static UserCanvas canvas;
    public static UserInterface userInterface;
    private AffineTransformOp op;
    private Rectangle2D rec;
    public ToolBoxAction(String name, String path, boolean mod,  int accel, String tTip){
        super(name, new ImageIcon(path));
        if(mod) putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accel, InputEvent.CTRL_DOWN_MASK));
        else putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accel, 0));
        putValue(SHORT_DESCRIPTION, tTip);
        KeyStroke a = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton source = (JButton) e.getSource();
        switch (((JButton) e.getSource()).getActionCommand()){
            case "draw":
                userInterface.getCardLayout().show(userInterface.getPropertiesPanel(), "pencil-properties");
                canvas.setCursor(canvas.getPencil().cursor);
                if(canvas.isEraseMode()){
                    canvas.setConnect(false);
                    canvas.setPrePoint(null);
                    canvas.setEraseMode(false);
                    canvas.removeMouseListener(canvas.getEraser());
                    canvas.removeMouseMotionListener(canvas.getEraser());
                }
                canvas.addMouseListener(canvas.getPencil());
                canvas.addMouseMotionListener(canvas.getPencil());
                canvas.setDrawMode(true);
                break;
            case "erase":
                canvas.setCursor(canvas.getEraser().cursor);
                userInterface.getCardLayout().show(userInterface.getPropertiesPanel(), "eraser-properties");
                if(canvas.isDrawMode()){
                    canvas.setConnect(false);
                    canvas.setPrePoint(null);
                    canvas.removeMouseListener(canvas.getPencil());
                    canvas.removeMouseMotionListener(canvas.getPencil());
                    canvas.setDrawMode(false);
                }
                canvas.addMouseListener(canvas.getEraser());
                canvas.addMouseMotionListener(canvas.getEraser());
                canvas.setEraseMode(true);
                break;
            case "clear":
                if(canvas.isDrawMode() || canvas.isEraseMode()){
                    canvas.setConnect(false);
                    canvas.setPrePoint(null);
                    canvas.setEraseMode(false);
                    canvas.setDrawMode(false);
                    canvas.removeMouseListener(canvas.getEraser());
                    canvas.removeMouseMotionListener(canvas.getEraser());
                    canvas.removeMouseListener(canvas.getPencil());
                    canvas.removeMouseMotionListener(canvas.getPencil());

                }
                canvas.setCursor(null);
                //canvas.setG2d(null);
                canvas.getG2d().setColor(Color.WHITE);
                canvas.getG2d().fill(new Rectangle(0, 0, canvas.getBuffer().getWidth(), canvas.getBuffer().getHeight()));
                canvas.getG2d().setColor(Color.BLACK);
                canvas.repaint();
                break;
            case "resize":
                canvas.setCursor(null);
                userInterface.getResizeCanvasDialog().setVisible(true);
                canvas.revalidate();
                break;
            case "zoom-in":
                canvas.setCursor(null);
                userInterface.getCardLayout().show(userInterface.getPropertiesPanel(), "zoom-control");
                canvas.setScale(canvas.getScale()+0.1);
                userInterface.getZoomLevelLabel().setText("Zoom Level: " + (int) (canvas.getScale()*100));
                canvas.repaint();
                op = new AffineTransformOp(canvas.getImageAffineTransform(), AffineTransformOp.TYPE_BICUBIC);
                rec = op.getBounds2D(canvas.getBuffer());
                canvas.setPreferredSize(new Dimension((int) rec.getWidth(), (int) rec.getHeight()));
                canvas.revalidate();
                break;
            case "zoom-out":
                canvas.setCursor(null);
                userInterface.getCardLayout().show(userInterface.getPropertiesPanel(), "zoom-control");
                canvas.setScale(canvas.getScale()-0.1);
                userInterface.getZoomLevelLabel().setText("Zoom Level: " + (int) (canvas.getScale()*100));
                canvas.repaint();
                op = new AffineTransformOp(canvas.getImageAffineTransform(), AffineTransformOp.TYPE_BICUBIC);
                rec = op.getBounds2D(canvas.getBuffer());
                canvas.setPreferredSize(new Dimension((int) rec.getWidth(), (int) rec.getHeight()));
                canvas.revalidate();
                System.out.println(rec);
                break;
            case "save":
                canvas.setCursor(null);
                JFileChooser fileChooserSave = new JFileChooser(new File("C:/Users/Mohammad Rashid/Pictures"));
                fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
                fileChooserSave.addChoosableFileFilter(new FileNameExtensionFilter("PNG Image", "png"));
                fileChooserSave.setAcceptAllFileFilterUsed(false);
                int responseSave = fileChooserSave.showSaveDialog(userInterface);
                fileChooserSave.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if(fileChooserSave.getSelectedFile() == null) return;
                File fileSave = new File(fileChooserSave.getSelectedFile().getPath() + ".png");
                if(responseSave == JFileChooser.APPROVE_OPTION) {
                   if(fileSave.exists()){
                       int overWritRes = JOptionPane.showConfirmDialog(null,
                               fileSave.toString() + " exists. Do you want to overwrite?",
                               "Confirm Save", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                       if(overWritRes == JOptionPane.NO_OPTION) return;
                   }
                    try {
                        ImageIO.write(canvas.getBuffer(), "PNG", fileSave);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                break;
            case "open":
                canvas.setCursor(null);
                JFileChooser fileChooserOpen = new JFileChooser(new File("C:/Users/Mohammad Rashid/Pictures"));
                fileChooserOpen.setDialogType(JFileChooser.OPEN_DIALOG);
                fileChooserOpen.addChoosableFileFilter(new FileNameExtensionFilter("Images", "png", "jpg", "gif"));
                fileChooserOpen.setAcceptAllFileFilterUsed(false);
                int responseOpen = fileChooserOpen.showOpenDialog(userInterface);
                fileChooserOpen.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if(fileChooserOpen.getSelectedFile() == null) return;
                File fileOpen = new File(fileChooserOpen.getSelectedFile().getPath());
                if(responseOpen == JFileChooser.APPROVE_OPTION){
                    try {
                        canvas.setBuffer(ImageIO.read(fileOpen));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    canvas.setRectangle(new Rectangle(canvas.getBuffer().getWidth(), canvas.getBuffer().getHeight()));
                    canvas.setPreferredSize(new Dimension(canvas.getBuffer().getWidth(), canvas.getBuffer().getHeight()));
                    canvas.setDimension(new Dimension(canvas.getBuffer().getWidth(), canvas.getBuffer().getHeight()));
                    canvas.setG2d((Graphics2D)canvas.getBuffer().getGraphics());
                    canvas.repaint();
                    userInterface.scrollPane.revalidate();
                }
                break;
            case "about":
                String info = "DrawingKit\n" +
                        "Versioin 1.0.0 Alpha\n" +
                        "Copyright(C) 2020\n" +
                        "For noncommercial use only.\n" +
                        "Developer: Mohammad Rashid";
                JOptionPane.showMessageDialog(userInterface, info, "About", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "close":
                canvas.setCursor(null);
                System.exit(0);

        }
    }

}

