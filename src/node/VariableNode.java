package node;

import token.Token;

public final class VariableNode extends StmtNode {
    private Token name;
    private ExprNode body;

    public Token getName() {
        return this.name;
    }

    public void setName(Token var1) {
        this.name = var1;
    }

    public ExprNode getBody() {
        return this.body;
    }

    public void setBody(ExprNode var1) {
        this.body = var1;
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

