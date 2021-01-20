package script_manager;

public class Token {
    public Type type;
    public String value;

    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public enum Type {

        NUMBER,    // 0 1 2 3 4 5 6 7 8 9 .
        BOOLEAN,   // true false
        STRING,    // "..." '...'
        OPERATOR,  // + - * / % ^ ! = < >
        MARKER,    // ( ) [ ] { } : " '
        KEY_WORD,  // Anything besides the above.
        UNDEFINED  // Not used for any actual Token instances. Token.Type equivalent of null

    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        value = this.value;
        return value;
    }

    public static Type checkCharType(int ch) {

        if ("0123456789.".contains("" + (char)ch)) {
            return Type.NUMBER;
        } else if ("\"'".contains("" + (char)ch)) {
            return Type.STRING;
        } else if ("+-*/%^!=<>".contains("" + (char)ch)) {
            return Type.OPERATOR;
        } else if ("()[]{}:".contains("" + (char)ch)) {
            return Type.MARKER;
        } else if (!(ch >= 'a' && ch <= 'z') &&
                   !(ch >= 'A' && ch <= 'Z') &&
                    ch != ' ') {
            throw new RuntimeException("Unexpected character found: " + ch + (char)ch);
        } else {
            return Type.UNDEFINED;
        }

    }

    public static Type checkType(String str) {

        if (str.equals("true") || str.equals("false")) {
            return Type.BOOLEAN;
        } if (str.equals("or") || str.equals("and") || str.equals("is") || str.equals("isnt") || str.equals("greater") || str.equals("than") || str.equals("less")) {
            return Type.OPERATOR;
        } else {
            return Type.KEY_WORD;
        }

    }

}
