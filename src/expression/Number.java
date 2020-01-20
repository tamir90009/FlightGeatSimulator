package expression;

public class Number implements Expression {
   private double value;

   public Number(double v) {
      this.value = v;
   }

   public double calculate() {
      return this.value;
   }
}
