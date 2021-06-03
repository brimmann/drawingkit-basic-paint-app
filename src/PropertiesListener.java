import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.chrono.Era;

public class PropertiesListener {
    static public Pencil pencil;
    static public Eraser eraser;
    static public JSlider pencilOpcitySlider, eraserOpcitySlider, eraserSizeSlider;
    static public JSpinner pencilOpcitySpinner, eraserOpcitySpinner, eraserSizeSpinner;
    class ChangePencilType implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<String> source = (JComboBox<String>) e.getSource();
            switch ((String) source.getSelectedItem()){
                case "6H":
                    pencil.setType(PencilType.P6H);
                    break;
                case "5H":
                    pencil.setType(PencilType.P5H);
                    break;
                case "4H":
                    pencil.setType(PencilType.P4H);
                    break;
                case "3H":
                    pencil.setType(PencilType.P3H);
                    break;
                case "2H":
                    pencil.setType(PencilType.P2H);
                    break;
                case "H":
                    pencil.setType(PencilType.PH);
                    break;
                case "HB":
                    pencil.setType(PencilType.PHB);
                    break;
                case "B":
                    pencil.setType(PencilType.PB);
                    break;
                case "2B":
                    pencil.setType(PencilType.P2B);
                    break;
                case "3B":
                    pencil.setType(PencilType.P3B);
                    break;
                case "4B":
                    pencil.setType(PencilType.P4B);
                    break;
                case "5B":
                    pencil.setType(PencilType.P5B);
                    break;
                case "6B":
                    pencil.setType(PencilType.P6B);
                    break;

            }
        }
    }

    static class ChangePencilSize implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton source = (JRadioButton) e.getSource();
            System.out.println("size-change-called to " + source.getActionCommand() + " it was " + pencil.getSize());
            switch ((String) source.getActionCommand()){
                case "small":
                    pencil.setSize(1);
                    break;
                case "medium":
                    pencil.setSize(2);
                    break;
                case "large":
                    pencil.setSize(3);
                    break;
            }
        }
    }
    static class ChangePencilOpacity implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            Object objSource = e.getSource();
            if(objSource instanceof JSlider){
               DefaultBoundedRangeModel slModel = (DefaultBoundedRangeModel) pencilOpcitySlider.getModel();
               SpinnerNumberModel spModel = (SpinnerNumberModel) pencilOpcitySpinner.getModel();
               spModel.setValue(slModel.getValue());
               pencil.setAlpha(slModel.getValue());
            }
            else if(objSource instanceof JSpinner){
                DefaultBoundedRangeModel slModel = (DefaultBoundedRangeModel) pencilOpcitySlider.getModel();
                SpinnerNumberModel spModel = (SpinnerNumberModel) pencilOpcitySpinner.getModel();
                slModel.setValue((int) spModel.getValue());
                pencil.setAlpha((int) spModel.getValue());
            }
        }
    }

    static class ChangeEraserSize implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            Object objSource = e.getSource();
            if(objSource instanceof JSlider){
                DefaultBoundedRangeModel slModel = (DefaultBoundedRangeModel) eraserSizeSlider.getModel();
                SpinnerNumberModel spModel = (SpinnerNumberModel) eraserSizeSpinner.getModel();
                spModel.setValue(slModel.getValue());
                eraser.setSize(slModel.getValue());
            }
            else if(objSource instanceof JSpinner){
                DefaultBoundedRangeModel slModel = (DefaultBoundedRangeModel) eraserSizeSlider.getModel();
                SpinnerNumberModel spModel = (SpinnerNumberModel) eraserSizeSpinner.getModel();
                slModel.setValue((int) spModel.getValue());
                eraser.setSize((int) spModel.getValue());
            }
        }
    }

    static class ChangeEraserOpacity implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            Object objSource = e.getSource();
            if(objSource instanceof JSlider){
                DefaultBoundedRangeModel slModel = (DefaultBoundedRangeModel) eraserOpcitySlider.getModel();
                SpinnerNumberModel spModel = (SpinnerNumberModel) eraserOpcitySpinner.getModel();
                spModel.setValue(slModel.getValue());
                eraser.setAlpha(slModel.getValue());
            }
            else if(objSource instanceof JSpinner){
                DefaultBoundedRangeModel slModel = (DefaultBoundedRangeModel) eraserOpcitySlider.getModel();
                SpinnerNumberModel spModel = (SpinnerNumberModel) eraserOpcitySpinner.getModel();
                slModel.setValue((int) spModel.getValue());
                eraser.setAlpha((int) spModel.getValue());
            }
        }
    }
}
