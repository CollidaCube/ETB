package parser;

import etb.*;
import parser.listeners.Say;
import parser.listeners.Variables;
import script_manager.Statement;
import script_manager.Token;

import java.util.ArrayList;

public class Parser {

    static ArrayList<ParserEvent> listeners
            = new ArrayList<ParserEvent>();
    static Parser parser
            = new Parser();
    static Transpiler transpiler = Transpiler.getTranspiler();

    private Parser() {
        addListener(new Variables());
        addListener(new Say());
    }

    public static Parser getParser() { return parser; }

    public void addListener(ParserEvent listener) {
        listeners.add(listener);
    }

    public boolean sendLine(Statement line) {
        System.out.println("New line parsed: \n");
        for (Token t : line.getTokens())
            System.out.println("TYPE: " + t.getType() + ", VALUE: " + t.getValue());
        System.out.println("\n");

        for (ParserEvent listener : listeners) {
            String code = listener.perParserPacket(line);
            if(code != null) {
                transpiler.addLine(code + ";");
                return true;
            }
        }
        return false;
    }

}
