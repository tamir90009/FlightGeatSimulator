package test;

public class MainOfFlightGear {
   public static void main(String[] args) {
      String[] test = new String[]{"openDataServer 6401 10", "connect 127.0.0.1 6400", "var breaks = bind \"/controls/flight/speedbrake\"", "var throttle = bind \"/controls/engines/current-engine/throttle\"", "var heading = bind \"/instrumentation/heading-indicator/offset-deg\"", "var airspeed = bind \"/instrumentation/airspeed-indicator/indicated-speed-kt\"", "var roll = bind \"/instrumentation/attitude-indicator/indicated-roll-deg\"", "var pitch = bind \"/instrumentation/attitude-indicator/internal-pitch-deg\"", "var rudder = bind \"/controls/flight/rudder\"", "var aileron = bind \"/controls/flight/aileron\"", "var elevator = bind \"/controls/flight/elevator\"", "var alt = bind \"/instrumentation/altimeter/indicated-altitude-ft\"", "sleep 80000", "breaks = 0", "throttle = 1", "var desiredPitch = 10", "var h0 = heading", "sleep 40000", "while alt < 1000 {", "rudder = (h0 - heading)/20", "aileron = (0 - roll) / 70 - 0.1", "elevator = pitch / 50 + 0.1", "print alt", "sleep 250", "}", "disconnect", "print done"};
      MyInterpreter.interpret(test);
      System.exit(0);
   }
}
