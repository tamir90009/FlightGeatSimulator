package expression;

import symbolTable.Dictionary;

public class Predicate {
   double left;
   double right;
   String charter;

   public Predicate(String[] arg) {
      int i = 1;
      if (Dictionary.getInstance().ifContainInSymTb1(arg[i])) {
         this.left = Dictionary.getInstance().getVal(arg[i]);
      } else {
         this.left = Double.parseDouble(arg[i]);
      }

      this.charter = arg[i + 1];
      if (Dictionary.getInstance().ifContainInSymTb1(arg[i + 2])) {
         this.right = Dictionary.getInstance().getVal(arg[i + 2]);
      } else {
         this.right = Double.parseDouble(arg[i + 2]);
      }

   }

   public double getLeft() {
      return this.left;
   }

   public void setLeft(String left) {
      if (Dictionary.getInstance().ifContainInSymTb1(left)) {
         this.left = Dictionary.getInstance().getVal(left);
      } else {
         this.left = Double.parseDouble(left);
      }

   }

   public double getRight() {
      return this.right;
   }

   public void setRight(String right) {
      if (Dictionary.getInstance().ifContainInSymTb1(right)) {
         this.right = Dictionary.getInstance().getVal(right);
      } else {
         this.right = Double.parseDouble(right);
      }

   }

   public boolean calc() {
      if (this.charter.equals(">")) {
         return this.left > this.right;
      } else if (this.charter.equals("<")) {
         return this.left < this.right;
      } else {
         return this.left == this.right;
      }
   }
}
