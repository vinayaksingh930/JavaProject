import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextEditor extends JPanel {
    private JTextArea textArea;
    private JComboBox<String> fontComboBox;
    private JComboBox<Integer> sizeComboBox;
    private JButton toUpperButton, toLowerButton, countButton;

    public TextEditor() {
        // Initialize the panel layout
        setLayout(new BorderLayout());

        // Create text area for the editor
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Create a panel for the toolbar
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new FlowLayout());

        // Font selection combo box
        fontComboBox = new JComboBox<>(new String[]{"Arial", "Courier", "Times New Roman", "Verdana"});
        fontComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTextFont();
            }
        });
        toolbar.add(new JLabel("Font:"));
        toolbar.add(fontComboBox);

        // Size selection combo box
        sizeComboBox = new JComboBox<>(new Integer[]{12, 14, 16, 18, 20});
        sizeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTextFont();
            }
        });
        toolbar.add(new JLabel("Size:"));
        toolbar.add(sizeComboBox);

        // Upper case button
        toUpperButton = new JButton("Uppercase");
        toUpperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(textArea.getText().toUpperCase());
            }
        });
        toolbar.add(toUpperButton);

        // Lower case button
        toLowerButton = new JButton("Lowercase");
        toLowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(textArea.getText().toLowerCase());
            }
        });
        toolbar.add(toLowerButton);

        // Word count button
        countButton = new JButton("Word/Char Count");
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                int wordCount = text.split("\\s+").length;
                int charCount = text.length();
                JOptionPane.showMessageDialog(null, "Words: " + wordCount + ", Characters: " + charCount);
            }
        });
        toolbar.add(countButton);

        add(toolbar, BorderLayout.NORTH);
    }

    // Update font style and size
    private void updateTextFont() {
        String selectedFont = (String) fontComboBox.getSelectedItem();
        int selectedSize = (int) sizeComboBox.getSelectedItem();
        textArea.setFont(new Font(selectedFont, Font.PLAIN, selectedSize));
    }
}
