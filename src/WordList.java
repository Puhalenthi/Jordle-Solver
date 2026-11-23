import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WordList {
    private final List<String> words;

    public WordList(String filePath) throws IOException {
        words = Files.readAllLines(Path.of(filePath));
    }

    /**
     * Returns a list containing all the words in the list
     */

    public List<String> getWords() {
        return words;
    }

    /**
     * Returns the total number of words in the list
     */

    public int size() {
        return words.size();
    }
}