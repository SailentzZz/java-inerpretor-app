package token;

import util.TokenType;

public final class Token {
    private TokenType type;
    private String text;
    private int index;
    private int line;
    private int column;

    public TokenType getType() {
        return this.type;
    }

    public String getText() {
        return this.text;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int var1) {
        this.index = var1;
    }

    public int getLine() {
        return this.line;
    }

    public void setLine(int var1) {
        this.line = var1;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int var1) {
        this.column = var1;
    }

    public Token (TokenType type, String text, int index, int line, int column) {
        this.type = type;
        this.text = text;
        this.index = index;
        this.line = line;
        this.column = column;
    }

    public Token copy(TokenType type, String text, int index, int line, int column) {
        return new Token(type, text, index, line, column);
    }

    public String toString() {
        return "Token(type=" + this.type + ", text=" + this.text + ", index=" + this.index + ", line=" + this.line + ", column=" + this.column + ")";
    }
}

