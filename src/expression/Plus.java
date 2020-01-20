package expression;

public class Plus extends BinaryExpression {
   public Plus(Expression l, Expression r) {
      super(l, r);
   }

   public double calculate() {
      return this.left.calculate() + this.right.calculate();
   }
}
