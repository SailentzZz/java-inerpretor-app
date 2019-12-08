package node;

import token.Token;

public final class PrintNode extends StmtNode {
    private Token token;
    private ExprNode body;

    public Token getToken() {
        return this.token;
    }

    public void setToken(Token var1) {
        this.token = var1;
    }

    public ExprNode getBody() {
        return this.body;
    }

    public void setBody(ExprNode var1) {
        this.body = var1;
    }

    public PrintNode(Token token, ExprNode body) {
        this.token = token;
        this.body = body;
    }

    public PrintNode copy(Token token, ExprNode body) {
        return new PrintNode(token, body);
    }

    public String toString() {
        return "PrintNode(token=" + this.token + ", body=" + this.body + ")";
    }
}

