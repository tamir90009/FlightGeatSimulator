package view_model;

import application.Position;
import java.util.Observable;
import java.util.Observer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.Model;

public class ViewModel extends Observable implements Observer {
   private Model m;
   public DoubleProperty joyStickX;
   public DoubleProperty joyStickY;
   public DoubleProperty throttle;
   public DoubleProperty rudder;

   public ViewModel(Model m) {
      this.m = m;
      this.joyStickY = new SimpleDoubleProperty();
      this.joyStickX = new SimpleDoubleProperty();
      this.throttle = new SimpleDoubleProperty();
      this.rudder = new SimpleDoubleProperty();
   }

   public void update(Observable o, Object arg) {
      this.setChanged();
      this.notifyObservers();
   }

   public String connectToCalcServerVm(String ip, String port, int[][] matrix, Position init, Position goal) {
      String[][] matrixAsString = new String[matrix.length][matrix[0].length];
      String initPointAsString = init.get_i() + "," + init.get_j();
      String goalPointAsString = goal.get_i() + "," + goal.get_j();

      for(int i = 0; i < matrix.length; ++i) {
         for(int j = 0; j < matrix[i].length; ++j) {
            matrixAsString[i][j] = String.valueOf(matrix[i][j]);
         }
      }

      System.out.println("viewModel calc path");
      String returnFromConnect = this.m.connectToCalcServer(ip, port, matrixAsString, initPointAsString, goalPointAsString);
      System.out.println(returnFromConnect);
      return returnFromConnect;
   }

   public String getPathFromCalcServerVm(Position init, Position goal) {
      String initPointAsString = init.get_i() + "," + init.get_j();
      String goalPointAsString = goal.get_i() + "," + goal.get_j();
      String returngetPathFromCalcServerVm = this.m.getPathFromCalcServer(initPointAsString, goalPointAsString);
      System.out.println(returngetPathFromCalcServerVm);
      return returngetPathFromCalcServerVm;
   }

      public void controlElevatorAileronVm() {
         double elevatorVal = -this.joyStickY.doubleValue() / 100.0D;
         double aileronVal = this.joyStickX.doubleValue() / 100.0D;
         this.m.controlElevatorAileron(elevatorVal, aileronVal);
      }

   public void controlRudderVm() {
      this.m.controlRudder(this.rudder.doubleValue());
   }

   public void controlThrottleVm() {
      this.m.controlThrottle(this.throttle.doubleValue());
   }

   public void connectToSimVM(String ip, String port) {
      this.m.connectToSimVM(ip, port);
   }
}
