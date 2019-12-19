package node;

import token.Token;

public final class VarNode extends ExprNode {
    private Token id;

    public Token getId() {
        return this.id;
    }

    public VarNode(Token id) {
        this.id = id;
    }

    public VarNode copy(Token id) {
        return new VarNode(id);
    }

    public String toString() {
        return "VarNode(id=" + this.id + ")";
    }
}

