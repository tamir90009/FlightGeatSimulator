package application;

public class Position {
   int _i;
   int _j;

   public Position(int _i, int _j) {
      this._i = _i;
      this._j = _j;
   }

   public Position() {
      this._i = 0;
      this._j = 0;
   }

   public Position(String ij) {
      this._i = Integer.parseInt(ij.split(",")[0]);
      this._j = Integer.parseInt(ij.split(",")[1]);
   }

   public Position(int num) {
      this._i = num;
      this._j = num;
   }

   public int get_i() {
      return this._i;
   }

   public void set_i(int _i) {
      this._i = _i;
   }

   public int get_j() {
      return this._j;
   }

   public void set_j(int _j) {
      this._j = _j;
   }

   public int getTileNumber(int rowLength) {
      return this._i * rowLength + this._j;
   }

   public boolean isMinusOne() {
      return this._i == -1 || this._j == -1;
   }

   public String toString() {
      return this._i + "," + this._j;
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (!Position.class.isAssignableFrom(obj.getClass())) {
         return false;
      } else {
         Position position = (Position)obj;
         return this._i == position.get_i() && this._j == position.get_j();
      }
   }
}
