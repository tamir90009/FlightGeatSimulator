package command;

import connect.myClient;
import expression.algorithem;
import java.util.LinkedList;
import symbolTable.Dictionary;

public class varAssigmentCommand implements Command {
   public void doCommand(LinkedList<String> arg) {
      if (Dictionary.getInstance().getVariable((String)arg.get(0)).getBindTo() != null) {
         String tmp = Dictionary.getInstance().getVariable((String)arg.get(0)).getBindTo();
         if (arg.size() != 2) {
            StringBuilder sb = new StringBuilder("");
            sb.append((String)arg.get(0));
            String val = sb.toString();
            arg.remove(0);
            double res = algorithem.calc(arg);
            arg.add(0, val);
            Dictionary.getInstance().setVal(val, res);
            myClient.getInstance().set(tmp, res);
         } else {
            myClient.getInstance().set(tmp, Double.parseDouble((String)arg.get(1)));
         }
      } else {
         Dictionary.getInstance().getVariable((String)arg.get(0)).setValue(Dictionary.getInstance().getVal((String)arg.get(1)));
         System.out.println("check if ged to here");
      }

   }
}
