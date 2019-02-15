public class RAM extends PCPart
{
   public RAM(String name, double value) {
      super(name, value);
   }

   public RAM() {
      name = null;
      value = 0;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public double getValue() {
      return value;
   }

   public void setValue(double value) {
      this.value = value;
   }

   public String toString() {
      return super.toString();
   }
}