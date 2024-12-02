import java.awt.*;
import java.io.*;
import java.util.StringTokenizer;
import javax.swing.*;

public class TextEditorFunctions {

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

    public static void findAndReplace(JTextArea textArea) {
        String findWord = JOptionPane.showInputDialog("Enter word to find:");
        String replaceWord = JOptionPane.showInputDialog("Enter word to replace with:");

        if (findWord != null && replaceWord != null) {
            String content = textArea.getText();
            content = content.replace(findWord, replaceWord);
            textArea.setText(content);
        }
    }

    public static void changeFont(JTextArea textArea) {
        String fontName = JOptionPane.showInputDialog("Enter Font Name (e.g., Arial):");
        String fontSize = JOptionPane.showInputDialog("Enter Font Size:");
        if (fontName != null && fontSize != null) {
            try {
                int size = Integer.parseInt(fontSize);
                textArea.setFont(new Font(fontName, Font.PLAIN, size));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid font size entered!");
            }
        }
    }

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

    public static void insertShapes(JTextArea textArea) {
        String[] shapes = {"Rectangle", "Circle", "Triangle", "Line", "Square"};
        String shape = (String) JOptionPane.showInputDialog(null, "Choose a shape to insert:",
                "Insert Shapes", JOptionPane.QUESTION_MESSAGE, null, shapes, shapes[0]);
        if (shape != null) {
            textArea.append("[Shape: " + shape + "]\n");
        }
    }
}
