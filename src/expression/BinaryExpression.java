package expression;

public abstract class BinaryExpression implements Expression {
   protected Expression left;
   protected Expression right;

   public BinaryExpression(Expression l, Expression r) {
      this.left = l;
      this.right = r;
   }

   public abstract double calculate();
}
