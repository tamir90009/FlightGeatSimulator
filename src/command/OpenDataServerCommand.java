package command;

import connect.myServer;
import java.util.LinkedList;

public class OpenDataServerCommand implements Command {
   public void doCommand(LinkedList<String> arg) {
      (new Thread(() -> {
         myServer s = new myServer(Integer.parseInt((String)arg.get(0)), Integer.parseInt((String)arg.get(1)));
         s.start();
      })).start();
   }
}
