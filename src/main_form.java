import javafx.stage.FileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

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
    private ArrayList<String> encryptedLines = new ArrayList<>();
    private ArrayList<String> decryptedLines = new ArrayList<>();
    private FileWriter fileWriter;

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
                        encryptedLines.add(encrypter.encrypt(line));
                    }
                    bufferedReader.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    fileWriter = new FileWriter(encryptPathTxt.getText());
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    encryptedLines.forEach((i) -> {
                        try {
                            bufferedWriter.write(i);
                            bufferedWriter.newLine();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    });
                    bufferedWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                encryptKeyTxt.setText(encrypter.generateKey());
                JOptionPane.showMessageDialog(null, "Encryption Successfully!");
            }
        });
        decryptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decrypter = new Decrypter(decryptKeyTxt.getText());
                try {
                    fileReader = new FileReader(decryptPathTxt.getText());
                    String line = null;
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    while((line = bufferedReader.readLine()) != null) {
                        decryptedLines.add(decrypter.decrypt(line));
                    }
                    bufferedReader.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    fileWriter = new FileWriter(decryptPathTxt.getText());
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    decryptedLines.forEach((i) -> {
                        try {
                            bufferedWriter.write(i);
                            bufferedWriter.newLine();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    });
                    bufferedWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Decryption Successfully!");
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
