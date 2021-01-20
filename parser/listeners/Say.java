package parser.listeners;

import parser.ParserEvent;
import script_manager.Statement;
import script_manager.Token;

import java.util.ArrayList;

public class Say implements ParserEvent {

    @Override
    public String perParserPacket(Statement line) {

        if (line.getTokens().get(0).value.equals("say")) {
            ArrayList<Token> tokens = line.getTokens();
            tokens.remove(0);

            String code = "";
            String text = "";
            for (Token t : tokens) {
                String s = "";
                if (t.getType() == Token.Type.STRING) s = "\"";
                text = text + s + t.value + s;
            }

            code = "System.out.println(" + text + ")";
            return code;

        }

        return null;
    }

}
