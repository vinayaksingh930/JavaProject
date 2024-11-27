import javax.swing.*;
import java.io.*;

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
}
