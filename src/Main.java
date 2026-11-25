import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Scanner;

public class Main implements ActionListener {
    public static JFrame frame;
    public static Solver solver;
    public static JTextField textField;
    public static JButton[] tiles = new JButton[30];
    public static int row = 0;
    public static String guess = "";

    public static void main(String[] args) throws IOException {
        WordList wordList = new WordList("data/words.txt");
        solver = new Solver(wordList.getWords());

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
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.add(label, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(6, 5));
        for (int i = 0; i < 30; i++) {
            JButton button = new JButton("");
            button.setFont(new Font("SansSerif", Font.BOLD, 24));
            button.setBackground(Color.GRAY);
            button.setActionCommand("gray");
            button.addActionListener(new Main());
            tiles[i] = button;
            panel.add(button);
        }
        frame.add(panel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        textField = new JTextField(10);
        JButton submitButton = new JButton("Submit");
        submitButton.setActionCommand("submit");
        submitButton.addActionListener(new Main());
        inputPanel.add(textField);
        inputPanel.add(submitButton);
        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();

        if (b.getActionCommand().equals("submit")) {
            guess = textField.getText().toLowerCase();
            for (int i = 0; i < 5; i++) {
                tiles[row * 5 + i].setText(("" + guess.charAt(i)).toUpperCase());
            }
            textField.setText("");
            b.setActionCommand("apply");
            b.setText("Apply Colors");
        } else if (b.getActionCommand().equals("gray")) {
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