package expression;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import symbolTable.Dictionary;

public class algorithem {
   public static double calc(LinkedList<String> arg) {
      String string = convertLlToString(arg);
      Queue<String> queue = new LinkedList();
      Stack<String> stack = new Stack();
      Stack<Expression> stackExp = new Stack();
      String[] split = string.split("(?<=[-+*/()])|(?=[-+*/()])");
      String[] var9 = split;
      int var8 = split.length;

      String s;
      for(int var7 = 0; var7 < var8; ++var7) {
         s = var9[var7];
         if (Dictionary.getInstance().ifContainInSymTb1(s)) {
            queue.add(Dictionary.getInstance().getVal(s).toString());
         } else if (isDouble(s)) {
            queue.add(s);
         } else {
            label109: {
               switch(s.hashCode()) {
               case 40:
                  if (!s.equals("(")) {
                     continue;
                  }
                  break label109;
               case 41:
                  if (!s.equals(")")) {
                     continue;
                  }

                  while(!((String)stack.peek()).equals("(")) {
                     queue.add((String)stack.pop());
                  }

                  stack.pop();
                  continue;
               case 42:
                  if (!s.equals("*")) {
                     continue;
                  }
                  break label109;
               case 43:
                  if (!s.equals("+")) {
                     continue;
                  }
                  break;
               case 45:
                  if (!s.equals("-")) {
                     continue;
                  }
                  break;
               case 47:
                  if (!s.equals("/")) {
                     continue;
                  }
                  break label109;
               default:
                  continue;
               }

               while(!stack.empty() && !((String)stack.peek()).equals("(")) {
                  queue.add((String)stack.pop());
               }

               stack.push(s);
               continue;
            }

            stack.push(s);
         }
      }

      while(!stack.isEmpty()) {
         queue.add((String)stack.pop());
      }

      Iterator var12 = queue.iterator();

      while(var12.hasNext()) {
         s = (String)var12.next();
         if (isDouble(s)) {
            stackExp.push(new Number(Double.parseDouble(s)));
         } else {
            Expression right = (Expression)stackExp.pop();
            Expression left = (Expression)stackExp.pop();
            switch(s.hashCode()) {
            case 42:
               if (s.equals("*")) {
                  stackExp.push(new Mul(left, right));
               }
               break;
            case 43:
               if (s.equals("+")) {
                  stackExp.push(new Plus(left, right));
               }
               break;
            case 45:
               if (s.equals("-")) {
                  stackExp.push(new Minus(left, right));
               }
               break;
            case 47:
               if (s.equals("/")) {
                  stackExp.push(new Div(left, right));
               }
            }
         }
      }

      double result = ((Expression)stackExp.pop()).calculate() * 1000.0D / 1000.0D;
      return result;
   }

   private static boolean isDouble(String val) {
      try {
         Double.parseDouble(val);
         return true;
      } catch (NumberFormatException var2) {
         return false;
      }
   }

   public static String convertLlToString(LinkedList<String> arg) {
      StringBuilder sb = new StringBuilder("");

      for(int i = 0; i < arg.size(); ++i) {
         sb.append((String)arg.get(i));
      }

      String string = sb.toString();
      return string;
   }
}
