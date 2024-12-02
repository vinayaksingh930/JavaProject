import java.awt.*;
import javax.swing.*;

public class TextEditorFrame extends JFrame {
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private ShapeDrawer shapeDrawer;

    public TextEditorFrame() {
        setTitle("Java Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        shapeDrawer = new ShapeDrawer();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Text Editor", scrollPane);
        tabbedPane.addTab("Shape Drawer", shapeDrawer);

        add(createMenuBar(), BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

        fileChooser = new JFileChooser();
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        openItem.addActionListener(e -> TextEditorFunctions.openFile(textArea, fileChooser, this));
        saveItem.addActionListener(e -> TextEditorFunctions.saveFile(textArea, fileChooser, this));
        fileMenu.add(openItem);
        fileMenu.add(saveItem);

        // Edit menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");
        JMenuItem findReplaceItem = new JMenuItem("Find & Replace");

        JMenuItem fontItem = new JMenuItem("Change Font");
        fontItem.addActionListener(e -> TextEditorFunctions.changeFont(textArea));

        JMenuItem wordCountItem = new JMenuItem("Count Words & Characters");
        wordCountItem.addActionListener(e -> TextEditorFunctions.countWordsAndCharacters(textArea));

        JMenuItem caseChangeItem = new JMenuItem("Change Case");
        caseChangeItem.addActionListener(e -> TextEditorFunctions.changeCase(textArea));

        cutItem.addActionListener(e -> textArea.cut());
        copyItem.addActionListener(e -> textArea.copy());
        pasteItem.addActionListener(e -> textArea.paste());
        findReplaceItem.addActionListener(e -> TextEditorFunctions.findAndReplace(textArea));

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(findReplaceItem);
        editMenu.add(fontItem);
        editMenu.add(wordCountItem);
        editMenu.add(caseChangeItem);

        // Shape menu
        JMenu shapeMenu = new JMenu("Shapes");
        JMenuItem rectangleItem = new JMenuItem("Rectangle");
        JMenuItem circleItem = new JMenuItem("Circle");
        JMenuItem lineItem = new JMenuItem("Line");
        JMenuItem triangleItem = new JMenuItem("Triangle");
        JMenuItem ovalItem = new JMenuItem("Oval");  // Replaced "Hexagon" with "Oval"

        // Set shape type for each option
        rectangleItem.addActionListener(e -> shapeDrawer.setShapeType("Rectangle"));
        circleItem.addActionListener(e -> shapeDrawer.setShapeType("Circle"));
        lineItem.addActionListener(e -> shapeDrawer.setShapeType("Line"));
        triangleItem.addActionListener(e -> shapeDrawer.setShapeType("Triangle"));
        ovalItem.addActionListener(e -> shapeDrawer.setShapeType("Oval"));  // Added action for Oval

        shapeMenu.add(rectangleItem);
        shapeMenu.add(circleItem);
        shapeMenu.add(lineItem);
        shapeMenu.add(triangleItem);
        shapeMenu.add(ovalItem);  // Added Oval menu item

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(shapeMenu);

        return menuBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextEditorFrame frame = new TextEditorFrame();
            frame.setVisible(true);
        });
    }
}
