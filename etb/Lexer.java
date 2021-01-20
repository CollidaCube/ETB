import script_manager.Function;
import script_manager.Statement;
import script_manager.Token;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Lexer {

    public static void lex(File target) throws FileNotFoundException {
        Scanner scanner = new Scanner(target);
        ArrayList<Statement> statements = new ArrayList<Statement>();
        while (scanner.hasNextLine()) {
            ArrayList<Token> tokens = tokenize(scanner.nextLine());
            statements.add(new Statement(tokens));
        }
        Main.mainFunction = new Function(statements, new HashMap<String,Token.Type>(), "main");
    }

    public static ArrayList<Token> tokenize(String line) {
        System.out.println("Being run...");
        ArrayList<Token> tokens = new ArrayList<Token>();
        String token = "";
        boolean buildingString = false,
                buildingNum = false;

        for (char c : line.toCharArray()) {
            Token.Type type = Token.checkCharType(c);

            // String
            if (type == Token.Type.STRING) {
                if (buildingString) {
                    tokens.add(new Token(type, token));
                    token = "";
                }
                buildingString = !buildingString;
            }

            // Building String
            else if (buildingString) {
                token = token + c;
            }

            // Number
            else if (type == Token.Type.NUMBER
                    && token == "") {
                token = token + c;
                buildingNum = true;
            }

            // Boolean
            else if (Token.checkType(token) == Token.Type.BOOLEAN) {
                tokens.add(new Token(Token.Type.BOOLEAN, token));
                token = "";
            }

            // Boolean Operator
            else if (Token.checkType(token) == Token.Type.OPERATOR) {
                String op = "";
                if (token.equals("or")) op = "|";
                if (token.equals("and")) op = "&";
                if (token.equals("is")) op = "==";
                if (token.equals("isnt")) op = "!=";
                if (token.equals("greater")) op = ">";
                if (token.equals("less")) op = "<";
                tokens.add(new Token(Token.Type.BOOLEAN, op));
                token = "";
            }

            // Operator
            else if (type == Token.Type.OPERATOR) {
                if (buildingNum) {
                    buildingNum = false;
                    tokens.add(new Token(Token.Type.NUMBER, token));
                    token = "";
                }
                else if (token != "") {
                    tokens.add(new Token(Token.Type.KEY_WORD, token));
                    token = "";
                }

                tokens.add(new Token(type, "" + c));
            }

            else if (type == Token.Type.MARKER) {
                if (buildingNum) {
                    buildingNum = false;
                    tokens.add(new Token(Token.Type.NUMBER, token));
                    token = "";
                }
                else if (token != "") {
                    tokens.add(new Token(Token.Type.KEY_WORD, token));
                }

                tokens.add(new Token(type, "" + c));

            }

            else if (c == ' ') {
                if (buildingNum) {
                    buildingNum = false;
                    tokens.add(new Token(Token.Type.NUMBER, token));
                    token = "";
                }
                else if (token != "") {
                    System.out.println("Key Word: " + token);
                    tokens.add(new Token(Token.Type.KEY_WORD, token));
                    token = "";
                }
            }

            else if (type == Token.Type.UNDEFINED) {
                if (buildingNum) {
                    buildingNum = false;
                    tokens.add(new Token(Token.Type.NUMBER, token));
                    token = "";
                }
                token = token + c;
            }

            else {
                token = token + c;
            }

        }

        if (buildingNum) {
            tokens.add(new Token(Token.Type.NUMBER, token));
        }
        else if (token != "") {
            tokens.add(new Token(Token.Type.KEY_WORD, token));
        }

        return tokens;
    }

}
