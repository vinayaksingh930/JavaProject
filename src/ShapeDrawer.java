import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
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
                    case "triangle":
                        currentShape = createTriangle(startPoint);
                        break;
                    case "oval":
                        currentShape = createOval(startPoint);
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

    // Create a triangle shape based on the starting point
    private Shape createTriangle(Point startPoint) {
        int x1 = startPoint.x;
        int y1 = startPoint.y;
        int x2 = x1 + 50;
        int y2 = y1;
        int x3 = x1 + 25;
        int y3 = y1 - 50;
        Polygon triangle = new Polygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
        return triangle;
    }

    // Create an oval shape based on the starting point
    private Shape createOval(Point startPoint) {
        int x = startPoint.x;
        int y = startPoint.y;
        int width = 50;
        int height = 30;
        return new Ellipse2D.Float(x, y, width, height); // Oval shape
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
        } else if (shape instanceof Polygon) {
            Polygon polygon = (Polygon) shape;
            // Resize polygon (triangle)
            if (polygon.npoints == 3) {
                polygon.xpoints[2] = newPoint.x;
                polygon.ypoints[2] = newPoint.y;
            }
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
        } else if (currentShape instanceof Polygon) {
            Polygon polygon = (Polygon) currentShape;
            // For triangle, resize based on the mouse drag
            if (polygon.npoints == 3) {
                polygon.xpoints[2] = endPoint.x;
                polygon.ypoints[2] = endPoint.y;
            }
        }
    }

    // Draw all shapes, highlighting the hovered shape
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Shape shape : shapes) {
            if (shape == hoveredShape) {
                g2.setColor(Color.RED);  // Highlight hovered shape
            } else {
                g2.setColor(Color.BLACK);
            }
            g2.draw(shape);
        }
    }

    // Method to dynamically set the shape type
    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shape Drawer");
        ShapeDrawer drawer = new ShapeDrawer();
        frame.add(drawer);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Example to set shape type (can be changed dynamically)
        drawer.setShapeType("Oval");
    }
}
