package parser.listeners;

import parser.ParserEvent;
import script_manager.Statement;
import script_manager.Token;

import java.util.ArrayList;

public class Variables implements ParserEvent {

    static ArrayList<String> labels = new ArrayList<String>();

    public String perParserPacket(Statement line) {
        ArrayList<Token> tokens = line.getTokens();

        if (line.getTokens().get(1).value.equals("=")) {
            String value = "", code = "";
            for (Token t : tokens.subList(2, tokens.size())) {
                String s = "";
                if (t.getType() == Token.Type.STRING) s = "\"";
                value = value + s + t.value + s;
            }

            if (!labels.contains(tokens.get(0).value)) {
                code = "var ";
                labels.add(tokens.get(0).value);
            }

            code = code + tokens.get(0).value + " = " + value;

            return code;

        }

        return null;
    }

}
