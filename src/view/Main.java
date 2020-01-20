package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Model;
import view_model.ViewModel;

public class Main extends Application {
   public void start(Stage primaryStage) {
      try {
         Model model = new Model();
         ViewModel vm = new ViewModel(model);
         FXMLLoader fxl = new FXMLLoader();
         BorderPane root = (BorderPane)fxl.load(this.getClass().getResource("MainWindow.fxml").openStream());
         Scene scene = new Scene(root, 650.0D, 400.0D);
         scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
         MainWindowController mwc = (MainWindowController)fxl.getController();
         mwc.setViewModel(vm);
         vm.addObserver(mwc);
         primaryStage.setScene(scene);
         primaryStage.show();
      } catch (Exception var8) {
         var8.printStackTrace();
      }

   }

   public static void main(String[] args) {
      launch(args);
   }
}
