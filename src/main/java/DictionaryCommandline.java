import java.util.Scanner;

public class DictionaryCommandline {
        DictionaryManagement dictManage = new DictionaryManagement();
        Scanner sc = new Scanner(System.in);
        public void showAllWords() {
            for (int i=0; i<dictManage.dictionary.getWords().size(); i++) {
                System.out.print((i + 1) +"\t|");
                dictManage.dictionary.getWords().get(i).display();
            }
        }

        public void dictionaryBasic() {
            System.out.println("======= MENU =======");
            while (true) {
                System.out.println("Your choice: ");
                int choice = sc.nextInt();
                if (choice == 1) {
                    dictManage.insertFromCommandline();
                } else if (choice == 2) {
                    showAllWords();
                } else {
                    break;
                }
            }
        }

        public void dictionaryAdvanced() {
            System.out.println("======= MENU =======");
            while (true) {
                System.out.println("Your choice: ");
                int choice = sc.nextInt();
                if (choice == 1) {
                    dictManage.insertFromCommandline();
                } else if (choice == 2) {
                    showAllWords();
                } else if (choice == 3) {
                    dictManage.dictionarySearcher();
                } else {
                    break;
                }
            }
        }



}
