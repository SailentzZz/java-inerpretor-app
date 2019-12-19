package node;

import token.Token;

import java.util.List;

public final class WhileNode extends StmtNode {
    private Token token;
    private ExprNode condition;
    private List<StmtNode> body;

    public Token getToken() {
        return this.token;
    }

    public ExprNode getCondition() {
        return this.condition;
    }

    public List<StmtNode> getBody() {
        return this.body;
    }

    public WhileNode(Token token, ExprNode condition, List body) {
        this.token = token;
        this.condition = condition;
        this.body = body;
    }

    public WhileNode copy(Token token, ExprNode condition, List body) {
        return new WhileNode(token, condition, body);
    }

    public String toString() {
        return "WhileNode(token=" + this.token + ", condition=" + this.condition + ", body=" + this.body + ")";
    }
}
