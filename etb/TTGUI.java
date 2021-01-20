import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TTGUI extends JFrame {
    // C:\Users\Joseph\Desktop\interpret.etb
    private JTextField location;
    private JButton runButton;
    private JPanel myPanel;
    private JButton exitButton;
    private JLabel errorMessage;

    public TTGUI() {
        add(myPanel);
        final Process[] process = new Process[1];
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Transpiler tp = Transpiler.getTranspiler();

                if (!executeScript()) return;

                try {
                    tp.pack();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                Main.mainFunction = null;

                try {
                    Runtime rt = Runtime.getRuntime();
                    process[0] = rt.exec("cmd /c start cmd.exe /K \"cd D:\\CollidaCube\\IntelliJ Projects\\EOPL && " +
                            "javac Code.java && " +
                            "cls && " +
                            "java Code");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }

            public boolean executeScript() {
                File target = getFile();
                // Write the script to lex the code then run it.
                try {
                    Lexer.lex(target);
                    Main.mainFunction.execute();
                    errorMessage.setText("");
                    return true;
                } catch (FileNotFoundException e) {
                    errorMessage.setText("File could not be found.\nTry making sure your destination is correct!");
                    return false;
                }
            }

            public File getFile() {
                return new File(location.getText());
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}
