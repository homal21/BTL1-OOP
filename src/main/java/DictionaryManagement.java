import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DictionaryManagement {
    public Dictionary dictionary = new Dictionary();

    public Dictionary getDictionary() {
        return dictionary;
    }

    static Scanner sc = new Scanner(System.in);

    /**
     * them vao tu dien.
     */
    public void insertFromCommandline() {
        System.out.println("numbers of words: ");
        int n = sc.nextInt();
        for (int i=1; i<=n; i++) {
            System.out.println("word target: ");
            String a = sc.next();
            System.out.println("explain: ");
            sc.nextLine();
            String b = sc.nextLine();
            Word tmp = new Word(a, b);
            dictionary.getWords().add(tmp);
        }
    }
    String PATH = "src\\english-vietnamese.txt";
    /**
     * doc du lieu tu file.
     */
    public void insertFromFile() {
        try {
            BufferedReader buffReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(PATH), StandardCharsets.UTF_8));
            String str;
            while ((str = buffReader.readLine()) != null) {
                String[] words = str.split("\t");
                if (words.length == 2) {
                    words[1] = words[1].replace("#", "\n");
                    this.dictionary.getWords().add(new Word(words[0], words[1]));
                }
            }
            this.dictionary.sort();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * tim kiem tu.
     */
    public void dictionaryLookup() {
        System.out.println("Find: ");
        String s = sc.nextLine();
        for (Word word : dictionary.getWords()) {
            if (word.word_target.equals(s)) {
                System.out.println(word.word_explain);
                break;
            }
        }
    }

    /**
     * sua nghia.
     */
    public void editExplainFromCommandLine (){
        System.out.println("Input Word explain you want to change: ");
        String knownTarget = sc.nextLine();
        System.out.println("Input new explain: ");
        String newExplain = sc.nextLine();

        for (int i=0; i< dictionary.getWords().size(); i++) {
            if (dictionary.getWords().get(i).word_target.equals(knownTarget)) {
                dictionary.getWords().get(i).word_explain = newExplain;
                break;
            }
        }
    }

    /**
     * sua tu.
     */
    public void editTargetFromCommandLine() {
        System.out.println("Input Word Target you want to change: ");
        String knowTarget = sc.nextLine();
        System.out.println("Input new Target: ");
        String newTarget = sc.nextLine();

        for (int i=0; i < dictionary.getWords().size(); i++) {
            if (dictionary.getWords().get(i).word_target.equals(knowTarget)) {
                dictionary.getWords().get(i).word_target = newTarget;
                break;
            }
        }
    }

    /**
     * tim kiem nang cao.
     */
    public void dictionarySearcher() {
        System.out.println("Find: ");
        String wordFind = sc.nextLine();
        for (Word word : dictionary.getWords()) {
            if (wordFind.length() <= word.word_target.length()) {
                if (wordFind.equals(word.word_target.substring(0,wordFind.length()))) {
                    System.out.println(word.word_target);
                }
            }
        }
    }

    /**
     * xuat du lieu ra file.
     */
    public void dictionaryExportToFile () {
        try {
            FileWriter fileWriter = new FileWriter(PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : dictionary.getWords()) {
                bufferedWriter.write(word.word_target + ", " + word.word_explain + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
