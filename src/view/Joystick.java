package view;

import javafx.scene.shape.Circle;

public class Joystick extends Circle {
   double radlimit;
   MainWindowController wc;

   public Joystick(MainWindowController wc) {
      this.wc = wc;
      this.radlimit = wc.frame.getRadius();
   }

   public void moveJoyStick() {
      this.wc.joystick.setOnMouseDragged((e) -> {
         double vectorLength = Math.sqrt(Math.pow(e.getX(), 2.0D) + Math.pow(e.getY(), 2.0D));
         if (vectorLength <= this.radlimit) {
            this.wc.joystick.setCenterX(e.getX());
            this.wc.joystick.setCenterY(e.getY());
            this.wc.valFromJoystick();
         } else {
            this.wc.joystick.setCenterX(e.getX() * this.radlimit / vectorLength);
            this.wc.joystick.setCenterY(e.getY() * this.radlimit / vectorLength);
            this.wc.valFromJoystick();
         }

      });
      this.wc.joystick.setOnMouseReleased((e) -> {
         this.wc.joystick.setCenterX(0.0D);
         this.wc.joystick.setCenterY(0.0D);
         this.wc.valFromJoystick();
      });
   }
}
