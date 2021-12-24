import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import java.io.IOException;


public class DictionaryApplication {
    private DictionaryManagement dictManagement = new DictionaryManagement();
    private Dictionary dictionary;
    private JFrame mainFrame = new JFrame("OK");
    private JPanel mainPanel = new JPanel();
    private JTextField textField = new JTextField(10);
    private JTextArea textArea = new JTextArea(5,5);
    private JScrollPane scrollPane = new JScrollPane();
    private JScrollPane scrollPaneArea = new JScrollPane();
    private JList list = new JList();
    private DefaultListModel listModel = new DefaultListModel();
    private JButton searchButton = new JButton("Search");
    private JButton deleteButton = new JButton("Delete");
    private JButton addButton = new JButton("Add");
    private JButton voiceButton = new JButton("Voice");

    private int frameWidth = 800;
    private int frameHeigth = 800;

    public Dictionary getDictionary() {
        return dictionary;
    }


    /**
     * List event.
     */
    public void addSelectionListener() {
        this.list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String s = (String) list.getSelectedValue();
                for (Word word : dictManagement.dictionary.getWords()) {
                    if (word.word_target.equals(s)) {
                        textArea.setText(word.word_explain);
                        break;
                    }
                }
                scrollPaneArea.setViewportView(textArea);
            }
        });
    }

    /**
     * search button event.
     */
    public void addSearchButtonListener() {
        this.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textField.getText();
                for (Word word : dictManagement.dictionary.getWords()) {
                    if (word.word_target.equals(s)) {
                        textArea.setText(word.word_explain);
                        scrollPaneArea.setViewportView(textArea);
                        break;
                    }
                }

            }
        });
    }

    /**
     *  text field event;
     */
    public void addTextFieldListener() {
        this.textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String wordFind = textField.getText();
                if (wordFind != "") {
                    listModel = new DefaultListModel();
                    for (Word word : dictManagement.dictionary.getWords()) {
                        if (wordFind.length() <= word.word_target.length()) {
                            if (wordFind.equals(word.word_target.substring(0, wordFind.length()))) {
                                listModel.addElement(word.word_target);
                            }
                        }
                    }
                    list = new JList(listModel);
                    scrollPane.setViewportView(list);
                    addSelectionListener();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String wordFind = textField.getText();
                if (wordFind != "") {
                    listModel = new DefaultListModel();
                    for (Word word : dictManagement.dictionary.getWords()) {
                        if (wordFind.length() <= word.word_target.length()) {
                            if (wordFind.equals(word.word_target.substring(0, wordFind.length()))) {
                                listModel.addElement(word.word_target);
                            }
                        }
                    }
                    list = new JList(listModel);
                    scrollPane.setViewportView(list);
                    addSelectionListener();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String wordFind = textField.getText();
                if (wordFind != "") {
                    listModel = new DefaultListModel();
                    for (Word word : dictManagement.dictionary.getWords()) {
                        if (wordFind.length() <= word.word_target.length()) {
                            if (wordFind.equals(word.word_target.substring(0, wordFind.length()))) {
                                listModel.addElement(word.word_target);
                            }
                        }
                    }
                    list = new JList(listModel);
                    scrollPane.setViewportView(list);
                    addSelectionListener();
                }
            }
        });
    }

    /**
     * delete button event.
     */
    public void addDeleteButtonListener() {
        this.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = list.getSelectedValue();
                String s = (String) list.getSelectedValue();
                listModel.removeElement(o);
                for (Word word : dictManagement.dictionary.getWords()) {
                   if (word.word_target.equals(s)) {
                       dictManagement.dictionary.getWords().remove(word);
                       break;
                   }
                }
                textArea.setText("");


            }

        });
    }

    /**
     * move to add GUI.
     */
    public void moveToAddGUI() {
        AddWordGUI addWordGUI = new AddWordGUI(this);
        mainFrame.remove(mainPanel);
        mainFrame.add(addWordGUI.getPanel());
        mainFrame.setVisible(true);
    }

    /**
     * add button event.
     */
    public void addButtonListener() {
        this.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveToAddGUI();
            }
        });
    }

    /**
     * voice button event;
     */
    public void addVoiceButtonListener() {
        this.voiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String wordFromList= (String) list.getSelectedValue();
                    Pronounce voice = new Pronounce();
                    try{
                        voice.pronounce(wordFromList);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
            }

        });
    }


    public void runApplication() throws IOException {

        mainFrame = new JFrame();
        mainFrame.setSize(frameWidth, frameHeigth);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainPanel.setLayout(null);

        textArea.setEditable(false);

        listModel = new DefaultListModel();
        for (Word word : dictManagement.dictionary.getWords()) {
            listModel.addElement(word.word_target);
        }

        this.list = new JList(listModel);
        this.list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.list.setVisibleRowCount(-1);
        this.list.setLayoutOrientation(JList.VERTICAL);

        scrollPane.setViewportView(list);
        scrollPane.setBounds(10,70,300,600);
        scrollPaneArea.setBounds(400,70,300,600);

        this.addSelectionListener();

        this.searchButton.setBounds(350,10,100,50);
        this.addSearchButtonListener();

        this.deleteButton.setBounds(450,10,100,50);
        this.addDeleteButtonListener();

        this.addButton.setBounds(550,10,100,50);
        this.addButtonListener();

        this.voiceButton.setBounds(650,10,100,50);
        this.addVoiceButtonListener();

        this.textField.setBounds(10,40,300,30);
        addTextFieldListener();

        mainPanel.add(searchButton);
        mainPanel.add(deleteButton);
        mainPanel.add(addButton);
        mainPanel.add(voiceButton);
        mainPanel.add(textField);
        mainPanel.add(scrollPane);
        mainPanel.add(scrollPaneArea);



        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);


    }

    public static void main(String[] args) throws IOException {
        DictionaryApplication myApp = new DictionaryApplication();
        myApp.dictManagement.insertFromFile();
        myApp.dictionary = myApp.dictManagement.getDictionary() ;
        myApp.runApplication();
    }

}
