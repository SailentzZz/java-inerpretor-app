package node;

import token.Token;

import java.util.List;

public final class WhileNode extends StmtNode {
    private Token token;
    private ExprNode condition;
    private List body;

    public Token getToken() {
        return this.token;
    }

    public void setToken(Token var1) {
        this.token = var1;
    }

    public ExprNode getCondition() {
        return this.condition;
    }

    public void setCondition(ExprNode var1) {
        this.condition = var1;
    }

    public List getBody() {
        return this.body;
    }

    public void setBody(List var1) {
        this.body = var1;
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
