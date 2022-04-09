import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI {

    // Field/button text control variables
    private boolean encode = true;
    private boolean hasTriggeredMLC = false;

    // Window Frame
    JFrame frame = new JFrame("MultiLayer Cipher");

    // Field Labels
    JLabel keyLabel = new JLabel();
    JLabel userTextLabel = new JLabel();
    JLabel cipherTextLabel = new JLabel();

    // Text areas
    JTextArea keyField = new JTextArea("Type your key/password here...");
    JTextArea userTextField = new JTextArea();
    JTextArea cipherTextField = new JTextArea(); // Not editable by user
    JTextArea helpField = new JTextArea(); // Not editable by user

    // Scroll panels for text areas
    JScrollPane keyScroll = new JScrollPane(keyField);
    JScrollPane userTextScroll = new JScrollPane(userTextField);
    JScrollPane cipherTextScroll = new JScrollPane(cipherTextField);

    // Radio buttons to switch control variable (encode)
    JRadioButton encryptMode = new JRadioButton("Set Encrypt Mode");
    JRadioButton decryptMode = new JRadioButton("Set Decrypt Mode");

    // Primary functionality buttons
    JButton pasteUserText = new JButton();
    JButton copyCipherText = new JButton();
    JButton callCipher = new JButton();
    JButton clearScreen = new JButton();
    JButton getHelp = new JButton();
    JButton closeHelp = new JButton();

    // GUI constructor formats components, adds components to frame, sets event listeners for buttons
    public GUI() {
        formatHelp();
        formatComponents();
        addHelp();
        closeHelp.addActionListener(e -> {
            addComponents();
            keyField.requestFocus();
        });
        encryptMode.addActionListener(e -> {
            encode = true;
            reformatDynamicFields();
            userTextField.requestFocus();
            userTextField.setText("");
            frame.getContentPane().repaint();
            keyField.requestFocus();
        });
        decryptMode.addActionListener(e -> {
            encode = false;
            reformatDynamicFields();
            userTextField.requestFocus();
            userTextField.setText("");
            frame.getContentPane().repaint();
            keyField.requestFocus();
        });
        pasteUserText.addActionListener(e -> {
            Clipboard clp = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable t = clp.getContents(this);
            try {
                userTextField.setText((String) t.getTransferData(DataFlavor.stringFlavor));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        copyCipherText.addActionListener(e -> {
            Clipboard clp = Toolkit.getDefaultToolkit().getSystemClipboard();
            clp.setContents(new StringSelection(cipherTextField.getText()), null);
        });
        clearScreen.addActionListener(e -> {
            keyField.setText("");
            userTextField.setText("");
            cipherTextField.setText("");
            keyField.requestFocus();
        });
        callCipher.addActionListener(e -> runMLC() );
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (hasTriggeredMLC) {
                    // Clears clipboard if text has been encrypted/decrypted by this application
                    Clipboard clp = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clp.setContents(new StringSelection(""), null);
                }
                System.exit(0);
            }
        });
        getHelp.addActionListener(e -> addHelp());
    }

    // Functionality for callCipher button - calls MultiLayerCipher, returns output to cipherTextField
    private void runMLC() {
        String key = keyField.getText();
        String message = userTextField.getText();
        MultiLayerCipher mlc = new MultiLayerCipher(key);
        if (encode) { // operation performed based on control variable value
            cipherTextField.setText(mlc.encrypt(message));
        }
        else {
            cipherTextField.setText(mlc.decrypt(message));
        }
        hasTriggeredMLC = true;
        frame.getContentPane().repaint(); // repaints frame to display output cipher text
    }

    private void formatHelp() {
        GUIConfig.formatHelpField(helpField);
        GUIConfig.formatCloseHelpButton(closeHelp);
    }

    private void addHelp() {
        frame.remove(keyLabel);
        frame.remove(userTextLabel);
        frame.remove(cipherTextLabel);
        frame.remove(keyScroll);
        frame.remove(userTextScroll);
        frame.remove(cipherTextScroll);
        frame.remove(getHelp);
        frame.remove(pasteUserText);
        frame.remove(copyCipherText);
        frame.remove(encryptMode);
        frame.remove(decryptMode);
        frame.remove(clearScreen);
        frame.remove(callCipher);

        frame.add(helpField);
        frame.add(closeHelp);
        frame.getContentPane().repaint();
    }

    // Formats components when GUI instance created
    private void formatComponents() {
        GUIConfig.formatFrame(frame);
        GUIConfig.formatKeyLabel(keyLabel);
        GUIConfig.formatKeyField(keyField);
        GUIConfig.formatKeyScroll(keyScroll);
        GUIConfig.formatUserTextLabel(userTextLabel, encode);
        GUIConfig.formatUserTextField(userTextField);
        GUIConfig.formatUserTextScroll(userTextScroll);
        GUIConfig.formatCipherTextLabel(cipherTextLabel, encode);
        GUIConfig.formatCipherTextField(cipherTextField);
        GUIConfig.formatCipherTextScroll(cipherTextScroll);
        GUIConfig.formatGetHelpButton(getHelp);
        GUIConfig.formatRadioButtons(encryptMode, decryptMode);
        GUIConfig.formatActionButtons(pasteUserText, copyCipherText, clearScreen, callCipher, encode);
    }

    // Adds components and paints frame
    private void addComponents() {
        frame.remove(helpField);
        frame.remove(closeHelp);
        frame.add(keyLabel);
        frame.add(userTextLabel);
        frame.add(cipherTextLabel);
        frame.add(keyScroll);
        frame.add(userTextScroll);
        frame.add(cipherTextScroll);
        frame.add(getHelp);
        frame.add(pasteUserText);
        frame.add(copyCipherText);
        frame.add(encryptMode);
        frame.add(decryptMode);
        frame.add(clearScreen);
        frame.add(callCipher);
        frame.getContentPane().repaint();
    }

    // Reformat fields to display changes made by radio buttons
    private void reformatDynamicFields() {
        GUIConfig.formatUserTextLabel(userTextLabel, encode);
        GUIConfig.formatUserTextField(userTextField);
        GUIConfig.formatCipherTextLabel(cipherTextLabel, encode);
        GUIConfig.formatActionButtons(pasteUserText, copyCipherText, clearScreen, callCipher, encode);
    }
}
