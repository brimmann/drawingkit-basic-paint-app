import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Eraser extends Tool {
    Eraser(){
        x = y = 0;
        size = 30;
        alpha = 100;
        try {
            BufferedImage cur = ImageIO.read(new File("Eraser.png"));
            cursor = Toolkit.getDefaultToolkit().createCustomCursor(cur, new Point(4,19), "pencil");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        UserCanvas canvas = (UserCanvas) e.getSource();
        canvas.setConnect(false);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        UserCanvas canvas = (UserCanvas) e.getSource();
        x = e.getX();
        y = e.getY();
        x = (int) Math.round(x/canvas.getScale());
        y = (int) Math.round(y/canvas.getScale());
        canvas.erase(new Point(x, y));

    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

}
