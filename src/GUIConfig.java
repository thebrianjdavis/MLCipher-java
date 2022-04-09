import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;

// GUIConfig contains all static methods to operate on objects within GUI class
public class GUIConfig {

    public static void formatFrame(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(820, 632);
        frame.getContentPane().setBackground(new Color(60, 63, 65));
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void formatHelpField(JTextArea helpField) {
        String helpText = "MultiLayerCipher -- Written by Brian Davis\n\n" +
                "This application allows the user to paste in text" +
                " and encrypt or decrypt the text based on a user-provided" +
                " key/password. The button to return to this screen is in" +
                " the upper-right hand corner and is labeled \"Help\". There" +
                " are buttons to paste text from the clipboard or copy text to" +
                " the clipboard above the respective fields that use these" +
                " functions. If this application has been used to either" +
                " encrypt or decrypt text, the contents of the computer's" +
                " clipboard will be cleared upon exiting.\n" +
                "To change between encryption or decryption, use the radio buttons" +
                " in the lower left-hand corner of the screen. Then use the button" +
                " labeled either \"Encrypt/Decrypt\" to process the provided text" +
                " using the provided key. Lastly, the \"Clear Screen\" button will" +
                " erase all text from all fields.\n\n" +
                "Please contact thebrianjdavis@gmail.com with any questions/suggestions." +
                "\nCHEERS!";
        helpField.setBounds(10, 150, 800, 220);
        helpField.setText(helpText);
        helpField.setLineWrap(true);
        helpField.setWrapStyleWord(true);
        helpField.setBackground(new Color (60, 63, 65));
        helpField.setForeground(new Color (187, 187, 187));
        helpField.setFont(new Font("Arial", Font.PLAIN, 15));
        helpField.setFocusable(false);
        helpField.setEditable(false);
    }

    public static void formatKeyLabel(JLabel keyLabel) {
        keyLabel.setText("Key/Password: ");
        keyLabel.setBounds(10, 5, 800, 30);
        labelCommonAttributes(keyLabel);
    }

    public static void formatKeyField(JTextArea keyField) {
        keyField.setBounds(10, 40, 800, 120);
        String fieldText = "Type your key/password here...";
        textareaCommonAttributes(keyField, fieldText);
    }

    public static void formatKeyScroll(JScrollPane keyScroll) {
        keyScroll.setBounds(10, 40, 800, 120);
        scrollCommonAttributes(keyScroll);
    }

    public static void formatUserTextLabel(JLabel userTextLabel,  boolean encode) {
        userTextLabel.setText("Text to " + ((encode) ? "encode: " : "decode: "));
        userTextLabel.setBounds(10, 165, 800, 30);
        labelCommonAttributes(userTextLabel);
    }

    public static void formatUserTextField(JTextArea userTextField) {
        String fieldText = "Type your text here...";
        userTextField.setBounds(10, 200, 800, 120);
        textareaCommonAttributes(userTextField, fieldText);
    }

    public static void formatUserTextScroll(JScrollPane userTextScroll) {
        userTextScroll.setBounds(10, 200, 800, 120);
        scrollCommonAttributes(userTextScroll);
    }

    public static void formatCipherTextLabel(JLabel cipherTextLabel, boolean encode) {
        cipherTextLabel.setText((encode) ? "Encrypted text: " : "Decrypted text: ");
        cipherTextLabel.setBounds(10, 325, 800, 30);
        labelCommonAttributes(cipherTextLabel);
    }

    public static void formatCipherTextField(JTextArea cipherTextField) {
        String fieldText = "";
        cipherTextField.setBounds(10, 360, 800, 120);
        textareaCommonAttributes(cipherTextField, fieldText);
        cipherTextField.setEditable(false);
        cipherTextField.setFocusable(false);
    }

    public static void formatCipherTextScroll(JScrollPane cipherTextScroll) {
        cipherTextScroll.setBounds(10, 360, 800, 120);
        scrollCommonAttributes(cipherTextScroll);
    }

    public static void formatRadioButtons(JRadioButton encryptMode, JRadioButton decryptMode) {
        ButtonGroup buttonGroup = new ButtonGroup();
        encryptMode.setSelected(true);
        encryptMode.setFocusable(false);
        encryptMode.setBounds(10, 490, 240, 50);
        encryptMode.setBackground(new Color (60, 63, 65));
        encryptMode.setForeground(new Color (187, 187, 187));
        encryptMode.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonGroup.add(encryptMode);

        decryptMode.setFocusable(false);
        decryptMode.setBounds(10, 535, 240, 50);
        decryptMode.setBackground(new Color (60, 63, 65));
        decryptMode.setForeground(new Color (187, 187, 187));
        decryptMode.setFont(new Font("Arial", Font.PLAIN, 20));
        buttonGroup.add(decryptMode);
    }

    public static void formatActionButtons(JButton pasteUserText, JButton copyCipherText, JButton clearScreen, JButton callCipher, boolean encode) {
        pasteUserText.setBounds(570, 163, 240, 35);
        pasteUserText.setText("Paste text from Clipboard");
        pasteUserText.setFont(new Font("Arial", Font.PLAIN, 18));
        pasteUserText.setFocusable(false);
        copyCipherText.setBounds(570, 323, 240, 35);
        copyCipherText.setText("Copy text to Clipboard");
        copyCipherText.setFont(new Font("Arial", Font.PLAIN, 18));
        copyCipherText.setFocusable(false);
        clearScreen.setBounds(605, 485, 200, 50);
        clearScreen.setText("Clear Screen");
        clearScreen.setFont(new Font("Arial", Font.PLAIN, 24));
        clearScreen.setFocusable(false);
        callCipher.setBounds(605, 540, 200, 50);
        callCipher.setText((encode) ? "Encrypt" : "Decrypt");
        callCipher.setFont(new Font("Arial", Font.PLAIN, 24));
        callCipher.setFocusable(false);
    }

    public static void formatGetHelpButton(JButton getHelp) {
        getHelp.setBounds(730, 3, 80, 35);
        getHelp.setText("Help");
        getHelp.setFont(new Font("Arial", Font.PLAIN, 18));
        getHelp.setFocusable(false);
    }

    public static void formatCloseHelpButton(JButton closeHelp) {
        closeHelp.setBounds(300, 420, 200, 50);
        closeHelp.setText("Continue...");
        closeHelp.setFont(new Font("Arial", Font.PLAIN, 24));
        closeHelp.setFocusable(false);
    }

    private static void labelCommonAttributes(JLabel label) {
        label.setForeground(new Color (187, 187, 187));
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setFocusable(false);
    }

    private static void textareaCommonAttributes(JTextArea textArea, String fieldText) {
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color (49, 51, 53));
        textArea.setForeground(new Color (187, 187, 187));
        textArea.setFont(new Font("Arial", Font.PLAIN, 15));
        textArea.setEditable(true);
        textArea.setFocusable(true);
        textArea.setCaretColor(new Color(187,187,187));
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    if (e.getModifiersEx() > 0) {
                        textArea.transferFocusBackward();
                    } else {
                        textArea.transferFocus();
                    }
                    e.consume();
                }
            }
        });
        textArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.requestFocus();
                textArea.setCaretPosition(textArea.getText().length());
                textArea.selectAll();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        textArea.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (textArea.getText().equals("")) {
                    textArea.setText(fieldText);
                }
                textArea.setCaretPosition(textArea.getText().length());
                textArea.selectAll();
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (evt.getComponent() instanceof JTextComponent){
                    ((JTextComponent)evt.getComponent()).select(0, 0);
                }
            }
        });
    }

    private static void scrollCommonAttributes(JScrollPane scrollPane) {
        scrollPane.setFocusable(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBackground(new Color (49, 51, 53));
        scrollPane.setForeground(new Color (187, 187, 187));
        scrollPane.setFont(new Font("Arial", Font.PLAIN, 15));
        scrollPane.setBorder(BorderFactory.createBevelBorder(1));
    }
}
