package util;

import java.util.regex.Pattern;

public enum TokenType {

    NUMBER("[0-9]+"),
    WHILE("while"),
    DONE("done"),
    DO("do"),
    PRINT("print"),
    ID("[a-zA-z]"),
    ADD("\\+"),
    SUB("-"),
    MUL("\\*"),
    DIV("/"),
    LPAR("\\("),
    RPAR("\\)"),
    MOREEQUAL(">="),
    LESSEQUAL("<="),
    MORE(">"),
    LESS("<"),
    EQUAL("="),
    NOTEQUAL("!="),
    SPACE("[ \t\r\n]+"),
    SEMICOLON(";");

    final Pattern pattern;

    TokenType(String regexp) {
        pattern = Pattern.compile(regexp);
    }
}