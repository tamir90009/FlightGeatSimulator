package command;

import java.util.LinkedList;

public class SleepCommand implements Command {
   public void doCommand(LinkedList<String> arg) {
      System.out.println("going to sleep");

      try {
         Thread.sleep(Long.parseLong((String)arg.get(0)));
      } catch (Exception var3) {
      }

      System.out.println("wakes up");
   }
}
