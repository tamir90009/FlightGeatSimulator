package connect;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class myClient implements Client, Observer {
   private int port;
   private String ip;
   private boolean clientChecker = false;
   private static myClient instance;
   public Socket interpreter;
   public PrintWriter out;
   public static boolean flagClient = false;

   public myClient(String ip, int port) {
      this.port = port;
      this.ip = ip;
      System.out.println("client is born");
   }

   public synchronized void run() {
      while(!this.clientChecker) {
         try {
            System.out.println("try conect to simulator");
            this.interpreter = new Socket(this.ip, this.port);
            this.interpreter.setSoTimeout(100000);
            System.out.println("conected to simulator");
            flagClient = true;
            this.clientChecker = true;
            this.out = new PrintWriter(this.interpreter.getOutputStream());
         } catch (Exception var2) {
            System.out.println("Client Exception");
         }
      }

   }

   public void setManual(String path, Double val) {
      this.out.println("set " + path + " " + val);
      this.out.flush();
   }

   public void set(String path, Double val) {
      this.out.println("set " + path + " " + val);
      this.out.flush();
   }

   public void close() throws IOException {
      this.out.println("bye");
      this.out.flush();
      this.out.close();
      this.interpreter.close();
   }

   public static synchronized myClient getInstance(String ip, int port) {
      if (instance == null) {
         Class var2 = myServer.class;
         synchronized(myServer.class) {
            if (instance == null) {
               instance = new myClient(ip, port);
            }
         }
      }

      return instance;
   }

   public static synchronized myClient getInstance() {
      return instance;
   }

   public void update(Observable o, Object arg) {
   }
}
