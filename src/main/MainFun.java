package main;

import node.StmtNode;
import token.Token;
import util.Interpreter;
import util.Lexeme;
import util.Parser;
import util.TokenType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFun {
    public static void main(String[] args) {
            String text = "x = -(-1) - 1;" +
                    "while(x == 0) do" +
                    "print(x);" +
                    "x = 1;" +
                    "done;";/*"x = -(1 + 1) + 1;\n" +
                    "while(x < 8) do\n" +
                    "x = x + 2;\n" +
                    "print(x);\n" +
                    "done;\n";*/

            Lexeme l = new Lexeme(text);
            List<Token> tokens = l.getLex();
            tokens.removeIf(t -> t.getType() == TokenType.SPACE);
            Parser p = new Parser(tokens);
            List<StmtNode> numbers = p.parseProgramm();
            Map<String, Integer> map = new HashMap<>();
            new Interpreter().evalProgramm(numbers, map);
    }
}
