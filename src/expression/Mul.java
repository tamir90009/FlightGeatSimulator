package expression;

public class Mul extends BinaryExpression {
   public Mul(Expression l, Expression r) {
      super(l, r);
   }

   public double calculate() {
      return this.left.calculate() * this.right.calculate();
   }
}
