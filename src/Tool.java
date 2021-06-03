import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

abstract public class Tool extends MouseAdapter {
    protected int x, y;
    protected Cursor cursor;
    protected int alpha;
    protected int size;

    @Override
    abstract public void mouseReleased(MouseEvent e);

    @Override
    abstract public void mouseDragged(MouseEvent e);

}
