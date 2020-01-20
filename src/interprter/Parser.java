package interprter;

import expression.Predicate;
import java.util.LinkedList;
import symbolTable.Dictionary;

public class Parser {
   public void parser(String[][] lexer) {
      String[][] var5 = lexer;
      int var4 = lexer.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         String[] line = var5[var3];
         this.parserLine(line);
      }

   }

   public void parserLine(String[] line) {
      Dictionary.getInstance();
      if (line[0].equals("while")) {
         this.whileHandler(line);
      }

      LinkedList<String> argList = convertALtoLL(line);
      if (checkBind(line)) {
         System.out.println("going do command: " + line[0]);
         Dictionary.getInstance().getCommand("=").doCommand(argList);
         Dictionary.getInstance().getCommand("bind").doCommand(argList);
      } else if (Dictionary.getInstance().ifContainInSymTb2(line[0])) {
         Dictionary.getInstance().getCommand(line[0]).doCommand(argList);
      } else {
         Dictionary.getInstance().getCommand("=").doCommand(argList);
      }

   }

   private static boolean checkBind(String[] line) {
      String[] var4 = line;
      int var3 = line.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String s = var4[var2];
         if (s.equals("bind")) {
            return true;
         }
      }

      return false;
   }

   public static LinkedList<String> convertALtoLL(String[] aL) {
      LinkedList<String> lL = new LinkedList();
      String[] var5 = aL;
      int var4 = aL.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         String s = var5[var3];
         lL.add(s);
      }

      if (!aL[0].equals("while")) {
         if (Dictionary.getInstance().ifContainInSymTb2((String)lL.get(0))) {
            lL.removeFirst();
         }

         if (lL.contains("=")) {
            lL.remove("=");
         }

         if (lL.contains("bind")) {
            lL.remove("bind");
         }
      }

      return lL;
   }

   public static String[] convertLinkedListToArray(LinkedList<String> arg) {
      int len = arg.size();
      String[] arry = new String[len];

      for(int i = 0; i < len; ++i) {
         String s = ((String)arg.get(i)).toString();
         arry[i] = s;
      }

      return arry;
   }

   public void whileHandler(String[] line) {
      Predicate predicate = new Predicate(line);
      LinkedList lst = convertALtoLL(line);

      while(predicate.calc()) {
         int i = 0;

         for(int size = lst.size(); i < size; ++i) {
            if (((String)lst.get(i)).equals("~") && !((String)lst.get(i)).equals("}")) {
               StringBuilder temp = new StringBuilder("");

               for(int j = i + 1; j < size && !((String)lst.get(j)).equals("~") && !((String)lst.get(j)).equals("}"); ++i) {
                  temp.append((String)lst.get(j)).append(" ");
                  ++j;
               }

               String[] goToParse = temp.toString().split(" ");
               if (goToParse.length != 1) {
                  this.parserLine(goToParse);
               }
            }
         }

         predicate.setLeft(line[1]);
         predicate.setRight(line[3]);
      }

   }
}
