package interprter;

import java.util.LinkedList;

public class Lexer {
   public String[][] lexer(String[] arr) {
      int length = arr.length;
      LinkedList<String[]> lst = new LinkedList();

      for(int i = 0; i < length; ++i) {
         if (!arr[i].startsWith("while")) {
            arr[i] = AddSpace(arr[i]);
            String[] s = arr[i].split(" ");
            lst.add(s);
         } else {
            StringBuilder inloop;
            for(inloop = new StringBuilder(""); !arr[i].endsWith("}"); ++i) {
               arr[i] = AddSpace(arr[i]);
               inloop.append(arr[i]).append(" ~ ");
            }

            arr[i] = AddSpace(arr[i]);
            inloop.append(arr[i]);
            lst.add(inloop.toString().split("[ \t]+"));
         }
      }

      String[][] r = new String[lst.size()][];
      lst.toArray(r);
      return r;
   }

   private static String AddSpace(String s) {
      s = s.replaceAll("\"", "");
      return s;
   }
}
