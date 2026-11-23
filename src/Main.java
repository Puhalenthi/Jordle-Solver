import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        WordList wordList = new WordList("data/words.txt");
        System.out.println("Loaded " + wordList.size() + " words");
    }
}