package test;

import interprter.Lexer;
import interprter.Parser;

public class MyInterpreter {
   public static void interpret(String[] lines) {
      Lexer lexer = new Lexer();
      Parser parser = new Parser();
      parser.parser(lexer.lexer(lines));
   }
}
