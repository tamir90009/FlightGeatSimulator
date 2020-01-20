package symbolTable;

import java.util.LinkedList;
import java.util.Observable;

public class Variable extends Observable {
   private double value;
   private String bindTo = null;

   public Variable(double value) {
      this.value = value;
   }

   public void setValue(double value) {
      if (value != this.value) {
         this.value = value;
         this.setChanged();
         LinkedList<String> arg = new LinkedList();
         String s = String.valueOf(value);
         arg.add(this.getBindTo());
         arg.add(s);
         this.notifyObservers(arg);
      }

   }

   public double getValue() {
      return this.value;
   }

   public String getBindTo() {
      return this.bindTo;
   }

   public void setBindTo(String bindTo) {
      this.bindTo = bindTo;
   }
}
