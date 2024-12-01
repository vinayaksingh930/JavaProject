import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create the main JFrame that will hold both the text editor and shape drawer
        JFrame frame = new JFrame("Text Editor and Shape Drawer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Create instances of both the TextEditor and ShapeDrawer
        TextEditor textEditor = new TextEditor();
        ShapeDrawer shapeDrawer = new ShapeDrawer();

        // Create a tabbed pane to hold both components
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Text Editor", textEditor);
        tabbedPane.addTab("Shape Drawer", shapeDrawer);

        // Add the tabbed pane to the main frame
        frame.add(tabbedPane, BorderLayout.CENTER);

        // Display the window
        frame.setVisible(true);
    }
}
