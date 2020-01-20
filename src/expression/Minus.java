package expression;

public class Minus extends BinaryExpression {
   public Minus(Expression l, Expression r) {
      super(l, r);
   }

   public double calculate() {
      return this.left.calculate() - this.right.calculate();
   }
}
