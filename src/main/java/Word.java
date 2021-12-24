import java.sql.SQLOutput;

public class Word implements Comparable<Word>{
    public String word_target;
    public String word_explain;

    public String getWord_target() {
        return word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    @Override
    public int compareTo(Word anotherWord) {
        return (this.word_target.compareToIgnoreCase(anotherWord.getWord_target()));
    }

    public void display() {
        System.out.println(word_target + "\t" +word_explain);
    }
}
