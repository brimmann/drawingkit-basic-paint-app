import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class UserCanvas extends JPanel {
    private Dimension dimension;
    Rectangle rectangle;
    boolean connect;
    Point prePoint;

    private Graphics2D g2d;
    private BufferedImage buffer, original;
    private boolean drawMode, eraseMode;
    private Pencil pencil;
    private Eraser eraser;
    private double scale;
    static public UserInterface userInterface;
    private AffineTransform imageAffineTransform;
    private boolean start;
    UserCanvas(){
        super();
        connect = false;
        drawMode = eraseMode = false;
        scale = 1.0;
        start = true;
        dimension = new Dimension(CanvasSizePattern.P7.getWidth(), CanvasSizePattern.P7.getHeight());
        buffer = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
        pencil = new Pencil();
        eraser = new Eraser();
        setPreferredSize(dimension);
        setSize(dimension);
        rectangle = new Rectangle(0,0, dimension.width, dimension.height);
        PropertiesListener.pencil = pencil;
        PropertiesListener.eraser = eraser;

    }

    public Pencil getPencil() {
        return pencil;
    }

    public void setPencil(Pencil pencil) {
        this.pencil = pencil;
    }

    public Eraser getEraser() {
        return eraser;
    }

    public void setEraser(Eraser eraser) {
        this.eraser = eraser;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gCast = (Graphics2D) g;
        gCast.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        gCast.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //gCast.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(g2d == null){
            buffer = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
            g2d = (Graphics2D) buffer.getGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fill(rectangle);

        }
        imageAffineTransform = AffineTransform.getScaleInstance(scale, scale);
        gCast.drawImage(buffer, imageAffineTransform, null);
    }


    public boolean isConnect() {
        return connect;
    }

    public void setConnect(boolean connect) {
        this.connect = connect;
    }

    public void draw(Point toolPoint){
        //g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setColor(Color.BLACK);
        if(connect){
            g2d.setStroke(new BasicStroke(pencil.getSize(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            Line2D.Double co = new Line2D.Double(prePoint.x, prePoint.y, toolPoint.x, toolPoint.y);
            g2d.draw(co);
        }
        prePoint = new Point(toolPoint.x, toolPoint.y);
        connect = true;
        repaint();
    }

    public void erase(Point toolPoint){
        g2d.setColor(Color.WHITE);
        if(connect){
            g2d.setStroke(new BasicStroke(eraser.getSize(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            Line2D.Double co = new Line2D.Double(prePoint.x, prePoint.y, toolPoint.x, toolPoint.y);
            g2d.draw(co);
            repaint();

        }
        prePoint = new Point(toolPoint.x, toolPoint.y);
        connect = true;
    }

    public void setPrePoint(Point prePoint) {
        this.prePoint = prePoint;
    }

    public boolean isDrawMode() {
        return drawMode;
    }

    public void setDrawMode(boolean drawMode) {
        this.drawMode = drawMode;
    }

    public boolean isEraseMode() {
        return eraseMode;
    }

    public void setEraseMode(boolean eraseMode) {
        this.eraseMode = eraseMode;
    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    public Graphics2D getG2d() {
        return g2d;
    }

    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public AffineTransform getImageAffineTransform() {
        return imageAffineTransform;
    }

    public void setImageAffineTransform(AffineTransform imageAffineTransform) {
        this.imageAffineTransform = imageAffineTransform;
    }

    /*public void drawCursor(Point p){
        Graphics2D gCast = (Graphics2D) getGraphics();
        gCast.drawOval(p.x, p.y, pencil.getSize()*5, pencil.getSize()*5);
        repaint();
    }*/
}
