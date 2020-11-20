package it.unibo.oop.lab.mvcio2;

import it.unibo.oop.lab.mvcio.SimpleGUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private final JTextField filePath;
    private final SimpleGUI simpleGUI;

    public  SimpleGUIWithFileChooser() {
        this.simpleGUI = new SimpleGUI();

        final JPanel fileChooserPanel = new JPanel(new BorderLayout());
        filePath = new JTextField();
        filePath.setEnabled(false);
        this.loadFile();
        fileChooserPanel.add(filePath, BorderLayout.CENTER);

        final JButton browseBtn = new JButton("Browse");
        fileChooserPanel.add(browseBtn, BorderLayout.LINE_END);
        this.simpleGUI.getCanvas().add(fileChooserPanel, BorderLayout.NORTH);

        browseBtn.addActionListener((ev) -> {
            final JFileChooser fileChooser = new JFileChooser();
            final int result = fileChooser.showSaveDialog(this.simpleGUI.getCanvas());
            if(result == JFileChooser.APPROVE_OPTION) {
                this.simpleGUI.getFileController().setFile(fileChooser.getSelectedFile());
                this.loadFile();
            } else if(result != JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(this.simpleGUI.getFrame(), "An unknown error has occurred","Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void loadFile() {
        try {
            this.filePath.setText(this.simpleGUI.getFileController().getFilePath());
            if(this.simpleGUI.getFileController().getFile().exists()) {
                this.simpleGUI.setTextAreaText(this.simpleGUI.getFileController().read());
            }
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(this.simpleGUI.getFrame(), e1, "Error", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }

    public static void main(final String... args) {
        new SimpleGUIWithFileChooser().simpleGUI.display();
    }
}
