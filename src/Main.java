import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        WordList wordList = new WordList("data/words.txt");
        System.out.println("Loaded " + wordList.size() + " words");

        System.out.println("cigar, crane gyyxx (expect true): "
                + Solver.isMatching("cigar", Feedback.fromInput("crane", "gyyxx")));
        System.out.println("cigar, crane gxxxx (expect false): "
                + Solver.isMatching("cigar", Feedback.fromInput("crane", "gxxxx")));

        Solver solver = new Solver(wordList.getWords());
        solver.getFeedback(Feedback.fromInput("crane", "gyyxx"));
        System.out.println(solver.getCandidates().size() + " words remain");
        System.out.println(solver.getCandidates());
    }
}