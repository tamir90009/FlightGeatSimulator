package command;

import connect.myClient;
import java.util.LinkedList;

public class ConnectCommand implements Command {
   public void doCommand(LinkedList<String> arg) {
      System.out.println("connect command");
      int port = Integer.parseInt((String)arg.get(1));
      myClient.getInstance((String)arg.get(0), port);
      Thread thread = new Thread(() -> {
         myClient.getInstance().run();
      });
      thread.start();

      while(myClient.flagClient) {
      }

   }
}
