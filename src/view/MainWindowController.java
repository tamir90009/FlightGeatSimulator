package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import view_model.ViewModel;

public class MainWindowController implements Initializable, Observer {
   static final int TIME_DELAY = 100;
   @FXML
   public Joystick myjoystick;
   @FXML
   public Slider throttle;
   @FXML
   public Slider rudder;
   @FXML
   Button connect;
   @FXML
   Circle frame;
   @FXML
   Circle joystick;
   boolean isConnected = false;
   ViewModel vm;


   public void initialize(URL location, ResourceBundle resources) {
      this.myjoystick = new Joystick(this);
      ToggleGroup tg = new ToggleGroup();
      this.connect();

   }

   public void setViewModel(ViewModel vm) {
      this.vm = vm;
      vm.joyStickX.bind(this.joystick.centerXProperty());
      vm.joyStickY.bind(this.joystick.centerYProperty());
      vm.throttle.bind(this.throttle.valueProperty());
      vm.rudder.bind(this.rudder.valueProperty());
   }




   public void connect() {
      Stage popup = new Stage();
      VBox box = new VBox(20.0D);
      Label ipLabel = new Label("IP:");
      Label portLabel = new Label("PORT:");
      TextField ipUserInput = new TextField();
      TextField portUserInput = new TextField();
      Button submit = new Button("Submit");
      box.getChildren().addAll(new Node[]{ipLabel, ipUserInput, portLabel, portUserInput, submit});
      popup.setScene(new Scene(box, 350.0D, 250.0D));
      popup.setTitle("Connect to FlightGear");
      popup.show();
      popup.setAlwaysOnTop(true);
      submit.setOnAction((e) -> {
         String ip = ipUserInput.getText();
         String port = portUserInput.getText();
         this.vm.connectToSimVM(ip, port);
         popup.close();
         System.out.println("connected to FlightGear...");
      });
      this.isConnected = true;
   }


   public void moveElevatorAileron() {
      this.myjoystick.moveJoyStick();
   }

   public void valFromJoystick() {
      this.vm.controlElevatorAileronVm();
   }

   public void moveThrottle() {
      this.vm.controlThrottleVm();
   }

   public void moveRudder() {
      this.vm.controlRudderVm();
   }

   public void update(Observable arg0, Object arg1) {
   }
}
