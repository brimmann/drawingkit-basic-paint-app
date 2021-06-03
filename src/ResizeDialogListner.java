import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ResizeDialogListner{
    public static ResizeCanvasDialog resizeCanvasDialog;
    public static UserCanvas canvas;
    public static UserInterface userInterface;


    static public class ComboBoxListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            JComboBox<CanvasSizePattern> source = (JComboBox<CanvasSizePattern>) e.getSource();
            CanvasSizePattern pattern = (CanvasSizePattern) source.getModel().getSelectedItem();
            resizeCanvasDialog.getWidthTextField().getDocument().removeDocumentListener(ResizeCanvasDialog.textFieldLister);
            resizeCanvasDialog.getHeightTextField().getDocument().removeDocumentListener(ResizeCanvasDialog.textFieldLister);
            resizeCanvasDialog.getWidthTextField().setText(Integer.toString(pattern.getWidth()));
            resizeCanvasDialog.getHeightTextField().setText(Integer.toString(pattern.getHeight()));
            resizeCanvasDialog.getWidthTextField().getDocument().addDocumentListener(ResizeCanvasDialog.textFieldLister);
            resizeCanvasDialog.getHeightTextField().getDocument().addDocumentListener(ResizeCanvasDialog.textFieldLister);
        }
    }

    static public class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            if(source.getActionCommand() == "resize-canvas"){
                CanvasSizePattern pattern = (CanvasSizePattern) resizeCanvasDialog.getPatternComboBox().getModel().getSelectedItem();
                if(pattern.getName() == "Custom"){
                    try{
                        int w = Integer.parseInt(resizeCanvasDialog.getWidthTextField().getText());
                        int h = Integer.parseInt(resizeCanvasDialog.getHeightTextField().getText());
                        if(w == 0 || h == 0) throw new CanvasDimensionZeroException();
                        BufferedImage scaledImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                        Graphics2D scaledImage2DG = (Graphics2D) scaledImage.getGraphics();
                        scaledImage2DG.setColor(Color.WHITE);
                        scaledImage2DG.fill(new Rectangle(0, 0, w, h));
                        canvas.setPreferredSize(new Dimension(w, h));
                        scaledImage.getGraphics().drawImage(canvas.getBuffer(), 0, 0, null);
                        canvas.setBuffer(scaledImage);
                        canvas.setG2d((Graphics2D) canvas.getBuffer().getGraphics());
                        canvas.repaint();
                        userInterface.scrollPane.revalidate();
                        resizeCanvasDialog.setVisible(false);
                    }
                    catch (CanvasDimensionZeroException exc){
                        JOptionPane.showMessageDialog(userInterface, exc.getMessage());
                    }
                }
                else{
                    int w = pattern.getWidth();
                    int h = pattern.getHeight();
                    BufferedImage scaledImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D scaledImage2DG = (Graphics2D) scaledImage.getGraphics();
                    scaledImage2DG.setColor(Color.WHITE);
                    scaledImage2DG.fill(new Rectangle(0, 0, w, h));
                    canvas.setPreferredSize(new Dimension(w, h));
                    scaledImage.getGraphics().drawImage(canvas.getBuffer(), 0, 0, null);
                    canvas.setBuffer(scaledImage);
                    canvas.setG2d((Graphics2D) canvas.getBuffer().getGraphics());
                    canvas.repaint();
                    userInterface.scrollPane.revalidate();
                    resizeCanvasDialog.setVisible(false);
                }
                /*if(pattern.getName() == "Custom"){

                    /*canvas.setRectangle(new Rectangle(Integer.parseInt(resizeCanvasDialog.getWidthTextField().getText()), Integer.parseInt(resizeCanvasDialog.getHeightTextField().getText())));
                    canvas.setPreferredSize(new Dimension(Integer.parseInt(resizeCanvasDialog.getWidthTextField().getText()), Integer.parseInt(resizeCanvasDialog.getHeightTextField().getText())));
                    canvas.setDimension(new Dimension(Integer.parseInt(resizeCanvasDialog.getWidthTextField().getText()), Integer.parseInt(resizeCanvasDialog.getHeightTextField().getText())));
                    //canvas.getBuffer().
                }
                else{
                    canvas.setRectangle(new Rectangle(pattern.getWidth(), pattern.getHeight()));
                    canvas.setPreferredSize(new Dimension(pattern.getWidth(), pattern.getHeight()));
                    canvas.setDimension(new Dimension(pattern.getWidth(), pattern.getHeight()));
                }
                //canvas.setG2d(null);*/

                //System.out.println(canvas.getRectangle().width + ", " +   canvas.getRectangle().height);
                //System.out.println(canvas.getDimension().width+ ", " +   canvas.getDimension().height);
                //System.out.println(canvas.getBuffer().getWidth() + ", " +   canvas.getBuffer().getHeight());*/
            }
            else if(source.getActionCommand() == "cancel-resize-dialog"){
                resizeCanvasDialog.setVisible(false);
            }
        }
    }

   static public class TextFieldLister implements DocumentListener {

       @Override
       public void insertUpdate(DocumentEvent e) {
           resizeCanvasDialog.getPatternComboBox().removeItemListener(ResizeCanvasDialog.comboBoxListener);
           resizeCanvasDialog.getPatternComboBox().setSelectedItem(CanvasSizePattern.P6);
           resizeCanvasDialog.getPatternComboBox().addItemListener(ResizeCanvasDialog.comboBoxListener);
       }

       @Override
       public void removeUpdate(DocumentEvent e) {

       }

       @Override
       public void changedUpdate(DocumentEvent e) {

       }
   }
}
