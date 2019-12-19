package util;

import node.*;
import java.util.List;
import java.util.Map;

public final class Interpreter {
    private int eval(ExprNode n, Map<String, Integer> vars) {
        if (n != null) {
            if (n instanceof NumberNode) return Integer.parseInt(((NumberNode) n).getNumber().getText());
            if (n instanceof VarNode) return vars.get(((VarNode) n).getId().getText());
            if (n instanceof NegativeNumberNode) return -1 * eval(((NegativeNumberNode) n).getNumber(), vars);
            if (n instanceof BinOpNode) {
                BinOpNode binOpNode = (BinOpNode) n;
                int l = eval(binOpNode.getLeft(), vars);
                int r = eval(binOpNode.getRight(), vars);

                switch (binOpNode.getOp().getType()) {
                    case ADD:
                        return l + r;
                    case SUB:
                        return l - r;
                    case MUL:
                        return l * r;
                    case DIV:
                        if (r == 0) {
                            throw new ArithmeticException("Деление на ноль в строке - " + binOpNode.getOp().getLine() +
                                    ", позиция - " + binOpNode.getOp().getColumn());
                        } else {
                            return l / r;
                        }

                }
            }
        }

        BinOpNode binOpNode = (BinOpNode) n;
        throw new IllegalArgumentException("Неверный литерал в строке - " + binOpNode.getOp().getLine() +
                ", позиция - " + binOpNode.getOp().getColumn());

    }

    public void evalProgram(List<StmtNode> program, Map<String, Integer> vars) {
        for (StmtNode s : program) {
            evalStatement(s, vars);
        }
    }

    private void evalStatement(StmtNode s, Map<String, Integer> vars) {
        if (s != null) {
            if (s instanceof WhileNode) {
                WhileNode whileNode = (WhileNode) s;
                while (evalCondition(whileNode, vars)) {
                    for (StmtNode stmtNode : whileNode.getBody()) {
                        evalStatement(stmtNode, vars);
                    }
                }
            }

            if (s instanceof PrintNode) {
                PrintNode printNode = (PrintNode) s;
                System.out.println(eval(printNode.getBody(), vars));
            }

            if (s instanceof VariableNode) {
                VariableNode variableNode = (VariableNode) s;
                vars.put(variableNode.getName().getText(), eval(((VariableNode) s).getBody(), vars));
            }
        }
    }

    private boolean evalCondition(WhileNode s, Map<String, Integer> vars) {
            ExprNode condition = s.getCondition();
            if (condition != null) {
                if (condition instanceof BinOpNode) {
                    BinOpNode binOpNode = (BinOpNode) condition;
                    int p1 = eval(binOpNode.getLeft(), vars);
                    int p2 = eval(binOpNode.getRight(), vars);
                    switch (binOpNode.getOp().getType()) {
                        case MORE:
                            return p1 > p2;
                        case LESS:
                            return p1 < p2;
                        case MOREEQUAL:
                            return p1 >= p2;
                        case LESSEQUAL:
                            return p1 <= p2;
                        case TWOEQUAL:
                            return p1 == p2;
                        case NOTEQUAL:
                            return p1 != p2;
                        default:
                            throw new RuntimeException("Неверное условие в цикле while." +
                                    " Строка - " + s.getToken().getLine() +
                                    " Столбец - " + s.getToken().getColumn());
                    }
                }
            }

            throw new RuntimeException("Ожидалось условие в цикле while." +
                " Строка - " + s.getToken().getLine() +
                " Столбец - " + s.getToken().getColumn());
    }
}

