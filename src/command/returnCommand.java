package command;

import expression.algorithem;
import java.util.LinkedList;
import symbolTable.Dictionary;

public class returnCommand implements Command {
   public void doCommand(LinkedList<String> arg) {
      double res = algorithem.calc(arg);
      if (Dictionary.getInstance().ifContainInSymTb1((String)arg.get(0))) {
         Dictionary.getInstance().setVal((String)arg.get(0), res);
         Dictionary.getInstance().setVal("return", res);
      } else {
         Dictionary.getInstance().setVal("return", res);
      }

   }
}
