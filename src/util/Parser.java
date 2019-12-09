package util;

import node.*;
import token.Token;
import java.util.*;

public class Parser {

    private int pos = 0;
    List<Token> tokens;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token match (TokenType ...types) {
        if (pos < tokens.size()) {
            Token token = tokens.get(pos);
            if (Arrays.asList(types).contains(token.getType())) {
                pos++;
                return token;
            }
        }
        return null;
    }

    private Token require(TokenType ...types) {
        Token t = match(types);
        if (t == null) {
            error("Ожидался " + Arrays.toString(types));
            return null;
        } else {
            return t;
        }
    }

    private void error(String message) {
        if (pos < tokens.size()) {
            throw new RuntimeException(message + "в строке - " + tokens.get(pos - 1).getLine() +
                                        " позиции - " + tokens.get(pos - 1).getColumn());
        } else {
            throw new RuntimeException(message + " в конце выражения");
        }
    }

    public List<StmtNode> parseProgramm() {
        List<StmtNode> condition = new ArrayList<>();
        while (pos < tokens.size()) {
            StmtNode s = parseCondition();
            condition.add(s);
        }
        return condition;
    }

    private StmtNode parseCondition() {
        Token t = match(TokenType.WHILE);
        if (t != null) {
            ExprNode condition = parseStatement();
            require(TokenType.DO);
            List<StmtNode> body = new ArrayList<>();
            while (match(TokenType.DONE) == null) {
                if (pos < tokens.size()) {
                    StmtNode s = parseCondition();
                    body.add(s);
                } else {
                    error("Ожидался DONE");
                }
            }

            require(TokenType.SEMICOLON);
            return new WhileNode(t, condition, body);
        }

        t = match(TokenType.PRINT);
        if (t != null) {
            if (match(TokenType.LPAR) != null) {
                ExprNode body = parseStatement();
                require(TokenType.RPAR);
                require(TokenType.SEMICOLON);
                return new PrintNode(t, body);
            }
        }

        t = match(TokenType.ID);
        if (t != null) {
            if (match(TokenType.EQUAL) != null) {
                ExprNode body = parseStatement();
                require(TokenType.SEMICOLON);
                return new VariableNode(t, body);
            } else if (match(TokenType.TWOEQUAL) != null) {
                error("Ошибка присваивание сравнением");
            }
        }

        error("Ожидалось While/Print/Variable");
        return null;
    }

    private ExprNode AddSubExpression() {
        ExprNode e1 = MulDivExpression();
        Token t;
        while (true) {
            t = match(TokenType.ADD, TokenType.SUB);
            if (t != null) {
                ExprNode e2 = MulDivExpression();
                e1 = new BinOpNode(t, e1, e2);
                continue;
            }
            break;
        }
        return e1;
    }

    private ExprNode MulDivExpression() {
        ExprNode e1 = multiparExpression();
        Token t;
        while (true) {
            t = match(TokenType.MUL, TokenType.DIV);
            if (t != null) {
                ExprNode e2 = multiparExpression();
                e1 = new BinOpNode(t, e1, e2);
                continue;
            }
            break;
        }
        return e1;
    }

    private ExprNode multiparExpression() {
        if (match(TokenType.LPAR) != null) {
            ExprNode expNode = parseStatement();
            require(TokenType.RPAR);
            return expNode;
        } else {
            return elemExpression();
        }
    }

    private ExprNode elemExpression() {
        Token num = match(TokenType.NUMBER);
        if (num != null) {
            return new NumberNode(num);
        }

        Token neg = match(TokenType.SUB);
        if (neg != null) {
            ExprNode e1 = MulDivExpression();
            return new NegativeNumberNode(e1);
        }

        Token id = match(TokenType.ID);
        if (id != null) {
            return new VarNode(id);
        }

        error("Ожидалось число или id");
        return null;
    }

    private ExprNode compareExpression() {
        ExprNode e1 = AddSubExpression();
        Token t;
        while (true) {
            t = match(TokenType.MORE, TokenType.LESS, TokenType.TWOEQUAL, TokenType.MOREEQUAL, TokenType.LESSEQUAL, TokenType.NOTEQUAL);
            if (t != null) {
                ExprNode e2 = AddSubExpression();
                e1 = new BinOpNode(t, e1, e2);
                continue;
            }
            break;
        }
        return e1;
    }

    private ExprNode parseStatement() {
        return compareExpression();
    }
}
