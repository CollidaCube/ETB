import script_manager.Function;
import etb.TTGUI;

import javax.swing.*;

public class Main {

    public static Function mainFunction;
    // functions to be released in later versions
//    public static HashMap<String, Function> functions = new HashMap<String, Function>();

    public static String[][] debug = new String[][] {
            /* "Function",
            "Statement",
            "Token",
            "Transpiler",
            "Lexer",
            "Main" */
            new String[] { // Function
                    "Constructor",
                    "execute()"
            },
            new String[] { // Statement
                    "Constructor",
                    "execute()"
            },
            new String[] { // Token
                    "Constructor"
            },
            new String[] { // Transpiler
                    "Constructor",
                    "getFile()",
                    "init()",
                    "addLine()",
                    "addLineToFunction()",
                    "addFunction()",
                    "getFunction()",
                    "pack()"
            }
    };

    public static void report(String str, int sect, String reportFrom) {
        for (String method : debug[sect]) {
            if (method.equals(reportFrom)) {
                System.out.println(str);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        TTGUI gui = new TTGUI();
        gui.setSize(300,125);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);

    }
}
