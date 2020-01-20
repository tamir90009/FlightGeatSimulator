package symbolTable;

import command.Command;
import command.ConnectCommand;
import command.OpenDataServerCommand;
import command.PrintCommand;
import command.SleepCommand;
import command.VarCommand;
import command.bindCommand;
import command.disconnectCommand;
import command.loopCommand;
import command.returnCommand;
import command.varAssigmentCommand;
import connect.myClient;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {
   private static volatile Dictionary instance = null;
   private static Map<String, Variable> symTb1;
   private static Map<String, Command> symTb2;

   public Dictionary() {
      symTb1 = new HashMap();
      symTb2 = new HashMap();
      symTb2.put("print", new PrintCommand());
      symTb2.put("connect", new ConnectCommand());
      symTb2.put("sleep", new SleepCommand());
      symTb2.put("while", new loopCommand());
      symTb2.put("openDataServer", new OpenDataServerCommand());
      symTb2.put("disconnect", new disconnectCommand());
      symTb2.put("return", new returnCommand());
      symTb2.put("bind", new bindCommand());
      symTb2.put("var", new VarCommand());
      symTb2.put("=", new varAssigmentCommand());
      symTb1.put("rudder", new Variable(0.0D));
      symTb1.put("elevator", new Variable(0.0D));
      symTb1.put("h0", new Variable(0.0D));
      symTb1.put("heading", new Variable(0.0D));
      symTb1.put("desiredPitch", new Variable(0.0D));
      symTb1.put("throttle", new Variable(0.0D));
      symTb1.put("breaks", new Variable(0.0D));
      symTb1.put("alt", new Variable(0.0D));
      symTb1.put("roll", new Variable(0.0D));
      symTb1.put("pitch", new Variable(0.0D));
      symTb1.put("airspeed", new Variable(0.0D));
      symTb1.put("aileron", new Variable(0.0D));
      symTb1.put("/instrumentation/airspeed-indicator/indicated-speed-kt", new Variable(0.0D));
      symTb1.put("/instrumentation/altimeter/indicated-altitude-ft", new Variable(0.0D));
      symTb1.put("/instrumentation/altimeter/pressure-alt-ft", new Variable(0.0D));
      symTb1.put("/instrumentation/attitude-indicator/indicated-pitch-deg", new Variable(0.0D));
      symTb1.put("/instrumentation/attitude-indicator/indicated-roll-deg", new Variable(0.0D));
      symTb1.put("/instrumentation/attitude-indicator/internal-pitch-deg", new Variable(0.0D));
      symTb1.put("/instrumentation/attitude-indicator/internal-roll-deg", new Variable(0.0D));
      symTb1.put("/instrumentation/encoder/indicated-altitude-ft", new Variable(0.0D));
      symTb1.put("/instrumentation/encoder/pressure-alt-ft", new Variable(0.0D));
      symTb1.put("/instrumentation/gps/indicated-altitude-ft", new Variable(0.0D));
      symTb1.put("/instrumentation/gps/indicated-ground-speed-kt", new Variable(0.0D));
      symTb1.put("/instrumentation/gps/indicated-vertical-speed", new Variable(0.0D));
      symTb1.put("/instrumentation/heading-indicator/indicated-heading-deg", new Variable(0.0D));
      symTb1.put("/instrumentation/magnetic-compass/indicated-heading-deg", new Variable(0.0D));
      symTb1.put("/instrumentation/slip-skid-ball/indicated-slip-skid", new Variable(0.0D));
      symTb1.put("/instrumentation/turn-indicator/indicated-turn-rate", new Variable(0.0D));
      symTb1.put("/instrumentation/vertical-speed-indicator/indicated-speed-fpm", new Variable(0.0D));
      symTb1.put("/controls/flight/aileron", new Variable(0.0D));
      symTb1.put("/controls/flight/elevator", new Variable(0.0D));
      symTb1.put("/controls/flight/rudder", new Variable(0.0D));
      symTb1.put("/controls/flight/flaps", new Variable(0.0D));
      symTb1.put("/controls/engines/current-engine/throttle", new Variable(0.0D));
      symTb1.put("/engines/engine/rpm", new Variable(0.0D));
      symTb1.put("/controls/flight/speedbrake", new Variable(0.0D));
      symTb1.put("/instrumentation/heading-indicator/offset-deg", new Variable(0.0D));
   }

   public static Dictionary getInstance() {
      Dictionary returnTable = instance;
      if (returnTable == null) {
         Class var1 = Dictionary.class;
         synchronized(Dictionary.class) {
            if (returnTable == null) {
               instance = new Dictionary();
            }
         }
      }

      return instance;
   }

   public void setVal(String str, double val) {
      this.getVariable(str).setValue(val);
   }

   public Double getVal(String str) {
      return ((Variable)symTb1.get(str)).getValue();
   }

   public Variable getVariable(String str) {
      return (Variable)symTb1.get(str);
   }

   public void bind(String var, String bindTo) {
      symTb1.replace(var, this.getVariable(bindTo));
      this.getVariable(var).setBindTo(bindTo);
      System.out.println("val: " + var + "befor changing: " + this.getVal(var));
      System.out.println("val: " + bindTo + " :" + this.getVal(bindTo));
      System.out.println("val: " + var + "after changing: " + this.getVal(var));
   }

   public void addSim(String str, double val) {
      if (!symTb1.containsKey(str)) {
         symTb1.put(str, new Variable(val));
         this.getVariable(str).addObserver(myClient.getInstance());
      }

   }

   public Command getCommand(String str) {
      Command command = (Command)symTb2.get(str);
      return command;
   }

   public boolean ifContainInSymTb2(String str) {
      return symTb2.containsKey(str);
   }

   public boolean ifContainInSymTb1(String str) {
      return symTb1.containsKey(str);
   }

   public void restart() {
      symTb1.clear();
      symTb1.put("simX", new Variable(0.0D));
      symTb1.put("simY", new Variable(0.0D));
      symTb1.put("simZ", new Variable(0.0D));
   }

   public void printAll() {
      symTb1.forEach((key, value) -> {
         System.out.println(key + ":" + value.getValue());
      });
   }
}
