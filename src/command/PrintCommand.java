package command;

import java.util.Iterator;
import java.util.LinkedList;
import symbolTable.Dictionary;

public class PrintCommand implements Command {
   static int caunter;

   public void doCommand(LinkedList<String> arg) {
      Iterator var3 = arg.iterator();

      while(var3.hasNext()) {
         String str = (String)var3.next();
         if (Dictionary.getInstance().ifContainInSymTb1(str)) {
            System.out.println(str + " " + Dictionary.getInstance().getVal(str));
         } else if (((String)arg.get(0)).equals("all")) {
            Dictionary.getInstance().printAll();
         } else {
            System.out.println(str);
         }
      }

   }
}
