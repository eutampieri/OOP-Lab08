package it.unibo.oop.lab.mvcio2;

import it.unibo.oop.lab.mvcio.SimpleGUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser extends SimpleGUI {
    private final JTextField filePath;

    public  SimpleGUIWithFileChooser() {
        super();

        final JPanel fileChooserPanel = new JPanel(new BorderLayout());
        filePath = new JTextField();
        filePath.setEnabled(false);
        this.loadFile();
        fileChooserPanel.add(filePath, BorderLayout.CENTER);

        final JButton browseBtn = new JButton("Browse");
        fileChooserPanel.add(browseBtn, BorderLayout.LINE_END);
        this.canvas.add(fileChooserPanel, BorderLayout.NORTH);

        browseBtn.addActionListener((ev) -> {
            final JFileChooser fileChooser = new JFileChooser();
            final int result = fileChooser.showSaveDialog(this.canvas);
            if(result == JFileChooser.APPROVE_OPTION) {
                this.fileController.setFile(fileChooser.getSelectedFile());
                this.loadFile();
            } else if(result != JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(frame, "An unknown error has occurred","Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void loadFile() {
        try {
            this.filePath.setText(this.fileController.getFilePath());
            if(this.fileController.getFile().exists()) {
                this.textArea.setText(this.fileController.read());
            }
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }


    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, the graphical interface
     * must reflect such change too. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */

    public static void main(final String... args) {
        new SimpleGUIWithFileChooser().display();
    }
}
