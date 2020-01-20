package model;

import command.Command;
import command.ConnectCommand;
import command.OpenDataServerCommand;
import connect.myClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Observable;
import test.MyInterpreter;

public class Model extends Observable {
   MyInterpreter myInterpreter = new MyInterpreter();
   PrintWriter outTocalcServer;
   BufferedReader inFromCalcServer;
   String[][] matrix;
   String path;
   String ipForCalcServer;
   int portForCalcServer;
   PrintWriter outToSimulator;

   public Model() {
      Command openServer = new OpenDataServerCommand();
      LinkedList<String> arg = new LinkedList();
      arg.add("5400");
      arg.add("10");
      openServer.doCommand(arg);
      this.setChanged();
   }

   public void controlElevatorAileron(double elevator, double aileron) {
      System.out.println(elevator + " , " + aileron);
      Thread th = new Thread(() -> {
         if (myClient.getInstance().out != null) {
            myClient.getInstance().setManual(" /controls/flight/elevator ", elevator);
            myClient.getInstance().setManual(" /controls/flight/aileron ", aileron);
         } else {
            System.out.println("printwriter is null");
         }

      });
      th.start();
   }

   public void controlRudder(double rudder) {
      myClient.getInstance().setManual(" /controls/flight/rudder ", rudder);
   }

   public void controlThrottle(double throttle) {
      myClient.getInstance().setManual(" /controls/engines/current-engine/throttle ", throttle);
   }

   public void connectToSimVM(String ip, String port) {
      LinkedList<String> arg = new LinkedList();
      arg.add(ip);
      arg.add(port);
      ConnectCommand c = new ConnectCommand();
      c.doCommand(arg);
      this.outToSimulator = myClient.getInstance().out;
   }

   public String connectToCalcServer(String ip, String port, String[][] matrixAsString, String initPointAsString, String goalPointAsString) {
      this.ipForCalcServer = ip;
      this.portForCalcServer = Integer.parseInt(port);
      this.matrix = matrixAsString;
      System.out.println("model . connect to calc path");
      return this.getPathFromCalcServer(initPointAsString, goalPointAsString);
   }

   public String getPathFromCalcServer(String initPointAsString, String goalPointAsString) {
      try {
         Socket theServer = new Socket(this.ipForCalcServer, this.portForCalcServer);
         System.out.println("connected to calc server");
         this.outTocalcServer = new PrintWriter(theServer.getOutputStream());
         this.inFromCalcServer = new BufferedReader(new InputStreamReader(theServer.getInputStream()));
      } catch (IOException var7) {
      }

      System.out.println("sending problem...");

      for(int i = 0; i < this.matrix.length; ++i) {
         int j;
         for(j = 0; j < this.matrix[i].length - 1; ++j) {
            this.outTocalcServer.print(this.matrix[i][j] + ",");
         }

         this.outTocalcServer.println(this.matrix[i][j]);
      }

      this.outTocalcServer.println("end");
      this.outTocalcServer.println(initPointAsString);
      this.outTocalcServer.println(goalPointAsString);
      this.outTocalcServer.flush();
      System.out.println("\tend\n\t" + initPointAsString + "\n\t" + goalPointAsString);
      System.out.println("\tproblem sent, waiting for solution...");

      try {
         this.path = this.inFromCalcServer.readLine();
      } catch (IOException var6) {
      }

      System.out.println("\tsolution: " + this.path);
      return this.path;
   }
}
