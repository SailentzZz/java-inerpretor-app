package node;

public final class NegativeNumberNode extends ExprNode {
    private ExprNode number;

    public ExprNode getNumber() {
        return this.number;
    }

    public NegativeNumberNode(ExprNode number) {
        this.number = number;
    }

    public NegativeNumberNode copy(ExprNode number) {
        return new NegativeNumberNode(number);
    }

    public String toString() {
        return "NegativeNumberNode(number=" + this.number + ")";
    }

}
