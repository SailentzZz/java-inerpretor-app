package node;

import token.Token;

public final class NumberNode extends ExprNode {
    private Token number;

    public Token getNumber() {
        return this.number;
    }

    public NumberNode(Token number) {
        this.number = number;
    }

    public NumberNode copy(Token number) {
        return new NumberNode(number);
    }

    public String toString() {
        return "NumberNode(number=" + this.number + ")";
    }
}

