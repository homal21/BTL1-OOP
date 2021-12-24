import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    private List <Word> words = new ArrayList<Word>();

    public List<Word> getWords() {
        return words;
    }

    public void sort() {
        words.sort(Word::compareTo);
    }
}
