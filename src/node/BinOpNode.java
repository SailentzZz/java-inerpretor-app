package node;

import token.Token;

public final class BinOpNode extends ExprNode {
    private Token op;
    private ExprNode left;
    private ExprNode right;

    public Token getOp() {
        return this.op;
    }

    public ExprNode getLeft() {
        return this.left;
    }

    public ExprNode getRight() {
        return this.right;
    }

    public BinOpNode(Token op, ExprNode left, ExprNode right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public BinOpNode copy (Token op, ExprNode left, ExprNode right) {
        return new BinOpNode(op, left, right);
    }

    public String toString() {
        return "BinOpNode(op=" + this.op + ", left=" + this.left + ", right=" + this.right + ")";
    }
}
