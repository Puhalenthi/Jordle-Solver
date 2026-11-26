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
    public static JLabel suggestionLabel;
    public static JLabel wordsLabel;
    public static JLabel guessLabel;
    public static JLabel messageLabel;
    public static JTextField textField;
    public static JButton[] tiles = new JButton[30];
    public static int row = 0;
    public static String guess = "";
    public static boolean gameOver = false;


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

        JPanel topPanel = new JPanel(new GridLayout(0, 1));
        suggestionLabel = new JLabel("Suggested guess: " + solver.suggestGuess());
        wordsLabel = new JLabel("Words left: " + solver.getCandidates().size());
        guessLabel = new JLabel("Guess: 1");
        suggestionLabel.setHorizontalAlignment(JLabel.CENTER);
        wordsLabel.setHorizontalAlignment(JLabel.CENTER);
        guessLabel.setHorizontalAlignment(JLabel.CENTER);

        topPanel.add(suggestionLabel);
        topPanel.add(wordsLabel);
        topPanel.add(guessLabel);

        JPanel inputPanel = new JPanel();
        textField = new JTextField(10);
        JButton submitButton = new JButton("Submit");
        submitButton.setActionCommand("submit");
        submitButton.addActionListener(new Main());

        inputPanel.add(textField);
        inputPanel.add(submitButton);
        topPanel.add(inputPanel);

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(messageLabel);

        frame.add(topPanel, BorderLayout.NORTH);

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

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();

        if (b.getActionCommand().equals("submit")) {
            if (gameOver) {
                return;
            }

            guess = textField.getText().trim().toLowerCase();
            if (guess.length() != 5) {
                messageLabel.setText("Invalid word: must be 5 letters");
                return;
            }
            if (!isAllLetters(guess)) {
                messageLabel.setText("Invalid word: letters only");
                return;
            }

            messageLabel.setText("");

            for (int i = 0; i < 5; i++) {
                tiles[row * 5 + i].setText(("" + guess.charAt(i)).toUpperCase());
            }

            textField.setText("");
            b.setActionCommand("apply");
            b.setText("Apply Colors");
        } else if (b.getActionCommand().equals("apply")) {
            LetterColor[] colors = new LetterColor[5];
            for (int i = 0; i < 5; i++) {
                String color = tiles[row * 5 + i].getActionCommand();
                if (color.equals("green"))
                    colors[i] = LetterColor.GREEN;
                else if (color.equals("yellow"))
                    colors[i] = LetterColor.YELLOW;
                else
                    colors[i] = LetterColor.GREY;
            }

            int greens = 0;
            for (int i = 0; i < 5; i++) {
                if (colors[i] == LetterColor.GREEN)
                    greens++;
            }
            if (greens == 5) {
                frame.dispose();
                return;
            }

            solver.getFeedback(new Feedback(guess, colors));
            wordsLabel.setText("Words left: " + solver.getCandidates().size());

            row++;

            b.setActionCommand("submit");
            b.setText("Submit");

            if (solver.getCandidates().size() == 0) {
                suggestionLabel.setText("Suggested guess: none");
                messageLabel.setText("Game complete: no matching words");
                gameOver = true;
            } else if (row == 6) {
                messageLabel.setText("Game complete: out of guesses");
                gameOver = true;
            } else {
                suggestionLabel.setText("Suggested guess: " + solver.suggestGuess());
                guessLabel.setText("Guess: " + (row + 1));
                textField.requestFocus();
            }
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

    public static boolean isAllLetters(String guess) {
        String validLetters = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < guess.length(); i++) {
            char letter = guess.charAt(i);
            if (validLetters.indexOf(letter) == -1) { // If the character is not a letter
                return false;
            }
        }

        return true;
    }
}