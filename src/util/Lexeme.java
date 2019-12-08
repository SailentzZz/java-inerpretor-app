package util;

import token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class Lexeme {

    private String src;
    private List<Token> tokens = new ArrayList<>();
    private int pos;
    private int column;
    private int line;

    public Lexeme(String src) {
        this.src = src;
    }

    public boolean nextToken() {
        if (pos >= src.length()) {
            return false;
        }

        for (TokenType tt: TokenType.values()) {
            Matcher m = tt.pattern.matcher(src);
            m.region(pos, src.length());

            if (m.lookingAt()) {
                String text = m.group();
                Token t = new Token(tt, text, pos, line, column);
                tokens.add(t);
                pos = m.end();

                if (tt == TokenType.SPACE) {
                    for (char c: text.toCharArray()) {
                        if (c == '\n') {
                            line++;
                            column= 1;
                        } else {
                            column++;
                        }
                    }
                } else {
                    column = text.length();
                }
                return true;
            }
        }
        throw new RuntimeException("unknown symbol");
    }

    public List<Token> getLex() {
        while(nextToken()){}
        return tokens;
    }
}




