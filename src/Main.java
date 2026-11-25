import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main implements ActionListener {
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
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Suggested guess: " + solver.suggestGuess() + "   Words left: " + solver.getCandidates().size());
        frame.add(label, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(6, 5));
        for (int i = 1; i <= 30; i++) {
            JButton button = new JButton("");
            button.setBackground(Color.GRAY);
            button.setActionCommand("gray");
            button.addActionListener(new Main());
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

    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if (b.getActionCommand().equals("gray")) {
            b.setActionCommand("yellow");
            b.setBackground(Color.YELLOW);
        } else if (b.getActionCommand().equals("yellow")) {
            b.setActionCommand("green");
            b.setBackground(Color.GREEN);
        } else if (b.getActionCommand().equals("green")) {
            b.setActionCommand("gray");
            b.setBackground(Color.GRAY);
        }
    }
}