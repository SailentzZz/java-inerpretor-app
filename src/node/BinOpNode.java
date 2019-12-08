package node;

import token.Token;

public final class BinOpNode extends ExprNode {
    private Token op;
    private ExprNode left;
    private ExprNode right;

    public Token getOp() {
        return this.op;
    }

    public void setOp(Token var1) {
        this.op = var1;
    }

    public ExprNode getLeft() {
        return this.left;
    }

    public void setLeft(ExprNode var1) {
        this.left = var1;
    }

    public ExprNode getRight() {
        return this.right;
    }

    public void setRight(ExprNode var1) {
        this.right = var1;
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
