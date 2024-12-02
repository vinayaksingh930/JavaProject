import java.awt.*;
import java.io.*;
import java.util.StringTokenizer;
import javax.swing.*;

public class TextEditorFunctions {

    // Method to open a file
    public static void openFile(JTextArea textArea, JFileChooser fileChooser, JFrame parent) {
        int returnValue = fileChooser.showOpenDialog(parent);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parent, "Error opening file: " + e.getMessage());
            }
        }
    }

    // Method to save a file
    public static void saveFile(JTextArea textArea, JFileChooser fileChooser, JFrame parent) {
        int returnValue = fileChooser.showSaveDialog(parent);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parent, "Error saving file: " + e.getMessage());
            }
        }
    }

    // Method to find and replace text
    public static void findAndReplace(JTextArea textArea) {
        String findWord = JOptionPane.showInputDialog("Enter word to find:");
        String replaceWord = JOptionPane.showInputDialog("Enter word to replace with:");

        if (findWord != null && replaceWord != null) {
            String content = textArea.getText();
            content = content.replace(findWord, replaceWord);
            textArea.setText(content);
        }
    }

    // Updated changeFont method to remove font selection and allow user to input font name directly
    public static void changeFont(JTextArea textArea) {
        // Font style options (Regular, Italic, Cursive)
        String[] styleOptions = {"Regular", "Italic", "Cursive"};
        String fontStyle = (String) JOptionPane.showInputDialog(
            null,
            "Choose Font Style",
            "Select Font Style",
            JOptionPane.QUESTION_MESSAGE,
            null,
            styleOptions,
            styleOptions[0]
        );

        if (fontStyle != null) {
            // Default font name and size for simplicity
            String fontName = "Arial";  // You can set a default font name if desired
            int fontSize = 12;          // You can set a default font size if desired

            // Determine the font style to apply
            int style = Font.PLAIN; // Default to Regular
            if ("Italic".equals(fontStyle)) {
                style = Font.ITALIC;  // Set style to Italic
            } else if ("Cursive".equals(fontStyle)) {
                // Cursive is a variant of Italic, so it can be treated similarly
                style = Font.ITALIC;  // For demonstration purposes, we use Italic
                // Optionally, you could use a cursive-like font like "Brush Script"
                fontName = "Brush Script";  // Change the font to Brush Script for cursive appearance
            }

            // Set the font with the selected style and apply it to the JTextArea
            Font newFont = new Font(fontName, style, fontSize);
            textArea.setFont(newFont);  // Apply the new font to the text area
        }
    }

    // Method to count words and characters
    public static void countWordsAndCharacters(JTextArea textArea) {
        String selectedText = textArea.getSelectedText();
        if (selectedText != null && !selectedText.isEmpty()) {
            int wordCount = new StringTokenizer(selectedText).countTokens();
            int charCount = selectedText.length();
            JOptionPane.showMessageDialog(null, "Words: " + wordCount + ", Characters: " + charCount);
        } else {
            JOptionPane.showMessageDialog(null, "No text selected!");
        }
    }

    // Method to change case (uppercase/lowercase)
    public static void changeCase(JTextArea textArea) {
        String selectedText = textArea.getSelectedText();
        if (selectedText != null && !selectedText.isEmpty()) {
            String[] options = {"UPPERCASE", "lowercase"};
            int choice = JOptionPane.showOptionDialog(null, "Choose case:", "Change Case",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (choice == 0) {
                textArea.replaceSelection(selectedText.toUpperCase());
            } else if (choice == 1) {
                textArea.replaceSelection(selectedText.toLowerCase());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No text selected!");
        }
    }

    // Method to insert shapes
    public static void insertShapes(JTextArea textArea) {
        String[] shapes = {"Rectangle", "Circle", "Triangle", "Line", "Square"};
        String shape = (String) JOptionPane.showInputDialog(null, "Choose a shape to insert:",
            "Insert Shapes", JOptionPane.QUESTION_MESSAGE, null, shapes, shapes[0]);
        if (shape != null) {
            textArea.append("[Shape: " + shape + "]\n");
        }
    }
}
