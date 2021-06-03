import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pencil extends Tool {
    private PencilType type;

    private Image texture;
    Pencil(){
        x = y = 0;
        size = 1;
        type = PencilType.PHB;
        try {
            BufferedImage cur = ImageIO.read(new File("Pencil.png"));
            cursor = Toolkit.getDefaultToolkit().createCustomCursor(cur, new Point(0,30), "pencil");
        } catch (IOException e) {
            e.printStackTrace();
        }

        alpha = 100;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public PencilType getType() {
        return type;
    }

    public void setType(PencilType type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public Image getTexture() {
        return texture;
    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        UserCanvas canvas = (UserCanvas ) e.getSource();
        canvas.setConnect(false);
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        UserCanvas canvas = (UserCanvas ) e.getSource();
        x = e.getX();
        y = e.getY();
        x = (int) Math.round(x/canvas.getScale());
        y = (int) Math.round(y/canvas.getScale());
        canvas.draw(new Point(x, y));

    }

    /*@Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        UserCanvas canvas = (UserCanvas ) e.getSource();
        //canvas.drawCursor(new Point(e.getX(), e.getY()));
    }*/
}
