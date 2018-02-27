import javafx.stage.FileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class main_form extends JFrame {
    private JTabbedPane tabbedPane;
    private JTextField encryptPathTxt;
    private JButton encryptBrowseBtn;
    private JTextField encryptKeyTxt;
    private JButton encryptBtn;
    private JTextField decryptPathTxt;
    private JButton decryptBrowseBtn;
    private JTextField decryptKeyTxt;
    private JButton decryptBtn;
    private JPanel main_panel;
    private JPanel encryptPanel;
    private JPanel decryptPanel;
    private JButton encryptClrBtn;
    private JButton decryptClrBtn;

    private Encrypter encrypter;
    private Decrypter decrypter;
    private FileReader fileReader;

    public main_form() throws HeadlessException {

        setMinimumSize(new Dimension(600, 500));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(main_panel);
//        getContentPane().setBackground(Color.ORANGE);
        tabbedPane.setBackground(Color.lightGray);
        encryptPanel.setBackground(Color.lightGray);
        decryptPanel.setBackground(Color.lightGray);
        setLocation(100,100);


        encryptBrowseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptPathTxt.setText(chooseFile());
            }
        });
        decryptBrowseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptPathTxt.setText(chooseFile());
            }
        });
        decryptClrBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptPathTxt.setText("");
                decryptKeyTxt.setText("");
            }
        });
        encryptClrBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptPathTxt.setText("");
                encryptKeyTxt.setText("");
            }
        });
        encryptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encrypter = new Encrypter();
                try {
                    fileReader = new FileReader(encryptPathTxt.getText());
                    String line = null;
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    while((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);
                    }
                    bufferedReader.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        decryptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decrypter = new Decrypter();
            }
        });
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        main_form mainForm = new main_form();
        mainForm.setVisible(true);
    }

    public String chooseFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose a Text File");
        chooser.setMultiSelectionEnabled(false);
        chooser.setApproveButtonText("Select File");
        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text Files (.txt)","txt");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(txtFilter);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    +  chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    +  chooser.getSelectedFile());
            return String.valueOf(chooser.getSelectedFile().getAbsolutePath());
        }
        else {
            System.out.println("No Selection ");
            return null;
        }
    }
}
