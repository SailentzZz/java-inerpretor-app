package node;

import token.Token;

public final class NumberNode extends ExprNode {
    private Token number;

    public Token getNumber() {
        return this.number;
    }

    public void setNumber(Token var1) {
        this.number = var1;
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

