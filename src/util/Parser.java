package util;

import node.*;
import token.Token;

import java.time.temporal.ValueRange;
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
            StmtNode s = parseStatement();
            condition.add(s);
        }
        return condition;
    }

    private StmtNode parseStatement() {
        Token t = match(TokenType.WHILE);

        if (t != null) {
            ExprNode condition = parseCondition();
            require(TokenType.DO);
            List<StmtNode> body = new ArrayList<>();
            while (match(TokenType.DONE) == null) {
                if (pos < tokens.size()) {
                    StmtNode s = parseStatement();
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
                ExprNode body = parseCondition();
                require(TokenType.RPAR);
                require(TokenType.SEMICOLON);
                return new PrintNode(t, body);
            }
        }

        t = match(TokenType.ID);
        if (t != null) {
            if (match(TokenType.EQUAL) != null) {
                ExprNode body = parse();
                require(TokenType.SEMICOLON);
                return new VariableNode(t, body);
            }
        }

        error("Ожидалось While/Print/Variable");
        return null;
    }

    private ExprNode parse() {
        ExprNode e1 = addeend();
        Token t;
        while (true) {
            t = match(TokenType.ADD, TokenType.SUB);
            if (t != null) {
                ExprNode e2 = addeend();
                e1 = new BinOpNode(t, e1, e2);
                continue;
            }
            break;
        }
        return e1;
    }

    private ExprNode incdec() {
        ExprNode e1 = addeend();
        Token t;
        while (true) {
            t = match();
            if (t != null) {
                ExprNode e2 = addeend();
                e1 = new BinOpNode(t, e1, e2);
                continue;
            }

            break;
        }
        return e1;
    }

    private ExprNode addeend() {
        ExprNode e1 = comparison();
        Token t;
        while (true) {
            t = match(TokenType.MUL, TokenType.DIV);
            if (t != null) {
                ExprNode e2 = multiplier();
                e1 = new BinOpNode(t, e1, e2);
                continue;
            }
            break;
        }
        return e1;
    }

    private ExprNode multiplier() {
        if (match(TokenType.LPAR) != null) {
            ExprNode expNode = parse();
            require(TokenType.RPAR);
            return expNode;
        } else {
            return elem();
        }
    }

    private ExprNode elem() {
        Token num = match(TokenType.NUMBER);
        if (num != null) {
            return new NumberNode(num);
        }

        Token neg = match(TokenType.SUB);
        if (neg != null) {
            ExprNode e1 = parse();
            return new NegativeNumberNode(e1);
        }

        Token id = match(TokenType.ID);
        if (id != null) {
            return new VarNode(id);
        }

        error("Ожидалось число или id");
        return null;
    }

    private ExprNode comparison() {
        ExprNode e1 = multiplier();
        Token t;
        while (true) {
            t = match(TokenType.MORE, TokenType.LESS, TokenType.MOREEQUAL, TokenType.LESSEQUAL);
            if (t != null) {
                ExprNode e2 = multiplier();
                e1 = new BinOpNode(t, e1, e2);
                continue;
            }
            break;
        }
        return e1;
    }

    private ExprNode parseCondition() {
        return parse();
    }
}
