import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        WordList wordList = new WordList("data/words.txt");
        System.out.println("Loaded " + wordList.size() + " words");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your guess: ");
        String guess = scanner.nextLine().trim().toLowerCase();
        System.out.print("Enter the colors (g = green, y = yellow, x = grey): ");
        String pattern = scanner.nextLine().trim().toLowerCase();

        Feedback feedback = Feedback.fromInput(guess, pattern);
        System.out.println(feedback);
        scanner.close();
    }
}