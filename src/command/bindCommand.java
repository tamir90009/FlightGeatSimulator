package command;

import java.util.LinkedList;
import symbolTable.Dictionary;

public class bindCommand implements Command {
   public void doCommand(LinkedList<String> arg) {
      Dictionary.getInstance().bind((String)arg.get(0), (String)arg.get(1));
   }
}
