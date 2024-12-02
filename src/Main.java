import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextEditorFrame editor = new TextEditorFrame();
            editor.setVisible(true);
        });
    }
}
