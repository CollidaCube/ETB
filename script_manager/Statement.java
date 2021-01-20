package script_manager;

import java.util.ArrayList;

public class Statement {
    ArrayList<Token> tokens;

    public Statement(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

}
