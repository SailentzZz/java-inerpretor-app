package main;

import com.sun.tools.javac.parser.Tokens;
import node.StmtNode;
import sun.applet.resources.MsgAppletViewer;
import token.Token;
import util.Interpreter;
import util.Lexeme;
import util.Parser;
import util.TokenType;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class MainFun {
    public static void main(String[] args) {
            String text = "x = 0;\n" +
                    "while(x < 8) do\n" +
                    "x = x * 2;\n" +
                    "print(x);\n" +
                    "done;\n";

            Lexeme l = new Lexeme(text);
            List<Token> tokens = l.getLex();
            tokens.removeIf(t -> t.getType() == TokenType.SPACE);
            Parser p = new Parser(tokens);
            List<StmtNode> numbers = p.parseProgramm();
            Map<String, Integer> map = new HashMap<>();
            new Interpreter().evalProgramm(numbers, map);
    }
}
