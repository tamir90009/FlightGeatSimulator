package command;

import connect.myClient;
import expression.algorithem;
import java.util.LinkedList;
import symbolTable.Dictionary;

public class VarCommand implements Command {
   public void doCommand(LinkedList<String> arg) {
      String tmp = Dictionary.getInstance().getVariable((String)arg.get(0)).getBindTo();
      if (tmp != null) {
         myClient.getInstance().set(tmp, Double.parseDouble((String)arg.get(1)));
      }

      if (arg.size() == 1) {
         Dictionary.getInstance().setVal((String)arg.get(0), 0.0D);
      } else {
         StringBuilder sb = new StringBuilder("");
         sb.append((String)arg.get(0));
         String val = sb.toString();
         arg.remove(0);
         double res = algorithem.calc(arg);
         arg.add(0, val);
         Dictionary.getInstance().setVal(val, res);
      }

   }
}
