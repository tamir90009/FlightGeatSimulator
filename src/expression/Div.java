package expression;

public class Div extends BinaryExpression {
   public Div(Expression l, Expression r) {
      super(l, r);
   }

   public double calculate() {
      return this.left.calculate() / this.right.calculate();
   }
}
