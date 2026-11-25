import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class Main {
    static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }

    public static void createGUI() {
        frame = new JFrame("Jordle");
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Suggested guess: ");
        frame.add(label, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(6, 5));
        for (int i = 1; i <= 30; i++) {
            JButton button = new JButton("");
            panel.add(button);
        }
        frame.add(panel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        JTextField textField = new JTextField(10);
        JButton submitButton = new JButton("Submit");
        inputPanel.add(textField);
        inputPanel.add(submitButton);
        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}