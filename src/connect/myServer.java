package connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import symbolTable.Dictionary;

public class myServer implements server {
   private int port;
   private int times;
   private boolean stop;
   private static myServer instance;
   public static boolean flagServer = false;

   public myServer(int port, int times) {
      this.port = port;
      this.stop = false;
      this.times = times;
      System.out.println("server is born");
   }

   public synchronized void start() {
      try {
         ServerSocket servSock = new ServerSocket(this.port);
         System.out.println("waiting for client");
         Socket client = servSock.accept();
         System.out.println("Client has connected");
         flagServer = true;

         while(!this.stop) {
            try {
               BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
               String line = null;
               line = in.readLine();

               while((line = in.readLine()) != null && !this.stop) {
                  try {
                     String[] splited = line.split(",");
                     Dictionary.getInstance().setVal("/instrumentation/airspeed-indicator/indicated-speed-kt", Double.parseDouble(splited[0]));
                     Dictionary.getInstance().setVal("/instrumentation/altimeter/indicated-altitude-ft", Double.parseDouble(splited[1]));
                     Dictionary.getInstance().setVal("/instrumentation/altimeter/pressure-alt-ft", Double.parseDouble(splited[2]));
                     Dictionary.getInstance().setVal("/instrumentation/attitude-indicator/indicated-pitch-deg", Double.parseDouble(splited[3]));
                     Dictionary.getInstance().setVal("/instrumentation/attitude-indicator/indicated-roll-deg", Double.parseDouble(splited[4]));
                     Dictionary.getInstance().setVal("/instrumentation/attitude-indicator/internal-pitch-deg", Double.parseDouble(splited[5]));
                     Dictionary.getInstance().setVal("/instrumentation/attitude-indicator/internal-roll-deg", Double.parseDouble(splited[6]));
                     Dictionary.getInstance().setVal("/instrumentation/encoder/indicated-altitude-ft", Double.parseDouble(splited[7]));
                     Dictionary.getInstance().setVal("/instrumentation/encoder/pressure-alt-ft", Double.parseDouble(splited[8]));
                     Dictionary.getInstance().setVal("/instrumentation/gps/indicated-altitude-ft", Double.parseDouble(splited[9]));
                     Dictionary.getInstance().setVal("/instrumentation/gps/indicated-ground-speed-kt", Double.parseDouble(splited[10]));
                     Dictionary.getInstance().setVal("/instrumentation/gps/indicated-vertical-speed", Double.parseDouble(splited[11]));
                     Dictionary.getInstance().setVal("/instrumentation/heading-indicator/indicated-heading-deg", Double.parseDouble(splited[12]));
                     Dictionary.getInstance().setVal("/instrumentation/magnetic-compass/indicated-heading-deg", Double.parseDouble(splited[13]));
                     Dictionary.getInstance().setVal("/instrumentation/slip-skid-ball/indicated-slip-skid", Double.parseDouble(splited[14]));
                     Dictionary.getInstance().setVal("/instrumentation/turn-indicator/indicated-turn-rate", Double.parseDouble(splited[15]));
                     Dictionary.getInstance().setVal("/instrumentation/vertical-speed-indicator/indicated-speed-fpm", Double.parseDouble(splited[16]));
                     Dictionary.getInstance().setVal("/controls/flight/aileron", Double.parseDouble(splited[17]));
                     Dictionary.getInstance().setVal("/controls/flight/elevator", Double.parseDouble(splited[18]));
                     Dictionary.getInstance().setVal("/controls/flight/rudder", Double.parseDouble(splited[19]));
                     Dictionary.getInstance().setVal("/controls/flight/flaps", Double.parseDouble(splited[20]));
                     Dictionary.getInstance().setVal("/controls/engines/current-engine/throttle", Double.parseDouble(splited[21]));
                     Dictionary.getInstance().setVal("/engines/engine/rpm", Double.parseDouble(splited[22]));
                  } catch (NumberFormatException var6) {
                  } catch (ArrayIndexOutOfBoundsException var7) {
                  }
               }

               in.close();
               client.close();
               System.out.println("closing socket client");
            } catch (SocketTimeoutException var8) {
            }
         }

         servSock.close();
      } catch (IOException var9) {
         System.out.println("error");
      }

   }

   public void close() {
      this.stop = true;
   }

   public static synchronized myServer getInstance(int port, int times) {
      if (instance == null) {
         Class var2 = myServer.class;
         synchronized(myServer.class) {
            if (instance == null) {
               instance = new myServer(port, times);
            }
         }
      }

      return instance;
   }

   public static synchronized myServer getInstance() {
      return instance;
   }
}
