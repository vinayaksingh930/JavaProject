import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.*;

public class ShapeDrawer extends JPanel {
    private java.util.List<Shape> shapes;
    private Shape currentShape;
    private String shapeType = "Rectangle";
    private Point startPoint;
    private Shape hoveredShape; // Shape currently hovered over
    private boolean resizing = false; // Whether we are resizing

    public ShapeDrawer() {
        shapes = new ArrayList<>();
        setBackground(Color.WHITE);

        // Add mouse listener for drawing and resizing
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point clickPoint = e.getPoint();

                // Check if the click is inside an existing shape
                for (Shape shape : shapes) {
                    if (shape.contains(clickPoint)) {
                        hoveredShape = shape;
                        resizing = true;
                        return;
                    }
                }

                // If no shape is hovered, create a new shape
                resizing = false;
                startPoint = clickPoint;
                switch (shapeType.toLowerCase()) {
                    case "rectangle":
                        currentShape = new Rectangle(startPoint.x, startPoint.y, 0, 0);
                        break;
                    case "circle":
                        currentShape = new Ellipse2D.Float(startPoint.x, startPoint.y, 0, 0);
                        break;
                    case "line":
                        currentShape = new Line2D.Float(startPoint, startPoint);
                        break;
                }
                if (currentShape != null) {
                    shapes.add(currentShape);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                hoveredShape = null; // Reset hover state
                resizing = false;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (resizing && hoveredShape != null) {
                    resizeShape(hoveredShape, e.getPoint());
                } else if (currentShape != null) {
                    updateShapeSize(e.getPoint());
                }
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // Highlight the shape being hovered over
                Point movePoint = e.getPoint();
                hoveredShape = null;
                for (Shape shape : shapes) {
                    if (shape.contains(movePoint)) {
                        hoveredShape = shape;
                        break;
                    }
                }
                repaint();
            }
        });
    }

    // Resize the shape dynamically
    private void resizeShape(Shape shape, Point newPoint) {
        if (shape instanceof Rectangle) {
            Rectangle rect = (Rectangle) shape;
            rect.setSize(Math.abs(newPoint.x - rect.x), Math.abs(newPoint.y - rect.y));
        } else if (shape instanceof Ellipse2D.Float) {
            Ellipse2D.Float ellipse = (Ellipse2D.Float) shape;
            ellipse.setFrame(ellipse.x, ellipse.y, Math.abs(newPoint.x - ellipse.x), Math.abs(newPoint.y - ellipse.y));
        } else if (shape instanceof Line2D.Float) {
            Line2D.Float line = (Line2D.Float) shape;
            line.setLine(line.getP1(), newPoint);
        }
    }

    // Update the size of the shape during drawing
    private void updateShapeSize(Point endPoint) {
        if (currentShape instanceof Rectangle) {
            Rectangle rect = (Rectangle) currentShape;
            rect.setBounds(Math.min(startPoint.x, endPoint.x),
                    Math.min(startPoint.y, endPoint.y),
                    Math.abs(endPoint.x - startPoint.x),
                    Math.abs(endPoint.y - startPoint.y));
        } else if (currentShape instanceof Ellipse2D.Float) {
            Ellipse2D.Float ellipse = (Ellipse2D.Float) currentShape;
            ellipse.setFrame(Math.min(startPoint.x, endPoint.x),
                    Math.min(startPoint.y, endPoint.y),
                    Math.abs(endPoint.x - startPoint.x),
                    Math.abs(endPoint.y - startPoint.y));
        } else if (currentShape instanceof Line2D.Float) {
            Line2D.Float line = (Line2D.Float) currentShape;
            line.setLine(startPoint, endPoint);
        }
    }

    // Draw all shapes, highlighting the hovered shape
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Shape shape : shapes) {
            if (shape == hoveredShape) {
                g2.setColor(Color.RED); // Highlight hovered shape
                g2.draw(shape);
                g2.setColor(Color.BLACK);
            } else {
                g2.draw(shape);
            }
        }
    }

    // Set the type of shape to draw
    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }
}
