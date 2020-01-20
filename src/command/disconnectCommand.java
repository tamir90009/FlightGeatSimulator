package command;

import connect.myClient;
import connect.myServer;
import java.io.IOException;
import java.util.LinkedList;

public class disconnectCommand implements Command {
   public void doCommand(LinkedList<String> arg) {
      try {
         myClient.getInstance().close();
         if (myServer.getInstance() != null) {
            myServer.getInstance().close();
         }
      } catch (IOException var3) {
         var3.printStackTrace();
      }

   }
}
