import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.*;

public class ShapeDrawer extends JPanel {
    private ArrayList<Shape> shapes;
    private String currentShape;
    private Shape hoveredShape;  // To track the hovered shape
    private static final int ZOOM_FACTOR = 20;  // Zoom factor for hover effect
    private Color selectedColor;  // To store the selected color for shapes

    public ShapeDrawer() {
        shapes = new ArrayList<>();
        currentShape = "Rectangle";  // Default shape to draw
        selectedColor = Color.RED;  // Default color for shapes

        // Set up the panel
        setBackground(Color.white);
        setPreferredSize(new Dimension(800, 600));

        // Create toolbar to select shape and perform operations
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new FlowLayout());

        JButton rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(e -> currentShape = "Rectangle");
        toolbar.add(rectangleButton);

        JButton circleButton = new JButton("Circle");
        circleButton.addActionListener(e -> currentShape = "Circle");
        toolbar.add(circleButton);

        JButton lineButton = new JButton("Line");
        lineButton.addActionListener(e -> currentShape = "Line");
        toolbar.add(lineButton);

        JButton triangleButton = new JButton("Triangle");
        triangleButton.addActionListener(e -> currentShape = "Triangle");
        toolbar.add(triangleButton);

        JButton ovalButton = new JButton("Oval");
        ovalButton.addActionListener(e -> currentShape = "Oval");
        toolbar.add(ovalButton);

        // Color selection button
        JButton colorButton = new JButton("Change Color");
        colorButton.addActionListener(e -> {
            selectedColor = (selectedColor == Color.RED) ? Color.GREEN : Color.RED;
            if (hoveredShape != null) {
                hoveredShape.setColor(selectedColor);
            }
            repaint();
        });
        toolbar.add(colorButton);

        // Add toolbar to the frame
        add(toolbar, BorderLayout.NORTH);

        // Add a mouse listener to draw shapes
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (currentShape.equals("Rectangle")) {
                    shapes.add(new RectangleShape(e.getX(), e.getY(), 100, 50, selectedColor));
                } else if (currentShape.equals("Circle")) {
                    shapes.add(new CircleShape(e.getX(), e.getY(), 50, selectedColor));
                } else if (currentShape.equals("Line")) {
                    shapes.add(new LineShape(e.getX(), e.getY(), e.getX() + 100, e.getY(), selectedColor));
                } else if (currentShape.equals("Triangle")) {
                    shapes.add(new TriangleShape(e.getX(), e.getY(), 100, 50, selectedColor));
                } else if (currentShape.equals("Oval")) {
                    shapes.add(new OvalShape(e.getX(), e.getY(), 100, 50, selectedColor));
                }
                repaint();
            }
        });

        // Mouse motion listener to detect hover effect
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point mousePoint = e.getPoint();
                hoveredShape = null;
                for (Shape shape : shapes) {
                    if (shape.contains(mousePoint)) {
                        hoveredShape = shape;
                        break;
                    }
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            // Apply zoom effect if the shape is hovered
            if (shape == hoveredShape) {
                shape.zoomIn(g, ZOOM_FACTOR);
            } else {
                shape.draw(g);
            }
        }
    }

    // Shape interface
    interface Shape {
        void draw(Graphics g);
        boolean contains(Point p);
        void zoomIn(Graphics g, int zoomFactor);  // Zoom-in for hovered shape
        void setColor(Color color);  // Set the color of the shape
        void resize(double scaleFactor);  // Resize the shape
    }

    // Rectangle class
    class RectangleShape implements Shape {
        int x, y, width, height;
        Color color;

        RectangleShape(int x, int y, int width, int height, Color color) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
        }

        @Override
        public void draw(Graphics g) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }

        @Override
        public boolean contains(Point p) {
            return new Rectangle(x, y, width, height).contains(p);
        }

        @Override
        public void zoomIn(Graphics g, int zoomFactor) {
            g.setColor(color);
            g.fillRect(x - zoomFactor / 2, y - zoomFactor / 2, width + zoomFactor, height + zoomFactor);
        }

        @Override
        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public void resize(double scaleFactor) {
            this.width *= scaleFactor;
            this.height *= scaleFactor;
        }
    }

    // Circle class
    class CircleShape implements Shape {
        int x, y, radius;
        Color color;

        CircleShape(int x, int y, int radius, Color color) {
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.color = color;
        }

        @Override
        public void draw(Graphics g) {
            g.setColor(color);
            g.fillOval(x, y, radius, radius);
        }

        @Override
        public boolean contains(Point p) {
            return new Ellipse2D.Float(x, y, radius, radius).contains(p);
        }

        @Override
        public void zoomIn(Graphics g, int zoomFactor) {
            g.setColor(color);
            g.fillOval(x - zoomFactor / 2, y - zoomFactor / 2, radius + zoomFactor, radius + zoomFactor);
        }

        @Override
        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public void resize(double scaleFactor) {
            this.radius *= scaleFactor;
        }
    }

    // Line class
    class LineShape implements Shape {
        int x1, y1, x2, y2;
        Color color;

        LineShape(int x1, int y1, int x2, int y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }

        @Override
        public void draw(Graphics g) {
            g.setColor(color);
            g.drawLine(x1, y1, x2, y2);
        }

        @Override
        public boolean contains(Point p) {
            // Simplified; you may need more complex hit testing for line shapes
            return Math.abs(x1 - p.x) < 5 && Math.abs(y1 - p.y) < 5;
        }

        @Override
        public void zoomIn(Graphics g, int zoomFactor) {
            g.setColor(color);
            g.drawLine(x1 - zoomFactor, y1 - zoomFactor, x2 + zoomFactor, y2 + zoomFactor);
        }

        @Override
        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public void resize(double scaleFactor) {
            this.x2 = (int)(this.x2 * scaleFactor);
            this.y2 = (int)(this.y2 * scaleFactor);
        }
    }

    // Triangle class
    class TriangleShape implements Shape {
        int x, y, base, height;
        Color color;

        TriangleShape(int x, int y, int base, int height, Color color) {
            this.x = x;
            this.y = y;
            this.base = base;
            this.height = height;
            this.color = color;
        }

        @Override
        public void draw(Graphics g) {
            int[] xPoints = {x, x + base / 2, x - base / 2};
            int[] yPoints = {y, y + height, y + height};
            g.setColor(color);
            g.fillPolygon(xPoints, yPoints, 3);
        }

        @Override
        public boolean contains(Point p) {
            // Simplified for triangle containment
            return false;
        }

        @Override
        public void zoomIn(Graphics g, int zoomFactor) {
            g.setColor(color);
            int newBase = (int)(base * 1.1);
            int newHeight = (int)(height * 1.1);
            int[] xPoints = {x, x + newBase / 2, x - newBase / 2};
            int[] yPoints = {y, y + newHeight, y + newHeight};
            g.fillPolygon(xPoints, yPoints, 3);
        }

        @Override
        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public void resize(double scaleFactor) {
            this.base *= scaleFactor;
            this.height *= scaleFactor;
        }
    }

    // Oval class
    class OvalShape implements Shape {
        int x, y, width, height;
        Color color;

        OvalShape(int x, int y, int width, int height, Color color) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
        }

        @Override
        public void draw(Graphics g) {
            g.setColor(color);
            g.fillOval(x, y, width, height);
        }

        @Override
        public boolean contains(Point p) {
            return new Ellipse2D.Float(x, y, width, height).contains(p);
        }

        @Override
        public void zoomIn(Graphics g, int zoomFactor) {
            g.setColor(color);
            g.fillOval(x - zoomFactor / 2, y - zoomFactor / 2, width + zoomFactor, height + zoomFactor);
        }

        @Override
        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public void resize(double scaleFactor) {
            this.width *= scaleFactor;
            this.height *= scaleFactor;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shape Drawer");
        ShapeDrawer panel = new ShapeDrawer();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
