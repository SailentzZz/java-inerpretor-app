package node;

import token.Token;

public final class VariableNode extends StmtNode {
    private Token name;
    private ExprNode body;

    public Token getName() {
        return this.name;
    }

    public ExprNode getBody() {
        return this.body;
    }

    public VariableNode(Token name, ExprNode body) {
        this.name = name;
        this.body = body;
    }

    public VariableNode copy(Token name, ExprNode body) {
        return new VariableNode(name, body);
    }

    public String toString() {
        return "VariableNode(name=" + this.name + ", body=" + this.body + ")";
    }
}

