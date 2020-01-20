package command;

import java.util.LinkedList;
import java.util.List;

public class loopCommand implements Command {
   private List<Command> cmList;

   public List<Command> getCmList() {
      return this.cmList;
   }

   public void setCmList(List<Command> cmList) {
      this.cmList = cmList;
   }

   public void doCommand(LinkedList<String> arg) {
   }

   public String[] convertLinkedListToArray(LinkedList<String> arg) {
      int len = arg.size();
      String[] arry = new String[len];

      for(int i = 0; i < len; ++i) {
         String s = ((String)arg.get(i)).toString();
         arry[i] = s;
      }

      return arry;
   }
}
