import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Transpiler {
    private File f;
    private String label;
    private String code;
    private ArrayList<String> imports;
    private ArrayList<String> functions;
    // label, index
    private HashMap<String, Integer> functionDir;

    private static Transpiler transpiler;

    static {
        try {
            transpiler = new Transpiler();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Transpiler getTranspiler() { return transpiler; }

    private Transpiler() throws IOException {
        init();
    }

    private File getFile() throws IOException {
        File file = new File(label);
        file.createNewFile();
        return file;
    }

    private void init() throws IOException {
        label = "Code.java";
        f = getFile();
        code = "public static void main(String[] args) {\n";
        functions = new ArrayList<String>();
        imports = new ArrayList<String>();
    }

    public void addLine(String line) { code = code + line + "\n"; }

    public void addLineToFunction(int index, String line) { functions.set(index, functions.get(index) + line + "\n"); }

    public void addFunction(String label, String args, String returnType) {
        functions.add("public static " + returnType + " " + label + "(" + args + ") {\n");
        functionDir.put(label, functions.size()-1);
    }

    public void addImport(String label) {
        imports.add(label);
    }

    private int getFunction(String label) { return functionDir.get(label); }

    public void pack() throws IOException {
        FileWriter fw = new FileWriter(label);
        for (String importStr : imports) {
            fw.write("import " + importStr + ";");
        }
        fw.write("public class Code {\n");
        fw.write(code + "\n}");
        for (String str : functions) {
            fw.write(str + "\n}");
        }
        fw.write("}");
        fw.close();
    }

    private static void example() throws IOException {

        Transpiler transpiler = Transpiler.getTranspiler(); // gets transpiler
        transpiler.init(); // initializes file
        transpiler.addLine("code"); // adds line to main function
        transpiler.addFunction("function_name","Class label, Class label2","void"); // adds function
        int index = transpiler.getFunction("function_name"); // gets index for this function
        transpiler.addLineToFunction(index, "code"); // adds code to existing function
        transpiler.pack(); // creates file and writes code

    }

}
