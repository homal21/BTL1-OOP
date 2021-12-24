import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class AddWordGUI {
    private DictionaryApplication app;
    private JPanel panel = new JPanel();
    private JTextField textField = new JTextField();
    private JTextArea textArea = new JTextArea();
    private JButton acceptButton=new JButton("accept");
    private JButton cancelButton=new JButton("cancel");
    public JPanel getPanel() {
        return panel;
    }

    /**
     * accept button event;
     */
    public void addAcceptButtonListener() {
        this.acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String newTargetWord = textField.getText();
                    String newExplainWord = textArea.getText();
                    Word tmp = new Word(newTargetWord, newExplainWord);
                    app.getDictionary().getWords().add(tmp);
                    app.getDictionary().sort();
                    app.runApplication();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });
    }

    /**
     * Cancel button event;
     */
    public void addCancelButtonListener() {
        this.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    app.runApplication();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    public AddWordGUI(DictionaryApplication dictionaryApplication) {
        app = dictionaryApplication;
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(100, 100, 200, 100);


        this.textField = new JTextField();
        this.textField.setBounds(130, 175, 470, 40);

        this.textArea = new JTextArea();
        this.textArea.setLineWrap(true);
        this.textArea.setBounds(130, 265, 470, 190);

        acceptButton.setBounds(180, 475, 150, 36);
        addAcceptButtonListener();

        cancelButton.setBounds(400, 475, 150, 36);
        addCancelButtonListener();

        this.panel.add(acceptButton);
        this.panel.add(cancelButton);
        this.panel.add(textField);
        this.panel.add(textArea);
    }
}
